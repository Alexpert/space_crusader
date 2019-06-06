package game.main.model;

import java.awt.Graphics;

import game.main.view.IPainter;

public abstract class Entity {
	
	private int health;
	private int maxHealth;
	private int x, y;
	private Direction orientation;
	private boolean moveable;
	private AbstractActionHandler actionHandler;
	private World world;
	private IPainter painter;
	
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
	
	public void paint(Graphics g) {
		this.painter.paint(g);
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
	
	public void addHealth(int healthpoints) {
		this.health += healthpoints;
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
	
	protected void setIPainter(IPainter ip) {
		this.painter=ip;
	}
	
	public World getWorld() {
		return this.world;
	}
	
	public Tile getTile(Direction d) {
		Direction d2 = d;
		if (d.ordinal() < 4 ) { //if the direction is not absolute
			if(d == Direction.LEFT) {
				d2 = d.get(((this.getOrientation().ordinal()+1)%4)+4);	//return WEST if the direction is NORTH
			}
			else if(d == Direction.RIGHT) {
				d2 = d.get(((this.getOrientation().ordinal()+3)%4)+4);
			}
			else if(d == Direction.BACK) {
				d2 = d.get(((this.getOrientation().ordinal()+2)%4)+4);
			}
			else if(d == Direction.FRONT) {
				d2 = this.getOrientation();
			}
		}
		
		if(d2 == Direction.NORTH) {
			if(this.getY() != 0) {
				return this.getWorld().getTile(this.getX(), this.getY()-1);
			}else {
				return this.getWorld().getTile(this.getX(), this.getWorld().getHeight()-1);
			}
		}
		if(d2 == Direction.SOUTH) {
			if(this.getY() != this.getWorld().getHeight()+1) {
				return this.getWorld().getTile(this.getX(), this.getY()+1);
			}else {
				return this.getWorld().getTile(this.getX(), 0);
			}
		}
		if(d2 == Direction.EAST) {
			if(this.getX() != this.getWorld().getWidth()-1) {
				return this.getWorld().getTile(this.getX()+1, this.getY());
			}else {
				return this.getWorld().getTile(0, this.getY());
			}
		}
		if(d2 == Direction.WEST) {
			if(this.getY() != 0) {
				return this.getWorld().getTile(this.getX()-1, this.getY());
			}else {
				return this.getWorld().getTile(this.getWorld().getWidth()-1, this.getY());
			}
		}
		return null;
	}
	
	public void move() {
		this.getActionHandler().move();
	}
	
	public void move(Direction d) {
		this.getActionHandler().move(d);
	}
	
	public void turn(Direction d) {
		this.getActionHandler().turn(d);
	}
	
	public void hit(Direction d) {
		this.getActionHandler().hit(d);
	}
}
