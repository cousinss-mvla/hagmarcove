package main.game.game.entities;

import javax.swing.ImageIcon;

public class Player extends Entity {
	
	private int xp;
	
	public Player() {
		super("You", 100, 1, 100);
		this.setImage(new ImageIcon(this.getClass().getResource("/assets/textures/entities/player/player_base.png")).getImage());
		
		this.setXp(0);
	}

	public int getXp() {
		return xp;
	}

	public void setXp(int xp) {
		this.xp = xp;
	}
}
