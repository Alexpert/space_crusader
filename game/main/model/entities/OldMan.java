package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerOldMan;
import game.main.view.painters.OldManPainter;

public class OldMan  extends Entity {

	public static String nameAtomaton = "OldMan";
	
	public OldMan(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(OldMan.nameAtomaton));
		this.collidable = true;
		this.moveable = true;
		this.setKind(Kind.TEAM);
		this.setIPainter(new OldManPainter(this));
		this.setActionHandler(new ActionHandlerOldMan(this));
		this.setHealth(Integer.MAX_VALUE);
	}

}
