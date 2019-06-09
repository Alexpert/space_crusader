package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.FlowerPainter;

public class Flower  extends Entity {

	public Flower(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new FlowerPainter(this));
	}
}
