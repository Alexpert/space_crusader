package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.view.painters.ChestPainter;
import game.main.view.painters.FlowerPainter;

public class Chest extends Entity {

	public static String nameAtomaton = "Idle";
	public Chest(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Flower.nameAtomaton));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new ChestPainter(this));
		this.collidable = true;
	}

}
