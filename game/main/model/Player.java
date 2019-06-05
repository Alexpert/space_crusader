package game.main.model;

import java.util.ArrayList;

public class Player extends Entity {
	
	private int money;
	private ArrayList<Item> inventary;

	public Player(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world);
		this.money = 0;
		this.inventary = new ArrayList<Item>();
		this.setActionHandler(new ActionHandlerPlayer(this));
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
	
	public void move() {
		this.getActionHandler().move();
	}
	
	public void move(Direction d) {
		this.getActionHandler().move(d);
	}
	
	public void turn(Direction d) {
		this.getActionHandler().turn(d);
	}
}
