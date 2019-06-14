package game.main.model.entities;

import game.main.model.*;
import game.main.model.action_handler.ActionHandlerIdle;
import game.main.view.painters.TreePainter;

public class Tree extends Entity {

	public static String nameAtomaton = "Tree";
	
	public Tree(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Tree.nameAtomaton));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.setIPainter(new TreePainter(this, tile.getBiome()));
		this.setActionHandler(new ActionHandlerIdle(this));
	}
}
