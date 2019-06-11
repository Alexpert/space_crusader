package game.main.model;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.model.action_handler.AbstractActionHandler;
import game.main.view.IPainter;
import interpreter.IAutomaton;

public abstract class Entity {

	private int health = 10;
	private int maxHealth = 10;
	private Direction orientation = Direction.NORTH;
	private Kind kind = Kind.ANYTHING;
	protected boolean moveable = false;
	protected boolean collidable = false;
	private boolean isVisible = true;
	
	private boolean hasViewport = false;
	private long currentTimeAction = 0;
	private long totalTimeAction = 0;
	private long beginTimeAction = 0;
	private Action currentAction = null;
	
	private AbstractActionHandler actionHandler;
	protected IAutomaton automaton;
	private IPainter painter;
	
	private Tile tile;

	protected Entity(Tile tile, IAutomaton automaton) {
		this.automaton = automaton;
		this.setTile(tile);
		System.out.println(this.automaton.getName());
	}
	
	
	
	public void paint(Graphics g,int posX,int posY) {
		this.painter.paint(g,posX,posY);
	}

	public void step(long now) {
		
		if(now<this.beginTimeAction+this.totalTimeAction) {
			this.currentTimeAction = this.beginTimeAction+this.totalTimeAction - now;
		}
		else {
			this.beginTimeAction = this.beginTimeAction+this.totalTimeAction;
			this.automaton.step(this);
		}
	}

	public void addHealth(int healthpoints) {
		this.health += healthpoints;
	}

	
	protected void setKind(Kind kind) {
		this.kind = kind;
	}
	
	public void setActionHandler(AbstractActionHandler ac) {
		this.actionHandler = ac;
	}

	public int getX() {
		return this.getTile().getX();
	}

	public int getY() {
		return this.getTile().getY();
	}

