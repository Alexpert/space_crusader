package game.main.model;

import game.main.model.action_handler.ActionHandlerLaser;
import game.main.view.painters.LaserPainter;

public class Laser extends Entity{

	protected Laser(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Laser"));
		this.moveable = true;
		this.setKind(Kind.MISSILE);
		this.setIPainter(new LaserPainter(this));
		this.setActionHandler(new ActionHandlerLaser(this));
	}
	
}
