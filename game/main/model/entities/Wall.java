package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.WallType;
import game.main.view.painters.WallPainter;

public class Wall extends Entity {

	WallType type;
	public Wall(Tile tile, WallType type) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Idle"));
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.type = type;
		this.setIPainter(new WallPainter(this, type));
	}
}
