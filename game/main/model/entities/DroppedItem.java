package game.main.model.entities;

import game.main.model.AutomatonProvider;
import game.main.model.Entity;
import game.main.model.Item;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.action_handler.DroppedItemActionHandler;
import game.main.view.painters.DroppedItemPainter;

public class DroppedItem extends Entity {

	Item item;
	
	public DroppedItem(Tile tile, Item item) {
		super(tile, AutomatonProvider.getInstance().getAutomaton("Item"));
		this.item = item;
		this.setIPainter(new DroppedItemPainter(this));
		this.setActionHandler(new DroppedItemActionHandler(this));
		this.setKind(Kind.ITEM);
	}

	public Item getItem() {
		return this.item;
	}
	
	@Override
	public void takeDamage(int dmg) {
		
	}
	
	
}
