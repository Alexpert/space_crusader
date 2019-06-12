package game.main.model.action_handler;

import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.entities.AdultRabbit;
import game.main.model.entities.Rabbit;

public class ActionHandlerRabbit extends AbstractActionHandler {

	public ActionHandlerRabbit(Entity e) {
		this.entity = e;
	}

	@Override
	public boolean move(Direction d) {
		this.entity.setActionTimer(500);
		super.move(d);
		return true;
	}

	@Override
	public void patient() {
		Random random = new Random();
		this.entity.setActionTimer(random.nextInt(10000)+100);
	}
	
	
	@Override
	public void wizz(Direction d) {
		// TODO dig a hole and disappear
		this.entity.setActionTimer(1000);
		if (this.entity.getIsVisible()) {
			this.entity.updateIsVisible(false);
			this.entity.updateCollidable(false);
			this.entity.updateMoveable(false);
		} else {
			Tile tile = this.entity.getTile(Direction.SOUTH);
			int i = 0;
			boolean collide = false;
			while (i < tile.nbEntity() && !collide) {
				if (tile.getEntity(i).getCollidable()) {
					collide = true;
				}
				i++;
			}
			if (!collide) {
				this.entity.updateIsVisible(true);
				this.entity.updateCollidable(true);
				this.entity.updateMoveable(true);
			}
		}
	}

	@Override
	public void pop(Direction d) {
		Tile tile = this.entity.getTile();
		tile.remove(this.entity);
		new AdultRabbit(tile).setActionTimer(5000);;
		return;
	}

	@Override
	public boolean jump(Direction d) {
		// TODO
		return true;
	}

	@Override
	public boolean hit(Direction d) {
		// TODO don't hit
		return true;
	}

	@Override
	public boolean protect(Direction d) {
		// TODO don't protect
		return true;
	}

	@Override
	public boolean pick(Direction d) {
		// TODO don't pick
		return true;
	}

	@Override
	public boolean store() {
		// TODO don't store
		return true;
	}

	@Override
	public boolean get() {
		// TODO don't get
		return false;
	}

	@Override
	public  boolean power(){
		if(this.entity.getHealth() > 0) {
			this.entity.takeDamage(1);
			return true;
		}
		return false;
	}

	@Override
	public  boolean kamikaze(){
		this.entity.setHealth(0);
		return true;
	}

	@Override
	public boolean egg() {
		this.entity.setActionTimer(10000);
		if (this.entity.getIsVisible()) {
			Tile tile = this.entity.getTile(Direction.NORTH);
			int i = 0;
			boolean collide = false;
			while (i < tile.nbEntity() && !collide) {
				if (tile.getEntity(i).getCollidable()) {
					collide = true;
				}
				i++;
			}
			if (!collide) {
				new Rabbit(tile);
				return true;
			}
			tile = this.entity.getTile(Direction.SOUTH);
			i = 0;
			collide = false;
			while (i < tile.nbEntity() && !collide) {
				if (tile.getEntity(i).getCollidable()) {
					collide = true;
				}
				i++;
			}
			if (!collide) {
				new Rabbit(tile);
				return true;
			}
			tile = this.entity.getTile(Direction.EAST);
			i = 0;
			collide = false;
			while (i < tile.nbEntity() && !collide) {
				if (tile.getEntity(i).getCollidable()) {
					collide = true;
				}
				i++;
			}
			if (!collide) {
				new Rabbit(tile);
				return true;
			}
			tile = this.entity.getTile(Direction.WEST);
			i = 0;
			collide = false;
			while (i < tile.nbEntity() && !collide) {
				if (tile.getEntity(i).getCollidable()) {
					collide = true;
				}
				i++;
			}
			if (!collide) {
				new Rabbit(tile);
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean cast(Direction d) {
		// TODO don't cast
		return false;
	}
}
