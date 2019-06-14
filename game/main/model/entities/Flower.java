package game.main.model.entities;

import game.main.model.*;
import game.main.model.action_handler.ActionHandlerIdle;
import game.main.view.painters.FlowerPainter;

public class Flower  extends Entity {

	public static String nameAtomaton = "Rock";
	public Flower(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Flower.nameAtomaton));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new FlowerPainter(this));
		this.setActionHandler(new ActionHandlerIdle(this));
	}
}
