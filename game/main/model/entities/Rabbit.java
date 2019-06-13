package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerRabbit;
import game.main.model.items.Fur;
import game.main.view.painters.RabbitPainter;

public class Rabbit extends Entity {

	public static String nameAtomaton = "Rabbit";
	
	public Rabbit(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Rabbit.nameAtomaton));
		this.moveable = true;
		this.collidable = true;
		this.setKind(Kind.MONSTER);
		this.setIPainter(new RabbitPainter(this));
		this.setActionHandler(new ActionHandlerRabbit(this));
		this.setHealth(5);
	}

	@Override
	public void die() {
		new DroppedItem(this.getTile(), new Fur());
		super.die();
	}
}
