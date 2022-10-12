package main.game.game.world.tiles.tileBehaviors;

import main.game.game.events.listeners.PlayerEventListener;
import main.game.game.world.AssetLoader;
import main.game.game.world.tiles.Tile;

public abstract class TileBehavior implements PlayerEventListener {
	
	private Tile tile;
	private final AssetLoader assetLoader;
	private boolean generated;
	
	public TileBehavior(Tile tile, AssetLoader assetLoader) {
		this.assetLoader = assetLoader;
		this.generated = false;
		this.setTile(tile);
	}

	public Tile getTile() {
		return tile;
	}

	public void setTile(Tile tile) {
		this.tile = tile;
	}
	
	public abstract boolean isPassable();

	/**
	 * Called when this tile is first generated, non-manually. <br>
	 * <b>ALWAYS CALL SUPER() WHEN OVERRIDING THIS METHOD.</b>
	 */
	public void onGenerateTile() {
		this.setGenerated();
	}

	public boolean hasGenerated() {
		return generated;
	}

	public void setGenerated() {
		this.generated = true;
	}

	public AssetLoader getAssetLoader() {
		return assetLoader;
	}
}
