package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.view.painters.FurniturePainter;

public class Furniture extends Entity{
	private FurnitureType type;
	public static String nameAtomaton = "Idle";
	
	public Furniture(Tile tile, FurnitureType type) {
		super(tile, AutomatonProvider.getInstance().getAutomaton(Rock.nameAtomaton));
		this.type = type;
		this.collidable = true;
		this.setKind(Kind.OBSTACLE);
		this.setIPainter(new FurniturePainter(this));
		this.setHealth(90000);
	}
	
	public FurnitureType getType() {
		return this.type;
	}
	
	

}
