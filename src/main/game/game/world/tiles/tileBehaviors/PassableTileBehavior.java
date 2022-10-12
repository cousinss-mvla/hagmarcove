package main.game.game.world.tiles.tileBehaviors;

import main.game.game.world.AssetLoader;
import main.game.game.world.tiles.Tile;

public abstract class PassableTileBehavior extends TileBehavior {

	public PassableTileBehavior(Tile tile, AssetLoader assetLoader) {
		super(tile, assetLoader);
	}

	@Override
	public boolean isPassable() {
		return true;
	}
	
}
