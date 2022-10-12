package main.game.process;

import java.awt.Graphics;

import main.game.game.world.TileMap;
import main.game.game.world.tiles.Tile;

public class TileRenderer {
		
	private TileMap tileMap;
	
	private int size;
	
	public TileRenderer(TileMap tileMap) {
		this.tileMap = tileMap;
		size = tileMap.getMap()[0][0].getImage().getWidth(null);
	}
	
	/**
	 * @param tileMap
	 * @param x
	 * @param y
	 * @param scaling The scaling of the tilemap, in percentage form -
	 * 	default is 100%, drawing the images as they are scaled in their default image file forms.
	 * @param g
	 */
	public void renderTiles(int x, int y, int scaling, int appWidth, int appHeight, Graphics g) {
		Tile[][] map = tileMap.getMap();
		for(int r = 0; r < map.length; r++) {
			int yPos = y + r*size*(scaling/100);
			if(yPos < 0 - size*(scaling/100)) {
				continue;
			}
			if(yPos > appHeight) {
				break;
			}
			for(int c = 0; c < map[0].length; c++) {
				int xPos = x + c*size*(scaling/100);
				if(xPos < 0 - size*(scaling/100) || xPos > appWidth) {
					continue;
				}
				g.drawImage(map[r][c].getImage(), xPos, yPos, size*(scaling/100), size*(scaling/100), null);
			}
		}
	}
	
	public int getTileSizeInPixelsUnscaled() {
		return this.size;
	}
}
