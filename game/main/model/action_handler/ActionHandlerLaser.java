package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.entities.Laser;
import game.main.model.Tile;

public class ActionHandlerLaser extends AbstractActionHandler {
	
	public ActionHandlerLaser(Entity e) {
		this.entity = e;
	}

	@Override
	public void patient() {
		return;
	}

	@Override
	public void wizz(Direction d) {
		this.entity.setActionTimer(100);
		Tile tile = this.entity.getTile();
		int i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(-5);
			}
			i++;
		}		
	}

	@Override
	public void pop(Direction d) {
		System.out.println("oui");
		this.entity.setActionTimer(500);
		Tile tile = this.entity.getTile(Direction.NORTH);
		int i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(10);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.SOUTH);
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(10);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.EAST);
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(10);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.WEST);
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(10);
			}
			i++;
		}
		this.kamikaze();
	}

	@Override
	public boolean jump(Direction d) {
		return true;
	}

	@Override
	public boolean hit(Direction d) {
		this.entity.setActionTimer(300);
		Tile tile = this.entity.getTile();
		int i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(-5);
			}
			i++;
		}
		return false;
	}

	@Override
	public boolean protect(Direction d) {
		return true;
	}

	@Override
	public boolean pick(Direction d) {
		return true;
	}

	@Override
	public boolean cast(Direction d) {
		return true;
	}

	@Override
	public boolean store() {
		return true;
	}

	@Override
	public boolean get() {
		return true;
	}

	@Override
	public boolean power() {
		return true;
	}

	@Override
	public boolean kamikaze() {
		this.entity.setActionTimer(300);
		this.entity.getTile().remove(this.entity);
		return true;
	}

	@Override
	public boolean egg() {
		return true;
	}

}
