package main.game.game.events.playerEvent;

import main.game.game.GameModel;
import main.game.game.entities.Player;

public class PlayerEvent {
	private final Player player;
	private final GameModel game;
	
	public PlayerEvent(Player player, GameModel game) {
		super();
		this.player = player;
		this.game = game;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public GameModel getGame() {
		return this.game;
	}
}
