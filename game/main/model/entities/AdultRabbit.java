package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.action_handler.ActionHandlerRabbit;
import game.main.view.painters.AdultRabbitPainter;
import game.main.view.painters.RabbitPainter;

public class AdultRabbit extends Entity {

	public AdultRabbit(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Test"));
		this.moveable = true;
		System.out.println("Adult");
		this.collidable = true;
		this.setKind(Kind.MONSTER);
		this.setIPainter(new AdultRabbitPainter(this));
		this.setActionHandler(new ActionHandlerRabbit(this));
	}

}
