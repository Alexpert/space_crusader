package game.main.model;

import java.util.ArrayList;

public class Player extends Entity {
	
	private int money;
	private ArrayList<Item> inventary;

	Player(int x, int y, int health, char direction, boolean moveable) {
		super(x, y, health, direction, moveable);
		this.money = 0;
		this.inventary = new ArrayList<Item>();
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub

	}

	public int getMoney() {
		return this.money;
	}
	
	public void add(Item item) {
		this.inventary.add(item);
	}
}
