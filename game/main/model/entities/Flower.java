package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.FlowerPainter;

public class Flower  extends Entity {

	public static String nameAtomaton = "Idle";
	public Flower(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Flower.nameAtomaton));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new FlowerPainter(this));
	}
}
