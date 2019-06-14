package game.main.model.entities;

import game.main.model.*;
import game.main.model.action_handler.ActionHandlerIdle;
import game.main.view.painters.RockPainter;

public class Rock extends Entity {

	public static String nameAtomaton = "Rock";
	
	public Rock(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Rock.nameAtomaton));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.setIPainter(new RockPainter(this));
		this.setActionHandler(new ActionHandlerIdle(this));
		this.setHealth(90000);
		this.setMaxHealth(90000);
	}
}
