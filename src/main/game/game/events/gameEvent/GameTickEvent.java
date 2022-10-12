package main.game.game.events.gameEvent;

import main.game.game.GameModel;
import main.game.process.GameController;
import main.game.process.GameViewer;

public class GameTickEvent extends GameEvent {

	private final long tickNumber;
	private final float timeAccuracy;
	
	/**
	 * 
	 * @param model A reference to the GameModel.
	 * @param viewer A reference to the GameViewer. 
	 * @param controller A reference to the GameController.
	 * @param tickNumber The tick number of this tick event.
	 * @param timeAccuracy A positive float value, equal to the time this tick took to execute over the expected amount of time
	 * that a tick should take to execute. all operations that occur at a constant speed should be modified by this value - 
	 * For instance, if this tick occurs twice as fast as it should have(that is, a timeAccuracy value of 0.5), then a player should
	 * not move the usual 1 unit over this tick, but instead 0.5 units. This accounts for any amount of lag. 
	 */
	public GameTickEvent(GameModel model, GameViewer viewer, GameController controller, long tickNumber, float timeAccuracy) {
		super(model, viewer, controller);
		this.tickNumber = tickNumber;
		this.timeAccuracy = timeAccuracy;
	}
	
	public long getTickNumber() {
		return this.tickNumber;
	}
	
	public float getTimeAccuracy() {
		return this.timeAccuracy;
	}
}
