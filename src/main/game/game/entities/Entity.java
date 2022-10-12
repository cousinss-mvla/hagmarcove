package main.game.game.entities;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Entity {
	private String name;
	private int health;
	private int maxHealth;
	private int level;
	private int speed;
	private Image image;
	
	/**
	 * 
	 * @param name This entity's name.
	 * @param health This entity's health, presently.
	 * @param maxHealth This entity's maximum health capacity.
	 * @param level This entity's level.
	 * @param speed This entity's speed, as a percentage(so, default of 100).
	 */
	public Entity(String name, int health, int maxHealth, int level, int speed) {
		super();
		this.name = name;
		this.health = health;
		this.maxHealth = maxHealth;
		this.level = level;
		this.speed = speed;
		this.image = new ImageIcon(this.getClass().getResource("/assets/textures/missing_texture.png")).getImage();
	}
	public Entity(String name, int maxHealth, int level, int speed) {
		this(name, maxHealth, maxHealth, level, speed); //overload constructor for full health at inception
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public int getMaxHealth() {
		return maxHealth;
	}
	public void setMaxHealth(int maxHealth) {
		this.maxHealth = maxHealth;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSpeed() {
		return speed;
	}
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public Image getImage() {
		return this.image;
	}
}
