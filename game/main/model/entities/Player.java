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

	public Player(int x, int y, int health, Direction d, boolean moveable, World world, Kind kind) {
		super(x, y, health, d, moveable, world,kind);
		this.money = 0;
		this.inventary = new ArrayList<Item>();
		this.setActionHandler(new ActionHandlerPlayer(this));
		this.setIPainter(new PlayerPainter(this));
	}

	public Player(Tile tile) {
		super(tile, 10, false, Kind.PLAYER, AutomatonProvider.getInstance().getAutomaton("Playable"));
		this.setIPainter(new PlayerPainter(this));
		this.setActionHandler(new ActionHandlerPlayer(this));
	}

	public int getMoney() {
		return this.money;
	}
	
	public void add(Item item) {
		this.inventary.add(item);
	}
}