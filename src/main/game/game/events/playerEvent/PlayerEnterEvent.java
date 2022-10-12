package main.game.game.events.playerEvent;

import main.game.game.GameModel;
import main.game.game.entities.Player;
import main.game.game.world.tiles.Tile;

public class PlayerEnterEvent extends PlayerMovementEvent {

	private final Tile exit;
	private final Tile enter;
	
	public PlayerEnterEvent(Player player, GameModel game, float oldX, float oldY, float newX, float newY) {
		super(player, game, oldX, oldY, newX, newY);
		this.exit = game.getTile(oldX, oldY);
		this.enter = game.getCurrentTile();
	}

	public Tile getExitedTile() {
		return exit;
	}

	public Tile getEnteredTile() {
		return enter;
	}
}
