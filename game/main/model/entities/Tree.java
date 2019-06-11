package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.TreePainter;

public class Tree extends Entity {

	public Tree(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.setIPainter(new TreePainter(this, tile.getBiome()));
	}
}
