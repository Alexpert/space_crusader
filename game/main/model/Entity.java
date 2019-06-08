package game.main.model;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.view.IPainter;
import interpreter.IAutomaton;

public abstract class Entity {

	private int health;
	private int maxHealth;
	private int x, y;
	private Direction orientation;
	private Kind kind;
	private boolean moveable;
	private AbstractActionHandler actionHandler;
	private World world;
	private IPainter painter;
	private IAutomaton automaton;
	private boolean hasViewport;
	private long currentTimeAction;
	private long totalTimeAction;
	private long beginTimeAction;
	private Action currentAction;

	protected Entity(int x, int y, int health, Direction d, boolean moveable, World world, Kind kind) {
		this.x = x;
		this.y = y;
		this.health = health;
		this.maxHealth = health;
		this.orientation = d;
		this.moveable = moveable;
		this.world = world;
		this.kind = kind;
		this.automaton = AutomatonProvider.getInstance().getAutomaton("Playable");
		this.hasViewport = false;
		System.out.println(this.automaton.getName());
		this.totalTimeAction=0;
		this.beginTimeAction=0;
	}

	public void setActionHandler(AbstractActionHandler ac) {
		this.actionHandler = ac;
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
		this.painter = ip;
	}

	public World getWorld() {
		return this.world;
	}
	
	public void moveToTile(int x, int y) {
		this.world.getTile(this.getX(), this.getY()).remove(this);
		this.world.getTile(x, y).add(this);
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
		return world.getModel().getBoolHashMap(key);
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
		int worldWidth = this.world.getWidth();
		int worldHeight = this.world.getHeight();
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
			int i = this.x - distance + 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			int j = this.y - 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			while (n < nbTile && !res) {
				Tile tile = this.world.getTile(i, j);
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
			
			int i = this.x - distance + 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			
			int j = this.y + 1;
			if (j >= worldHeight) {
				j =	-(j % worldHeight);
			}
			
			while (n < nbTile && !res) {
				Tile tile = this.world.getTile(i, j);
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
			
			int i = this.x + 1;
			if (i >= worldWidth) {
				i =	-(i % worldWidth);
			}
			
			int j = this.y - distance + 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			
			while (n < nbTile && !res) {
				Tile tile = this.world.getTile(i, j);
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
			
			int i = this.x - 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			
			int j = this.y - distance + 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			
			while (n < nbTile && !res) {
				Tile tile = this.world.getTile(i, j);
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
		ArrayList<Entity> entities = this.world.getEntities();
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

}
