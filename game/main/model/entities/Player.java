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
	private int selectedItemIndex =-1;

	public Player(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Player.nameAtomaton));
		this.collidable = true;
		this.moveable = true;
		this.setKind(Kind.PLAYER);
		this.setIPainter(new PlayerPainter(this));
		this.setActionHandler(new ActionHandlerPlayer(this));
		this.inventory = new ArrayList<Item>();
		
		this.addItem(new Bomb());
	}

	public int getMoney() {
		return this.money;
	}
	
	public ArrayList<Item> getInventory(){
		return this.inventory;
	}
	
	@Override
	public void step(long now) {
		super.step(now);
	}

	public boolean addItem(Item item) {
		
		if(this.selectedItemIndex ==-1) {
			this.selectedItemIndex=0;
		}
		System.out.println("add to inventory: " + item.getName());
		if (this.inventory.size() < 10)
			this.inventory.add(item);
		
		for (Item it: this.inventory)
				System.out.println(it.getName());
		
		return this.inventory.size() < 11;
	}
	
	public Item getSelectedItem() {
		if(this.selectedItemIndex == -1) {
			return null;
		}
		else {
			return this.inventory.get(this.selectedItemIndex);
		}
	}
	
	public void incrementSelectedItem() {
		this.selectedItemIndex++;
	}
}
