package game.main.model;

public abstract class Entity {
	
	private int health;
	private int maxHealth;
	private int x, y;
	private Direction orientation;
	private boolean moveable;
	private AbstractActionHandler actionHandler;
	private World world;
	
	Entity(int x, int y, int health, Direction d, boolean moveable, World world) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.maxHealth = health;
		this.orientation = d;
		this.moveable = moveable;
		this.world = world;
	}
	
	public void setActionHandler(AbstractActionHandler ac) {
		this.actionHandler = ac;
	}
	
	public abstract void step();
	
	public int getX() {
		return this.x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int healthpoints) {
		this.health = healthpoints;
	}
	
	public Direction getOrientation() {
		return this.orientation;
	}
	
	public void setOrientation(Direction d) {
		this.orientation = d;
	}
	
	public boolean moveable() {
		return this.moveable;
	}
	
	protected AbstractActionHandler getActionHandler() {
		return this.actionHandler;
	}
	
}
