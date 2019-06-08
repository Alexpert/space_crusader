package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.TreePainter;

public class Tree  extends Entity {
	public Tree(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.OBSTACLE);
		this.setIPainter(new TreePainter(this));
	}

	public Tree(Tile tile) {
		super(tile, 10, false, Kind.OBSTACLE, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.setIPainter(new TreePainter(this));
	}
}
