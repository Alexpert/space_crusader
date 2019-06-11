package game.main.model;

import interpreter.IAutomaton;

public class Laser extends Entity{

	protected Laser(Tile tile) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Laser"));
		// TODO Auto-generated constructor stub
		this.moveable = true;
	}
	
}
