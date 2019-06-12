package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerRabbit;
import game.main.view.painters.DroppedBombPainter;
import interpreter.IAutomaton;

public class DroppedBomb extends Entity{

	public static String nameAtomaton = "Bomb";
	
	public DroppedBomb(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Flower.nameAtomaton));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new DroppedBombPainter(this));
		this.setActionHandler(new ActionHandlerRabbit(this));
	}

}
