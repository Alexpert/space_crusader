package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.ActionHandlerCompanyRabbit;
import game.main.model.action_handler.ActionHandlerRabbit;
import game.main.view.painters.AdultRabbitPainter;
import game.main.view.painters.CompanyRabbitPainter;

public class CompanyRabbit extends Entity {
	
	public static String nameAtomaton = "Company";
	private int counter;
	private boolean initialColor;

	public CompanyRabbit(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(CompanyRabbit.nameAtomaton));
		this.moveable = true;
		this.collidable = true;
		this.setKind(Kind.MONSTER);
		this.setIPainter(new CompanyRabbitPainter(this));
		this.setActionHandler(new ActionHandlerCompanyRabbit(this));
		this.setHealth(5);
		this.counter = 0;
		this.initialColor = true;
		this.setActionTimer(10);
	}

	public int getCounter() {
		return this.counter;
	}
	
	public void setCounter() {
		this.counter++;
		if(this.counter > 20) {
			this.counter = 0;
		}
	}
	
	public boolean getInitialColor() {
		return this.initialColor;
	}
	
	public void setInitialColor(boolean bool) {
		this.initialColor = bool;
	}

}
