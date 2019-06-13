package game.main.model.entities;

import java.util.ArrayList;

import game.main.model.*;
import game.main.model.action_handler.ActionHandlerPlayer;
import game.main.model.items.*;
import game.main.view.painters.*;

public class Player extends Entity {
	
	private int money;
	private ArrayList<Item> inventory;
	public static String nameAtomaton = "Playable";

	public Player(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Player.nameAtomaton));
		this.collidable = true;
		this.moveable = true;
		this.setKind(Kind.PLAYER);
		this.setIPainter(new PlayerPainter(this));
		this.setActionHandler(new ActionHandlerPlayer(this));
		this.inventory = new ArrayList<Item>();
		
		this.inventory.add(new Bomb());
		this.inventory.add(new Apple());
		this.inventory.add(new Fur());
		this.inventory.add(new Bomb());
		this.inventory.add(new Apple());
		this.inventory.add(new Fur());
		this.inventory.add(new Bomb());
		this.inventory.add(new Bomb());
	}

	public int getMoney() {
		return this.money;
	}
	
	public void add(Item item) {
		this.inventory.add(item);
	}
	
	public ArrayList<Item> getInventory(){
		return this.inventory;
	}
	
	@Override
	public void step(long now) {
		super.step(now);
	}

	public boolean addItem(Item item) {
		
		System.out.println("add to inventory: " + item.getName());
		if (this.inventory.size() < 10)
			this.inventory.add(item);
		
		for (Item it: this.inventory)
				System.out.println(it.getName());
		
		return this.inventory.size() < 11;
	}
}
