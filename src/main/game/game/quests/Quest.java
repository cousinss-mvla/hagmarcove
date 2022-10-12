package main.game.game.quests;

import java.util.ArrayList;

import main.game.game.entities.Player;
import main.game.game.items.Item;

public abstract class Quest {
	private Player player;
	private String name;
	private int stage;
	private boolean started;
	private int startLevel;
	private int rewardXP;
	private ArrayList<Item> rewardItems;
	
	public Quest(Player player, String name, int startLevel, int rewardXP, ArrayList<Item> rewardItems) {
		this.player = player;
		this.name = name;
		this.stage = 0;
		this.started = false;
		this.startLevel = startLevel;
		this.rewardXP = rewardXP;
		this.rewardItems = rewardItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public boolean isStarted() {
		return started;
	}

	public void setStarted(boolean started) {
		this.started = started;
	}

	public int getStartLevel() {
		return startLevel;
	}

	public void setStartLevel(int startLevel) {
		this.startLevel = startLevel;
	}

	public int getRewardXP() {
		return rewardXP;
	}

	public void setRewardXP(int rewardXP) {
		this.rewardXP = rewardXP;
	}

	public ArrayList<Item> getRewardItems() {
		return rewardItems;
	}

	public void setRewardItems(ArrayList<Item> rewardItems) {
		this.rewardItems = rewardItems;
	}
	
	public abstract String getStageDescription();
}
