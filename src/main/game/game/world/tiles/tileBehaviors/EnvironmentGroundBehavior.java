package main.game.game.world.tiles.tileBehaviors;

import main.game.game.events.playerEvent.PlayerEnterEvent;
import main.game.game.events.playerEvent.PlayerMovementEvent;
import main.game.game.world.AssetLoader;
import main.game.game.world.Direction;
import main.game.game.world.tiles.Tile;

public class EnvironmentGroundBehavior extends PassableTileBehavior {

	public EnvironmentGroundBehavior(Tile tile, AssetLoader assetLoader) {
		super(tile, assetLoader);
	}

	@Override
	public void onPlayerMoved(PlayerMovementEvent e) {}

	@Override
	public void onPlayerEntered(PlayerEnterEvent e) {}

	@Override
	public void onGenerateTile() {
		super.onGenerateTile();
		int toss1 = (int)(Math.random() * this.getAssetLoader().getNumTileStates(this.getTile().getId()));
		this.getTile().setMeta(toss1);
		int toss2 = (int)(Math.random() * 4);
		switch(toss2) {
			case 0:
				this.getTile().setDirection(Direction.EAST);
				return;
			case 1:
				this.getTile().setDirection(Direction.SOUTH);
				return;
			case 2:
				this.getTile().setDirection(Direction.WEST);
				return;
			default:
				return; //direction is already NORTH, no need to re-grab the image
		}
		
	}
}
