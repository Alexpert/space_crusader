package game.main.model.action_handler;

import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.entities.Flower;
import game.main.model.entities.Rock;

public class ActionHandlerRock extends AbstractActionHandler {
	
	public ActionHandlerRock(Entity e) {
		this.entity = e;
	}

	@Override
	public void patient() {
		Random random = new Random();
		this.entity.setActionTimer(random.nextInt(100000) + 150000);
	}

	@Override
	public void wizz(Direction d) {
		Random random = new Random();
		int i = random.nextInt(3);
		Tile oldTile = this.entity.getTile();
		Tile tile;
		if(i == 0) {
			tile = this.entity.getTile(Direction.NORTH);
		}
		else if(i == 1) {
			tile = this.entity.getTile(Direction.EAST);
		}
		else if(i == 2) {
			tile = this.entity.getTile(Direction.SOUTH);
		}
		else {
			tile = this.entity.getTile(Direction.WEST);
		}
		if(tile.isEmpty()) {
			oldTile.remove(this.entity);
			this.entity.setTile(tile);
		}

	}

	@Override
	public void pop(Direction d) {
		Boolean bool = this.entity.getIsVisible();
		if(bool == true) {
			this.entity.updateIsVisible(false);
		}
		else {
			this.entity.updateIsVisible(true);
		}

	}

	@Override
	public boolean jump(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean protect(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cast(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean kamikaze() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg() {
		if(this.entity.getIsVisible()) {
			Tile tile = this.entity.getTile(Direction.NORTH);
			int i = 0;
			boolean collide = false;
			while(i < tile.nbEntity() && !collide) {
				if(tile.getEntity(i).getCollidable() || tile.getEntity(i) instanceof Flower) {
					collide = true;
				}
				i++;
			}
			if(!collide) {
				new Rock(tile);
				return true;
			}
			i = 0;
			collide = false;
			tile = this.entity.getTile(Direction.EAST);
			while(i < tile.nbEntity() && !collide) {
				if(tile.getEntity(i).getCollidable() || tile.getEntity(i) instanceof Flower) {
					collide = true;
				}
				i++;
			}
			if(!collide) {
				new Rock(tile);
				return true;
			}
			i = 0;
			collide = false;
			tile = this.entity.getTile(Direction.SOUTH);
			while(i < tile.nbEntity() && !collide) {
				if(tile.getEntity(i).getCollidable() || tile.getEntity(i) instanceof Flower) {
					collide = true;
				}
				i++;
			}
			if(!collide) {
				new Rock(tile);
				return true;
			}
			i = 0;
			collide = false;
			tile = this.entity.getTile(Direction.WEST);
			while(i < tile.nbEntity() && !collide) {
				if(tile.getEntity(i).getCollidable() || tile.getEntity(i) instanceof Flower) {
					collide = true;
				}
				i++;
			}
			if(!collide) {
				new Rock(tile);
				return true;
			}
			return false;
		}
		return false;
	}

}
