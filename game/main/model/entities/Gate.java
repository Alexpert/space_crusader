package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.view.painters.GatePainter;
import interpreter.IAutomaton;

public class Gate extends Entity {

	public Gate(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Test"));
		this.setKind(Kind.GATE);
		this.setIPainter(new GatePainter(this));
	}
}
