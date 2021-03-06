package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.GraalActionHandler;
import game.main.view.painters.GraalPainter;

public class Graal extends Entity {
	
	public static String nameAtomaton = "PlayableGraal";
	
	public Graal (Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Graal.nameAtomaton));
		this.setKind(Kind.DANGER);
		this.setActionHandler(new GraalActionHandler(this));
		this.setIPainter(new GraalPainter(this));
	}
	
	

}
