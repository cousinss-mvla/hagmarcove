package main.game.game.world;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.imageio.ImageIO;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import main.game.game.world.tiles.Tile;
import main.game.game.world.tiles.tileBehaviors.*;

public class AssetLoader {

	private BufferedImage missingTexture;
	private BufferedImage[][][] tileTextures;
	private String[] tileBehaviors;
	
	public AssetLoader() {
		preloadTextures();
		preloadBehaviors();
	}

	private void preloadTextures() {
		try {
			this.missingTexture = ImageIO.read(this.getClass().getResource("/assets/textures/missing_texture.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
		    URL url = this.getClass().getResource("/assets/generation/tiles.json");       
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    JSONParser parser = new JSONParser();
		    JSONObject json = (JSONObject) (parser.parse(in));
		    JSONArray tiles = (JSONArray) (json.get("tiles"));
		    this.tileTextures = new BufferedImage[tiles.size()][][];
		    for(Object obj : tiles) {
		    	JSONObject tile = (JSONObject) obj;
		    	int id = Integer.parseInt((String) tile.get("id"));
		    	JSONArray meta = (JSONArray) (tile.get("meta"));
		    	this.tileTextures[id] = new BufferedImage[meta.size()][4];
		    	for(int i = 0; i < meta.size(); i++) {
		    		this.tileTextures[id][i][0] = ImageIO.read(this.getClass().getResource("/assets/textures/tiles/" + meta.get(i) +".png"));
		    		this.tileTextures[id][i][1] = rotateImage(tileTextures[id][i][0], 1);
		    		this.tileTextures[id][i][2] = rotateImage(tileTextures[id][i][0], 2);
		    		this.tileTextures[id][i][3] = rotateImage(tileTextures[id][i][0], 3);
		    	}
		    }
		    in.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void preloadBehaviors() {
		try {
		    URL url = this.getClass().getResource("/assets/generation/tiles.json");       
		    BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
		    JSONParser parser = new JSONParser();
		    JSONObject json = (JSONObject) (parser.parse(in));
		    JSONArray tiles = (JSONArray) (json.get("tiles"));
		    this.tileBehaviors = new String[tiles.size()];
		    for(Object obj : tiles) {
		    	JSONObject tile = (JSONObject) obj;
		    	int id = Integer.parseInt((String) tile.get("id"));
		    	String behavior = (String) tile.get("behavior");
		    	tileBehaviors[id] = behavior;
		    }
		    in.close();
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}
	
	private BufferedImage rotateImage(BufferedImage image, int turns) {
		int deg = turns * 90;
		double sin = Math.abs(Math.sin(Math.toRadians(deg))),
		           cos = Math.abs(Math.cos(Math.toRadians(deg)));
	    int w = image.getWidth();
	    int h = image.getHeight();
	    int neww = (int) Math.floor(w*cos + h*sin),
	        newh = (int) Math.floor(h*cos + w*sin);
	    BufferedImage rotated = new BufferedImage(neww, newh, image.getType());
	    Graphics2D graphic = rotated.createGraphics();
	    graphic.translate((neww-w)/2, (newh-h)/2);
	    graphic.rotate(Math.toRadians(deg), w/2, h/2);
	    graphic.drawRenderedImage(image, null);
	    graphic.dispose();
	    return rotated;
	}

	public BufferedImage getMissingTexture() {
		return missingTexture;
	}
	
	public BufferedImage getTileTexture(int id, int metadata, Direction direction) {
		return this.tileTextures[id][metadata][directionToIndex(direction)];
	}
	
	public int getNumTiles() {
		return this.tileTextures.length;
	}
	
	/**
	 * @param id a tile id (>=0).
	 * @return the number of states this tile id can take - that is, the number of metadata values it has loaded in with textures.
	 */
	public int getNumTileStates(int id) {
		return this.tileTextures[id].length;
	}
	
	private static int directionToIndex(Direction dir) {
		switch(dir) {
		case NORTH:
			return 0;
		case EAST:
			return 1;
		case SOUTH:
			return 2;
		default:
			return 3;
		}
	}

	public TileBehavior getBehavior(Tile tile) {
		int id = tile.getId();
		String behavior = tileBehaviors[id]; //preloaded
		switch(behavior) {
			case "environment_ground":
				return new EnvironmentGroundBehavior(tile, this);
			case "water":
				return new WaterBehavior(tile, this);
			default:
				return new GenericBehavior(tile, this);
		}
	}
}
