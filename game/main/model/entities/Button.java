package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerButton;
import game.main.view.painters.ButtonPainter;
import game.main.view.painters.GatePainter;

public class Button extends Entity {

	public Button(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("BigRedButton"));
		this.setKind(Kind.ANYTHING);
		this.setIPainter(new ButtonPainter(this));
		this.setActionHandler(new ActionHandlerButton(this));
	}
	
	@Override
	public void takeDamage(int dmg) {
		
	}

}
