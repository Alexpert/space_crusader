package game.main.model;

import java.util.ArrayList;

import game.main.controller.Controller;
import game.main.view.PlayerPainter;

public class Player extends Entity {
	
	private int money;
	private ArrayList<Item> inventary;

	public Player(int x, int y, int health, Direction d, boolean moveable, World world, Kind kind) {
		super(x, y, health, d, moveable, world,kind);
		this.money = 0;
		this.inventary = new ArrayList<Item>();
		this.setActionHandler(new ActionHandlerPlayer(this));
		this.setIPainter(new PlayerPainter(this));
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
