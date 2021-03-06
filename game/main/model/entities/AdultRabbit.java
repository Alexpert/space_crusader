package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.action_handler.ActionHandlerAdultRabbit;
import game.main.model.items.Fur;
import game.main.view.painters.AdultRabbitPainter;
import game.main.view.painters.RabbitPainter;

public class AdultRabbit extends Entity {

	public static String nameAtomaton = "AdultRabbit";
	
	public AdultRabbit(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(AdultRabbit.nameAtomaton));
		this.moveable = true;
		this.collidable = true;
		this.setKind(Kind.MONSTER);
		this.setIPainter(new AdultRabbitPainter(this));
		this.setActionHandler(new ActionHandlerAdultRabbit(this));
	}
	
	@Override
	public void die() {
		new DroppedItem(this.getTile(), new Fur());
		super.die();
	}
}
