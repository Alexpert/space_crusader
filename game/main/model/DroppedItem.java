package game.main.model;

public class DroppedItem extends Entity {

	Item item;
	
	DroppedItem(Tile tile, Item item) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Default"));
		this.item = item;
	}
}
