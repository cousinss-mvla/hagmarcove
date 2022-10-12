package main.game.game.world.tiles.tileBehaviors;

import main.game.game.events.playerEvent.PlayerEnterEvent;
import main.game.game.events.playerEvent.PlayerMovementEvent;
import main.game.game.world.AssetLoader;
import main.game.game.world.tiles.Tile;

public class GenericBehavior extends PassableTileBehavior {

	public GenericBehavior(Tile tile, AssetLoader assetLoader) {
		super(tile, assetLoader);
	}

	@Override
	public void onPlayerMoved(PlayerMovementEvent e) {}

	@Override
	public void onPlayerEntered(PlayerEnterEvent e) {}

	@Override
	public void onGenerateTile() {}

}