	public int getHealth() {
		return this.health;
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
	public void updateMoveable(boolean moveable) {
		this.moveable = moveable;
	}

	protected AbstractActionHandler getActionHandler() {
		return this.actionHandler;
	}

	protected void setIPainter(IPainter ip) {
		this.painter = ip;
	}

	public World getWorld() {
		return this.getTile().getWorld();
	}
	
	public void moveToTile(int x, int y) {
		Tile tile = this.getWorld().getTile(x, y);
		int i = 0;
		boolean collide = false;
		while (i < tile.nbEntity() && !collide) {
			if (tile.getEntity(i).collidable) {
				collide = true;
			}
			i++;
		}
		if (!collide) {
			this.getTile().remove(this);
			this.setTile(tile);
		}
	}

	public Tile getTile(Direction d) {
		Direction d2 = d;
		if (d.ordinal() < 4) { // if the direction is not absolute
			if (d == Direction.LEFT) {
				d2 = d.get(((this.getOrientation().ordinal() + 1) % 4) + 4); // return WEST if the direction is NORTH
			} else if (d == Direction.RIGHT) {
				d2 = d.get(((this.getOrientation().ordinal() + 3) % 4) + 4);
			} else if (d == Direction.BACK) {
				d2 = d.get(((this.getOrientation().ordinal() + 2) % 4) + 4);
			} else if (d == Direction.FRONT) {
				d2 = this.getOrientation();
			}
		}
		int newX=this.getX();
		int newY=this.getY();

		if (d2 == Direction.NORTH) {
			if (this.getY() > 0) {
				return this.getWorld().getTile(this.getX(), this.getY() - 1);
			} else {
				return this.getWorld().getTile(this.getX(), this.getWorld().getHeight() - 1);
			}
		}
		if (d2 == Direction.SOUTH) {
			if (this.getY() != this.getWorld().getHeight() + 1) {
				return this.getWorld().getTile(this.getX(), this.getY() + 1);
			} else {
				return this.getWorld().getTile(this.getX(), 0);
			}
		}
		if (d2 == Direction.EAST) {
			if (this.getX() != this.getWorld().getWidth() - 1) {
				return this.getWorld().getTile(this.getX() + 1, this.getY());
			} else {
				return this.getWorld().getTile(0, this.getY());
			}
		}
		if (d2 == Direction.WEST) {
			if (this.getY() > 0) {
				return this.getWorld().getTile(this.getX() - 1, this.getY());
			} else {
				return this.getWorld().getTile(this.getWorld().getWidth() - 1, this.getY());
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

	// Condition

	public boolean key(String key) {
		return getWorld().getModel().getBoolHashMap(key);
	}

	public boolean myDir(Direction d) {
		if (this.orientation == d) {
			return true;
		} else {
			return false;
		}
	}

	public boolean cell(Direction d, Kind e, int distance) {
		if (distance == 0) {
			return false;
		}
		int nbTile = 1 + (distance-1) * 2;
		int n = 0;
		int worldWidth = this.getWorld().getWidth();
		int worldHeight = this.getWorld().getHeight();
		Direction d2 = d;
		boolean res = false;
		if (d.ordinal() < 4) { // if the direction is not absolute
			if (d == Direction.LEFT) {
				d2 = d.get(((this.getOrientation().ordinal() + 1) % 4) + 4); // return WEST if the direction is NORTH
			} else if (d == Direction.RIGHT) {
				d2 = d.get(((this.getOrientation().ordinal() + 3) % 4) + 4);
			} else if (d == Direction.BACK) {
				d2 = d.get(((this.getOrientation().ordinal() + 2) % 4) + 4);
			} else if (d == Direction.FRONT) {
				d2 = this.getOrientation();
			}
		}
		if (d2 == Direction.NORTH) {
			int i = this.getX() - distance + 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			int j = this.getY() - 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			while (n < nbTile && !res) {
				Tile tile = this.getWorld().getTile(i, j);
				if (!tile.isEmpty()) {
					for (int k = 0; k < tile.nbEntity(); k++) {
						if (tile.getEntity(k).kind == e) {
							res = true;
						}
					}
				}
				if (n < Math.abs(nbTile/2)) {
					j--;
					if (j < 0) {
						j = worldHeight + (j % worldHeight);
					}
				} else {
					j++;
					if (j >= worldHeight) {
						j =	-(j % worldHeight);
					}
				}
				i++;
				if (i >= worldWidth) {
					i =	-(i % worldWidth);
				}
				n++;
			}
		}
		if (d2 == Direction.SOUTH) {
			
			int i = this.getX() - distance + 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			
			int j = this.getY() + 1;
			if (j >= worldHeight) {
				j =	-(j % worldHeight);
			}
			
			while (n < nbTile && !res) {
				Tile tile = this.getWorld().getTile(i, j);
				if (!tile.isEmpty()) {
					for (int k = 0; k < tile.nbEntity(); k++) {
						if (tile.getEntity(k).kind == e) {
							res = true;
						}
					}
				}
				if (n < Math.abs(nbTile/2)) {
					j++;
					if (j >= worldHeight) {
						j =	-(j % worldHeight);
					}
				} else {
					j--;
					if (j < 0) {
						j = worldHeight + (j % worldHeight);
					}
				}
				i++;
				if (i >= worldWidth) {
					i =	-(i % worldWidth);
				}
				n++;
			}
		}
		
		if (d2 == Direction.EAST) {
			
			int i = this.getX() + 1;
			if (i >= worldWidth) {
				i =	-(i % worldWidth);
			}
			
			int j = this.getY() - distance + 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			
			while (n < nbTile && !res) {
				Tile tile = this.getWorld().getTile(i, j);
				if (!tile.isEmpty()) {
					for (int k = 0; k < tile.nbEntity(); k++) {
						if (tile.getEntity(k).kind == e) {
							res = true;
						}
					}
				}
				if (n < Math.abs(nbTile/2)) {
					i++;
					if (i < 0) {
						i = worldWidth + (i % worldWidth);
					}
				} else {
					i--;
					if (i >= worldWidth) {
						i =	-(i % worldWidth);
					}
				}
				j++;
				if (j >= worldHeight) {
					j =	-(j % worldHeight);
				}
				n++;
			}
		}
		
		if (d2 == Direction.WEST) {
			
			int i = this.getX() - 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			
			int j = this.getY() - distance + 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			
			while (n < nbTile && !res) {
				Tile tile = this.getWorld().getTile(i, j);
				if (!tile.isEmpty()) {
					for (int k = 0; k < tile.nbEntity(); k++) {
						if (tile.getEntity(k).kind == e) {
							res = true;
						}
					}
				}
				if (n < Math.abs(nbTile/2)) {
					i--;
					if (i >= worldWidth) {
						i =	-(i % worldWidth);
					}
				} else {
					i++;
					if (i < 0) {
						i = worldWidth + (i % worldWidth);
					}
				}
				j++;
				if (j >= worldHeight) {
					j =	-(j % worldHeight);
				}
				n++;
			}
		}
		return res;
	}

	public boolean closest(Kind e, Direction d) {
		ArrayList<Entity> entities = this.getWorld().getEntities();
		int k = 0;
		boolean isPresent = false;
		while (k < entities.size() && !isPresent) {
			Entity entity = entities.get(k);
			if (entity != this && entity.kind == e ) {
				isPresent = true;
			}
			k++;
		}
		if (!isPresent) {
			return isPresent;
		}
		
		boolean resN = false, resS = false, resE = false, resW = false;
		int i = 1;
		while (!resN && !resS && !resE && !resW ) {
			if (!resN) {
				resN = cell(Direction.NORTH, e, i);
			}
			if (!resS) {
				resS = cell(Direction.SOUTH, e, i);
			}
			if (!resE) {
				resE = cell(Direction.EAST, e, i);
			}
			if (!resW) {
				resW = cell(Direction.WEST, e, i);
			}
			i++;
		}
		if (d == Direction.NORTH && resN) {
			return resN;
		}
		else if (d == Direction.SOUTH && resS) {
			return resS;
		}
		else if (d == Direction.EAST && resE) {
			return resE;
		}
		else if (d == Direction.WEST && resW) {
			return resW;
		}
		return false;
	}

	public boolean gotStuff() {
		return false;
	}

	public void patient() {
		this.actionHandler.patient();
	}

	public void wizz(Direction direction) {
		this.actionHandler.wizz(direction);
	}

	public void pop(Direction direction) {
		this.actionHandler.pop(direction);
	}

	public void jump(Direction direction) {
		this.actionHandler.jump(direction);
		
	}

	public void protect(Direction direction) {
		this.actionHandler.protect(direction);
		
	}

	public void pick(Direction direction) {
		this.actionHandler.pick(direction);
	}

	public void cast(Direction direction) {
		this.actionHandler.cast(direction);
	}

	public void store() {
		this.actionHandler.store();
	}

	public void get() {
		this.actionHandler.get();
	}

	public void kamikaze() {
		this.actionHandler.kamikaze();
	}

	public void egg() {
		this.actionHandler.egg();
	}
	
	public void setActionTimer(long totalTimer) {
		this.totalTimeAction = totalTimer;
	}
	
	public void hasViewport(boolean bool) {
		this.hasViewport = bool;
	}
	
	public boolean getHasViewport() {
		return this.hasViewport;
	}
	
	public Kind getKind() {
		return this.kind;
	}
	
	public boolean getCollidable() {
		return this.collidable;
	}
	
	public void updateCollidable(boolean collidable) {
		this.collidable = collidable;
	}
	
	public Tile getTile() {
		return tile;
	}

	private void setTile(Tile tile) {
		this.tile = tile;
		tile.add(this);
	}
	
	public void removeTile() {
		this.tile = null;
	}
	
	public boolean getIsVisible() {
		return this.isVisible;
	}
	
	public void updateIsVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}
}
