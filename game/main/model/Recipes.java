package game.main.model;

import java.util.Arrays;

import game.main.model.entities.DroppedItem;
import game.main.model.items.*;

public class Recipes {
	static Craft[] crafts = {
			new Craft("Fur", "Fur", "Bomb")
	};
	
	public static Item craft(DroppedItem item1, DroppedItem item2) {
		int i = 0;
		while (i < crafts.length && crafts[i].entity1 != item1.getItem().getName() 
				&& crafts[i].entity2 != item2.getItem().getName()) {
			i++;
		}
		
		return i == crafts.length ? null : createItem(crafts[i].craft);
	}

	private static Item createItem(String craft) {
		Item item = null;
		switch (craft) {
		case "Fur":
			item = new Fur();
			break;
		case "Apple":
			item = new Apple();
			break;
		case "Bomb":
			item = new Bomb();
			break;
		}
		return item;
	}

}

class Craft {
	String entity1;
	String entity2;
	String craft;
	
	Craft(String entity1, String entity2, String craft) {
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.craft = craft;
	}
}