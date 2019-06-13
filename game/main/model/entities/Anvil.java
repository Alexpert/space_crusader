package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerAnvil;
import game.main.view.painters.AnvilPainter;
import game.main.view.painters.FlowerPainter;

public class Anvil extends Entity {

	public static String nameAtomaton = "Anvil";
	
	public Anvil(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Anvil.nameAtomaton));
		this.setKind(Kind.OBSTACLE);
		this.setActionHandler(new ActionHandlerAnvil(this));
		this.setIPainter(new AnvilPainter(this));
	}
	
	@Override
	public void step(long now) {
		super.step(now);
		System.out.println("Anvil Stepp");
	}
	
	@Override
	public void takeDamage(int dmg) {
		
	}

}
