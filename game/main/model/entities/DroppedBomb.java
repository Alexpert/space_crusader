package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Item;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerDroppedBomb;
import game.main.model.action_handler.ActionHandlerRabbit;
import game.main.view.painters.DroppedBombPainter;
import interpreter.IAutomaton;

public class DroppedBomb extends DroppedItem{

	public static String nameAtomaton = "Bomb";
	
	public DroppedBomb(Tile tile,Item item) {
		super(tile, item);
		this.setAutomaton(AutomatonProvider.getInstance().getAutomaton(DroppedBomb.nameAtomaton));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new DroppedBombPainter(this));
		this.setActionHandler(new ActionHandlerDroppedBomb(this));
	}

}
