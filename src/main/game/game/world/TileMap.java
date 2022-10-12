package main.game.game.world;

import main.game.game.world.tiles.Tile;

public class TileMap {
	private Tile[][] map;
	private float spawnX;
	private float spawnY;
	private AssetLoader assetLoader;
	
	public TileMap(String map, AssetLoader assetLoader) {
		this.assetLoader = assetLoader;
//		String url = "/assets/maps/" + map + ".map";
//		try {
//			FileInputStream input = new FileInputStream(url);
//			//TODO
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		//set spawnX and spawnY from map file (should be first line)
	}
	
	public TileMap(Tile[][] map, AssetLoader assetLoader) {
		this.assetLoader = assetLoader;
		this.map = map;
	}
	
	public TileMap(int[][] idMap, AssetLoader assetLoader) {
		this.assetLoader = assetLoader;
		setMapFromIds(idMap);
	}
	
	private void setMapFromIds(int[][] idMap) {
		map = new Tile[idMap.length][idMap[0].length];
		for(int r = 0; r < idMap.length; r++) {
			for(int c = 0; c < idMap[0].length; c++) {
				map[r][c] = new Tile(idMap[r][c], assetLoader);
			}
		}
		this.setSpawnX(390);
		this.setSpawnY(390);
	}
	
	public Tile[][] getMap() {
		return this.map;
	}

	public float getSpawnX() {
		return spawnX;
	}

	public void setSpawnX(float spawnX) {
		this.spawnX = spawnX;
	}

	public float getSpawnY() {
		return spawnY;
	}

	public void setSpawnY(float spawnY) {
		this.spawnY = spawnY;
	}
	
	public Tile getTile(int x, int y) {
		return this.map[y][x];
	}
}
