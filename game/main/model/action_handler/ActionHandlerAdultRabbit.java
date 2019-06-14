package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.entities.AdultRabbit;
import game.main.model.entities.Laser;

public class ActionHandlerAdultRabbit extends AbstractActionHandler {

	public ActionHandlerAdultRabbit(Entity e) {
		this.entity = e;
	}

	@Override
	public void patient() {
		this.entity.setActionTimer(500);
		return;
	}

	@Override
	public void wizz(Direction d) {
		boolean res = false;
		int nbTile = 3;
		int n = 0;
		int x = this.entity.getX(), y = this.entity.getY();
		int worldWidth = this.entity.getWorld().getWidth();
		int worldHeight = this.entity.getWorld().getHeight();
		Direction d2 = d;
		if (d.ordinal() < 4) { // if the direction is not absolute
			if (d == Direction.LEFT) {
				d2 = d.get(((this.entity.getOrientation().ordinal() + 1) % 4) + 4); // return WEST if the direction is
																					// NORTH
			} else if (d == Direction.RIGHT) {
				d2 = d.get(((this.entity.getOrientation().ordinal() + 3) % 4) + 4);
			} else if (d == Direction.BACK) {
				d2 = d.get(((this.entity.getOrientation().ordinal() + 2) % 4) + 4);
			} else if (d == Direction.FRONT) {
				d2 = this.entity.getOrientation();
			}
		}
		if (d2 == Direction.NORTH) {
			int i = this.entity.getX() - 3;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}
			int j = this.entity.getY() - 1;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}
			while (n < nbTile && !res) {
				Tile tile = this.entity.getWorld().getTile(i, j);
				if (!tile.isCollidable()) {
					res = true;
					x = i;
					y = j;
				}
				if (n < Math.abs(nbTile / 2)) {
					j--;
					if (j < 0) {
						j = worldHeight + (j % worldHeight);
					}
				} else {
					j++;
					if (j >= worldHeight) {
						j = -(j % worldHeight);
					}
				}
				i++;
				if (i >= worldWidth) {
					i = -(i % worldWidth);
				}
				n++;
			}
		}
		if (d2 == Direction.SOUTH) {
			int i = this.entity.getX() - 3;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}

			int j = this.entity.getY() + 1;
			if (j >= worldHeight) {
				j = -(j % worldHeight);
			}

			while (n < nbTile && !res) {
				Tile tile = this.entity.getWorld().getTile(i, j);
				if (!tile.isCollidable()) {
					res = true;
					x = i;
					y = j;
				}
				if (n < Math.abs(nbTile / 2)) {
					j++;
					if (j >= worldHeight) {
						j = -(j % worldHeight);
					}
				} else {
					j--;
					if (j < 0) {
						j = worldHeight + (j % worldHeight);
					}
				}
				i++;
				if (i >= worldWidth) {
					i = -(i % worldWidth);
				}
				n++;
			}
		}

		if (d2 == Direction.EAST) {
			int i = this.entity.getX() + 1;
			if (i >= worldWidth) {
				i = -(i % worldWidth);
			}

			int j = this.entity.getY() - 3;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}

			while (n < nbTile && !res) {
				Tile tile = this.entity.getWorld().getTile(i, j);
				if (!tile.isCollidable()) {
					res = true;
					x = i;
					y = j;
				}
				if (n < Math.abs(nbTile / 2)) {
					i++;
					if (i < 0) {
						i = worldWidth + (i % worldWidth);
					}
				} else {
					i--;
					if (i >= worldWidth) {
						i = -(i % worldWidth);
					}
				}
				j++;
				if (j >= worldHeight) {
					j = -(j % worldHeight);
				}
				n++;
			}
		}

		if (d2 == Direction.WEST) {
			int i = this.entity.getX() - 1;
			if (i < 0) {
				i = worldWidth + (i % worldWidth);
			}

			int j = this.entity.getY() - 3;
			if (j < 0) {
				j = worldHeight + (j % worldHeight);
			}

			while (n < nbTile && !res) {
				Tile tile = this.entity.getWorld().getTile(i, j);
				if (!tile.isCollidable()) {
					res = true;
					x = i;
					y = j;
				}
				if (n < Math.abs(nbTile / 2)) {
					i--;
					if (i >= worldWidth) {
						i = -(i % worldWidth);
					}
				} else {
					i++;
					if (i < 0) {
						i = worldWidth + (i % worldWidth);
					}
				}
				j++;
				if (j >= worldHeight) {
					j = -(j % worldHeight);
				}
				n++;
			}
		}
		if (res) {
			this.entity.moveToTile(x, y);
		}
		return;
	}

	@Override
	public void pop(Direction d) {
		this.entity.setActionTimer(2500);
		Tile tile = this.entity.getTile(d);
		new Laser(tile, entity.getOrientation());
		return;
	}

	@Override
	public boolean jump(Direction d) {
		// TODO don't jump
		return false;
	}

	@Override
	public boolean hit(Direction d) {
		this.entity.setActionTimer(1000);
		Tile t = this.entity.getTile(d);
		if (t.isEmpty()) {
			return false;
		} else {
			for (int i = 0; i < t.nbEntity(); i++) {
				t.getEntity(i).takeDamage(5);
			}
			return true;
		}
	}

	@Override
	public boolean protect(Direction d) {
		// TODO don't protect
		return false;
	}

	@Override
	public boolean pick(Direction d) {
		// TODO don't pick
		return false;
	}

	@Override
	public boolean cast(Direction d) {
		// TODO don't cast
		return false;
	}

	@Override
	public boolean store() {
		// TODO don't store
		return false;
	}

	@Override
	public boolean get() {
		// TODO don't get
		return false;
	}

	@Override
	public boolean power() {
		this.entity.setActionTimer(100);
		if (this.entity.getHealth() > 0) {
			this.entity.takeDamage(-1);
			return true;
		}
		return false;
	}

	@Override
	public boolean kamikaze() {
		this.entity.setActionTimer(100);
		this.entity.getTile().remove(this.entity);
		return true;
	}

	@Override
	public boolean egg() {
		// TODO don't egg
		return false;
	}

}
