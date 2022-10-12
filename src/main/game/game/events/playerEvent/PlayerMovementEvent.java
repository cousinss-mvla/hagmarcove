package main.game.game.events.playerEvent;

import main.game.game.GameModel;
import main.game.game.entities.Player;

public class PlayerMovementEvent extends PlayerEvent {
	
	private final float oldX;
	private final float oldY;
	private final float newX;
	private final float newY;
	
	public PlayerMovementEvent(Player player, GameModel game, float oldX, float oldY, float newX, float newY) {
		super(player, game);
		this.oldX = oldX;
		this.oldY = oldY;
		this.newX = newX;
		this.newY = newY;
	}

	public float getOldX() {
		return oldX;
	}

	public float getOldY() {
		return oldY;
	}

	public float getNewX() {
		return newX;
	}

	public float getNewY() {
		return newY;
	}
	
	public float getXChange() {
		return this.newX - this.oldX;
	}
	
	public float getYChange() {
		return this.newY - this.oldY;
	}
}
