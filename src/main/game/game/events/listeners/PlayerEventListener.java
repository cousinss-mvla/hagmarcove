package main.game.game.events.listeners;

import main.game.game.events.playerEvent.PlayerEnterEvent;
import main.game.game.events.playerEvent.PlayerMovementEvent;

public interface PlayerEventListener {
	/**
	 * Called when the player moves.
	 * @param e the PlayerEvent associated with this player movement actiion.
	 */
	public void onPlayerMoved(PlayerMovementEvent e);
	/**
	 * Called when the player enters a new tile.
	 * @param e the PlayerEvent associated with this player movement action.
	 */
	public void onPlayerEntered(PlayerEnterEvent e);
}
