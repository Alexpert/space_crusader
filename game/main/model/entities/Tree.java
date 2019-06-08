package game.main.model.entities;

import game.main.model.*;
import game.main.view.painters.TreePainter;

public class Tree extends Entity {

	public Tree(Tile tile) {
		super(tile, 10, false, Kind.OBSTACLE, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.setIPainter(new TreePainter(this, tile.getBiome()));
	}
}
