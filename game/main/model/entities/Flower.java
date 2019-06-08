package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.FlowerPainter;

public class Flower  extends Entity {
	public Flower(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.ANYTHING);
		this.setIPainter(new FlowerPainter(this));
	}

	public Flower(Tile tile) {
		super(tile, 10, false, Kind.ANYTHING, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.setIPainter(new FlowerPainter(this));
	}
}
