package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerLaser;
import game.main.view.painters.LaserPainter;

public class Laser extends Entity{

	public Laser(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Laser"));
		this.moveable = true;
		this.setKind(Kind.MISSILE);
		this.setIPainter(new LaserPainter(this));
		this.setActionHandler(new ActionHandlerLaser(this));
	}
	
}
