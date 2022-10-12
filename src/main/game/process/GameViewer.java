package main.game.process;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import main.game.game.GameModel;

public class GameViewer extends JPanel {
	
	private int scaling;
	private static final Color BACKGROUND = new Color(0, 190, 120);
	private static final int DEFAULT_SCALING = 400;
	
	private GameModel game;
	private JFrame window;
	private TileRenderer tileRenderer;
	
	private int scrollX;
	private int scrollY;
	
	public GameViewer(GameModel game, JFrame window) {
		super();
		this.game = game;
		tileRenderer = new TileRenderer(game.getTileMap());
		this.window = window;
		this.scaling = DEFAULT_SCALING;
	}
	
	public int getWidth() {
		return window.getWidth();
	}
	
	public int getHeight() {
		return window.getHeight();
	}
	
	public int getScaling() {
		return this.scaling;
	}
	
	public void setScaling(int scaling) { 
		this.scaling = scaling;
	}
	
	public void updatePlayerLocation(float x, float y) {
		setScrollX((int)(-x*(scaling/100)*this.tileRenderer.getTileSizeInPixelsUnscaled()));
		setScrollY((int)(-y*(scaling/100)*this.tileRenderer.getTileSizeInPixelsUnscaled()));
	}
	
	private void setScrollX(int x) {
		this.scrollX = x;
	}
	
	private void setScrollY(int y) {
		this.scrollY = y;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		g.setColor(BACKGROUND);
		g.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		
		this.render(g);
	}
	
	public void render(Graphics g) {
		tileRenderer.renderTiles(this.scrollX+getWidth()/2, this.scrollY+getHeight()/2, scaling, this.getWidth(), this.getHeight(), g);
		int playerSize = game.getPlayer().getImage().getWidth(null)*(scaling/100);
		g.drawImage(game.getPlayer().getImage(), this.getWidth() / 2 - playerSize / 2, this.getHeight() / 2 - playerSize / 2, game.getPlayer().getImage().getWidth(null)*(scaling/100), game.getPlayer().getImage().getHeight(null)*(scaling/100), null);
	}
}