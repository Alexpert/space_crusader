package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerOldMan;
import game.main.view.painters.OldManPainter;

public class OldMan  extends Entity {

	public static String nameAtomaton = "Garry";
	
	public OldMan(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(OldMan.nameAtomaton));
		this.collidable = true;
		this.moveable = true;
		this.setOrientation(Direction.SOUTH);
		this.setKind(Kind.TEAM);
		this.setIPainter(new OldManPainter(this));
		this.setActionHandler(new ActionHandlerOldMan(this));
	}

}
