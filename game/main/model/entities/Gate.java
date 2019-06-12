package game.main.model.entities;

import game.main.model.*;
import game.main.model.action_handler.ActionHandlerGate;
import game.main.view.painters.GatePainter;

public class Gate extends Entity {

	public Gate(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("SpaceshipGate"));
		
		System.out.println("Creating gate");
		this.setKind(Kind.GATE);
		this.setIPainter(new GatePainter(this));
		this.setActionHandler(new ActionHandlerGate(this));
		
		System.out.println(this.automaton.getCurrent().getName());
	}
	
	@Override
	public void takeDamage(int dmg) {
		
	}
	
	@Override
	public void pop(Direction direction) {
		System.out.println("POP GATE");
		super.pop(direction);
	}
	
	@Override
	public void wizz(Direction direction) {
		System.out.println("WIZZ GATE");
		super.wizz(direction);
	}
	
	@Override
	public void patient() {
		super.patient();
//		System.out.println("PATIENT GATE");
	}
}
