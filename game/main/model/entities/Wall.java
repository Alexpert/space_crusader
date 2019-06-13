package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.WallType;
import game.main.model.action_handler.ActionHandlerIdle;
import game.main.view.painters.WallPainter;

public class Wall extends Entity {

	public static String nameAtomaton = "Idle";
	
	WallType type;
	public Wall(Tile tile, WallType type) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Wall.nameAtomaton));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.type = type;
		this.setIPainter(new WallPainter(this, type));
		this.setActionHandler(new ActionHandlerIdle(this));
	}
}
