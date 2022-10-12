package main.game.game;

import java.util.ArrayList;

import main.game.game.entities.Player;
import main.game.game.events.listeners.PlayerEventListener;
import main.game.game.events.playerEvent.PlayerEnterEvent;
import main.game.game.events.playerEvent.PlayerMovementEvent;
import main.game.game.world.AssetLoader;
import main.game.game.world.TileMap;
import main.game.game.world.tiles.Tile;

public class GameModel {
	
	private TileMap tileMap;
	private Player player;
	private float locX;
	private float locY;
	
	private ArrayList<PlayerEventListener> playerEventListeners;
	
	public GameModel(String map) {
		AssetLoader assetLoader = new AssetLoader();
		int numTiles = assetLoader.getNumTiles();
		//TODO remove this, make it work off map file
		int[][] mapIds = new int[400][400];
		for(int r = 0; r < mapIds.length; r++) {
			for(int c = 0; c < mapIds[0].length; c++) {
				int toss = (int)(Math.random() * numTiles);
				mapIds[r][c] = toss;
			}
		}
		this.tileMap = new TileMap(mapIds, assetLoader);
		this.player = new Player();
		playerEventListeners = new ArrayList<PlayerEventListener>();
		this.setLoc(tileMap.getSpawnX(), tileMap.getSpawnY());
	}
	
	public void addPlayerEventListener(PlayerEventListener listener) {
		this.playerEventListeners.add(listener);
	}
	
	public void removePlayerEventListener(PlayerEventListener listener) {
		this.playerEventListeners.remove(listener);
	}
	
	private void updatePlayerPosition(float oldX, float oldY, float newX, float newY) {
		this.locX = newX;
		this.locY = newY;
		boolean movedTile = ((int)(oldX) != (int)(newX)) 
				 || ((int)(oldY) != (int)(newY));
		for(PlayerEventListener l : playerEventListeners) {
			l.onPlayerMoved(new PlayerMovementEvent(this.getPlayer(), this, oldX, oldY, newX, newY));
			if(movedTile) {
				l.onPlayerEntered(new PlayerEnterEvent(this.getPlayer(), this, oldX, oldY, newX, newY));
			}
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public TileMap getTileMap() {
		return this.tileMap;
	}
	
	public Tile getTile(float x, float y) {
		try {
			return this.getTileMap().getTile((int) x, (int) y);
		} catch(ArrayIndexOutOfBoundsException e) {
			return null; // this is lazy, sorry
		}
	}
	
	public Tile getCurrentTile() {
		return this.getTile(this.getLocX(), this.getLocY());
	}

	/**
	 * sets/updates the player's location(and scrolls the screen). Twice as efficient as separately updating the X and Y.
	 * @param locX the new X-location to set.
	 * @param locY the new Y-location to set. 
	 */
	public void setLoc(float locX, float locY) {
		updatePlayerPosition(this.locX, this.locY, locX, locY);
	}
	
	public float getLocX() {
		return locX;
	}

	public void setLocX(float locX) {
		updatePlayerPosition(this.locX, this.locY, locX, this.locY);
	}

	public float getLocY() {
		return locY;
	}

	public void setLocY(float locY) {
		updatePlayerPosition(this.locX, this.locY, this.locX, locY);
	}
}
