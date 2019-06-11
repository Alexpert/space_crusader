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

	public Rabbit(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Rabbit"));
		this.moveable = true;
		this.collidable = true;
		this.setKind(Kind.MONSTER);
		this.setIPainter(new RabbitPainter(this));
		this.setActionHandler(new ActionHandlerRabbit(this));
	}

}
