package game.main.model.entities;

import java.util.ArrayList;

import game.main.controller.Controller;
import game.main.model.AutomatonProvider;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Item;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.action_handler.ActionHandlerPlayer;
import game.main.view.painters.PlayerPainter;
import game.main.view.painters.RabbitPainter;

public class Player extends Entity {
	
	private int money;
	private ArrayList<Item> inventary;

	public Player(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Playable"));
		this.collidable = true;
		this.setKind(Kind.PLAYER);
		this.setIPainter(new PlayerPainter(this));
		this.setActionHandler(new ActionHandlerPlayer(this));
	}

	public int getMoney() {
		return this.money;
	}
	
	public void add(Item item) {
		this.inventary.add(item);
	}
	
	@Override
	public void step(long now) {
		super.step(now);
		
	}
}
