package main.game.game.events.listeners;

import main.game.game.events.gameEvent.GameTickEvent;

public interface GameEventListener {
	public void onGameTick(GameTickEvent e);
}
