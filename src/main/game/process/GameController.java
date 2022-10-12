package main.game.process;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import main.game.game.Configuration;
import main.game.game.GameModel;
import main.game.game.events.gameEvent.GameTickEvent;
import main.game.game.events.listeners.GameEventListener;
import main.game.game.events.listeners.PlayerEventListener;
import main.game.game.events.playerEvent.PlayerEnterEvent;
import main.game.game.events.playerEvent.PlayerMovementEvent;

public class GameController implements KeyListener, PlayerEventListener, GameEventListener {
	
	private GameModel model;
	private GameViewer viewer;
	
	private Timer gameTickTimer;
	private GameTickTask gameTickTask;
	
	private HashMap<Integer, Boolean> keysDown;
	
	public GameController(GameModel model, GameViewer viewer) {
		this.model = model;
		this.model.addPlayerEventListener(this);
		this.viewer = viewer;
		this.viewer.addKeyListener(this);
		this.keysDown = new HashMap<Integer, Boolean>();
		this.gameTickTask = new GameTickTask(model, viewer, this);
		this.gameTickTask.addGameEventListener(this);
		this.gameTickTimer = new Timer();
		this.gameTickTimer.schedule(gameTickTask, 0, 1000 / Configuration.GAME_TICK_SPEED);
	}
	
	private class GameTickTask extends TimerTask {
		
		private ArrayList<GameEventListener> gameEventListeners;
		private GameModel model;
		private GameViewer viewer;
		private GameController controller;
		private long tickNumber; 
		private long time;
		
		public GameTickTask(GameModel model, GameViewer viewer, GameController controller) {
			super();
			gameEventListeners = new ArrayList<GameEventListener>();
			this.model = model;
			this.viewer = viewer;
			this.controller = controller;
			this.tickNumber = 0;
			this.time = System.currentTimeMillis();
		}
		
		public void addGameEventListener(GameEventListener listener) {
			gameEventListeners.add(listener);
		}
		
		public void removeGameEventListener(GameEventListener listener) {
			gameEventListeners.remove(listener);
		}
		
		private void gameTick(long tick) {
			long msDif = System.currentTimeMillis() - time;
			this.time = System.currentTimeMillis();
			long shouldMsDif = 1000 / Configuration.GAME_TICK_SPEED;
			for(GameEventListener listener : gameEventListeners) {
				listener.onGameTick(new GameTickEvent(this.model, this.viewer, this.controller, tick, (float)msDif/shouldMsDif));
			}
		}

		@Override
		public void run() {
			this.gameTick(this.tickNumber++);
		}
	}
	
	public void addGameTickEventListener(GameEventListener listener) {
		this.gameTickTask.addGameEventListener(listener);
	}
	
	public void removeGameTickEventListener(GameEventListener listener) {
		this.gameTickTask.removeGameEventListener(listener);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//TODO - to enable multiple keys being pressed at the same time, this should simply add the key to an arraylist keysdown.
		//then, every frame(not gametick, different things), the keylist should be checked, and if a movement key is down the movement
		//is applied. this way diagonal movement can be allowed as well as using weapons/spells while moving.
		//remember to in the keyReleased() method to take the key off of the arraylist. 
		int key = e.getKeyCode();
		keysDown.put(key, true);
		//movement - TODO this should only happen when it's allowed to - can't be opened in inventories(cancel all movement),
		//and also shouldn't happen in specific directions if there isn't space obvs
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		keysDown.put(key, false);
	}

	@Override
	public void onPlayerMoved(PlayerMovementEvent e) {
		viewer.updatePlayerLocation(e.getNewX(), e.getNewY());
		e.getGame().getCurrentTile().getBehavior().onPlayerMoved(e);
	}

	@Override
	public void onPlayerEntered(PlayerEnterEvent e) {
		// TODO Auto-generated method stub
		// TODO react to different tiles, idk
		e.getGame().getCurrentTile().getBehavior().onPlayerEntered(e);
	}

	@Override
	public void onGameTick(GameTickEvent e) {
		this.handlePlayerMovement(e);
	}
	
	private void handlePlayerMovement(GameTickEvent e) {
		// TODO this needs to react to the TileMovement enum. 
		
		boolean north, east, south, west;
		north = east = south = west = false;
		float ROOT_TWO = 1.41421356237f; //square root of two.
		
		//these variables will be applied to the player's location at the end of the method. 
		float dx = 0.0f;
		float dy = 0.0f;
		
		//The player's speed.
		float vel = Configuration.DEFAULT_PLAYER_SPEED_TILES_PER_FRAME * e.getTimeAccuracy() 
				* ((float)(model.getPlayer().getSpeed())/100);
		
		//determine whether the player is holding down the keys associated with the four cardinal directions.
		east = keysDown.get(KeyEvent.VK_RIGHT) != null && keysDown.get(KeyEvent.VK_RIGHT);
		west = keysDown.get(KeyEvent.VK_LEFT) != null && keysDown.get(KeyEvent.VK_LEFT);
		north = keysDown.get(KeyEvent.VK_UP) != null && keysDown.get(KeyEvent.VK_UP);
		south = keysDown.get(KeyEvent.VK_DOWN) != null && keysDown.get(KeyEvent.VK_DOWN);

		//negate opposite directions. 
		if(north && south) {
			north = false;
			south = false;
		}
		if(east && west) {
			east = false;
			west = false;
		}
		
		//if going diagonal, 
		if(north && east) {
			dx = vel/ROOT_TWO;
			dy = -vel/ROOT_TWO;
		} else if(north && west) {
			dx = -vel/ROOT_TWO;
			dy = -vel/ROOT_TWO;
		} else if(south && east) {
			dx = vel/ROOT_TWO;
			dy = vel/ROOT_TWO;
		} else if(south && west) {
			dx = -vel/ROOT_TWO;
			dy = vel/ROOT_TWO;
		//if going single direction, simply move there. 
		} else if(north) {
			dy = -vel;
		} else if(east) {
			dx = vel;
		} else if(south) {
			dy = vel;
		} else if(west) {
			dx = -vel;
		}
		
		if(dx != 0.0f || dy != 0.0f) {
			model.setLoc(model.getLocX() + dx, model.getLocY() + dy);
			viewer.repaint();
		}
	}
}
