package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.RockPainter;

public class Rock extends Entity {
	public Rock(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.OBSTACLE);
		this.setIPainter(new RockPainter(this));
	}

	public Rock(Tile tile) {
		super(tile, 10, false, Kind.OBSTACLE, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.setIPainter(new RockPainter(this));
	}
}
