package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.RockPainter;

public class Rock extends Entity {

	public static String nameAtomaton = "Idle";
	
	public Rock(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Rock.nameAtomaton));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.setIPainter(new RockPainter(this));
	}
}
