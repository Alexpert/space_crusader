package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.view.painters.ButtonPainter;
import game.main.view.painters.GatePainter;

public class Button extends Entity {

	public Button(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Button"));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new ButtonPainter(this));
	}
	
	@Override
	public void takeDamage(int dmg) {
		
	}

}
