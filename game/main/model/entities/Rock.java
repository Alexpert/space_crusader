package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.RockPainter;

public class Rock extends Entity {

	public Rock(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.setIPainter(new RockPainter(this));
	}
}
