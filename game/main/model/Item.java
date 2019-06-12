package game.main.model;

import game.main.model.entities.DroppedItem;

public abstract class Item {
	private String name;
	private int value = 0;
	
	protected Item(String name) {
		this.setName(name);
	}
	
	protected Item(String name, int value) {
		this.setName(name);
		this.setValue(value);
	}
	
	public DroppedItem dropAtTile(Tile tile) {
		return new DroppedItem(tile, this);
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	protected void setValue(int value) {
		this.value = value;
	}
}
