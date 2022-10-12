package main.game.game.world.tiles;

import java.awt.image.BufferedImage;

import main.game.game.world.Direction;
import main.game.game.world.AssetLoader;
import main.game.game.world.tiles.tileBehaviors.TileBehavior;

public class Tile {
	
	private int id;
	private AssetLoader assetLoader;
	private TileBehavior behavior;
	private BufferedImage image;
	private int metadata;
	private Direction direction;
	
	public Tile(int id, int metadata, Direction direction, AssetLoader assetLoader) {
		this.assetLoader = assetLoader;
		this.setDirection(direction);
		this.setId(id);		
		this.setBehavior(assetLoader.getBehavior(this));
		this.setMeta(metadata);
	}
	
	public Tile(int id, AssetLoader assetLoader) {
		this(id, 0, Direction.NORTH, assetLoader);
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setId(int id) {
		this.id = id;
		this.image = this.assetLoader.getTileTexture(id, metadata, direction);
		this.setBehavior(assetLoader.getBehavior(this));
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public void setImage(BufferedImage image) {
		this.image = image;
	}

	public TileBehavior getBehavior() {
		return behavior;
	}

	public void setBehavior(TileBehavior behavior) {
		this.behavior = behavior;
	}

	public int getMeta() {
		return metadata;
	}

	public void setMeta(int metadata) {
		this.metadata = metadata;
		if(!(this.behavior.hasGenerated())) {
			this.behavior.onGenerateTile();
		}
		this.image = this.assetLoader.getTileTexture(this.getId(), this.getMeta(), this.getDirection());
	}
	
	public void setDirection(Direction direction) {
		this.direction = direction;
		this.image = this.assetLoader.getTileTexture(this.getId(), this.getMeta(), this.getDirection());
	}
	
	public Direction getDirection() {
		return this.direction;
	}
}