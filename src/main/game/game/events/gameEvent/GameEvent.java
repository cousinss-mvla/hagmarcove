package main.game.game.events.gameEvent;

import main.game.game.GameModel;
import main.game.process.GameController;
import main.game.process.GameViewer;

public class GameEvent {
	private final GameModel model;
	private final GameViewer viewer;
	private final GameController controller;
	
	public GameEvent(GameModel model, GameViewer viewer, GameController controller) {
		super();
		this.model = model;
		this.viewer = viewer;
		this.controller = controller;
	}

	public GameModel getModel() {
		return model;
	}

	public GameViewer getViewer() {
		return viewer;
	}

	public GameController getController() {
		return controller;
	}
}
