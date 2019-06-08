package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.action_handler.ActionHandlerPlayer;
import game.main.model.action_handler.ActionHandlerRabbit;
import game.main.view.painters.RabbitPainter;

public class Rabbit extends Entity {
	public Rabbit(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.MONSTER);
		this.setIPainter(new RabbitPainter(this));
		this.setActionHandler(new ActionHandlerRabbit(this));
	}

	public Rabbit(Tile tile) {
		super(tile, 10, false, Kind.MONSTER, AutomatonProvider.getInstance().getAutomaton("Rabbit"));
		this.setIPainter(new RabbitPainter(this));
		this.setActionHandler(new ActionHandlerRabbit(this));
	}

}
