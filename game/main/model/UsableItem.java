package game.main.model;

public abstract class UsableItem extends Item {
	protected UsableItem(String name) {
		super(name);
	}
	
	public abstract void use();
}
