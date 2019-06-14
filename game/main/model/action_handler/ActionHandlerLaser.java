package game.main.model.action_handler;

import game.main.model.Action;
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
		this.entity.setActionTimer(1000);
		System.out.println("oui");
		System.out.println(this.entity.getX());
		System.out.println(this.entity.getY());
		Tile tile = this.entity.getTile();
		int i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(1);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.NORTH);
		System.out.println("nonn");
		System.out.println(tile.getX());
		System.out.println(tile.getY());
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(1);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.SOUTH);
		System.out.println("nons");
		System.out.println(tile.getX());
		System.out.println(tile.getY());
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(1);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.EAST);
		System.out.println("none");
		System.out.println(tile.getX());
		System.out.println(tile.getY());
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(5);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.WEST);
		System.out.println("nonw");
		System.out.println(tile.getX());
		System.out.println(tile.getY());
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(1);
			}
			i++;
		}
	}
	@Override
	public boolean move() {
		this.entity.setActionTimer(2000);
		Direction d = this.entity.getOrientation();
		int newX = this.entity.getX();
		int newY = this.entity.getY();
		if(d == Direction.EAST) {
			if(!this.entity.getTile(d).isCollidable()) {
				newX++;
			}
			else {
				this.entity.setAction(Action.PATIENT);
			}
		}
		else if(d == Direction.WEST) {
			if(!this.entity.getTile(d).isCollidable()) {
				newX--;
			}
			else {
				this.entity.setAction(Action.PATIENT);
			}
		}
		else if(d == Direction.NORTH) {
			if(!this.entity.getTile(d).isCollidable()) {
				newY--;
			}
			else {
				this.entity.setAction(Action.PATIENT);
			}
		}
		else if(d == Direction.SOUTH) {
			if(!this.entity.getTile(d).isCollidable()) {
				newY++;
			}
			else {
				this.entity.setAction(Action.PATIENT);
			}
		}
		this.entity.changeActionAnimation(Action.MOVE, d);
		
		if(newX >= this.entity.getWorld().getWidth()) {		//controlling the torus property
			newX -= this.entity.getWorld().getWidth();
		}
		if(newX < 0) {
			newX += this.entity.getWorld().getWidth();
		}
		if(newY >= this.entity.getWorld().getHeight()) {
			newY -= this.entity.getWorld().getHeight();
		}
		if(newY < 0) {
			newY += this.entity.getWorld().getHeight();
		}
		
		this.entity.moveToTile(newX, newY);
		
		return true;
	}
	@Override
	public boolean move(Direction d) {
		this.entity.turn(d);
		this.move();
		return false;

	}
	
	@Override
	public boolean jump(Direction d) {
		return true;
	}
	
	@Override
	public boolean hit(Direction d) {
		this.entity.setActionTimer(500);
		Tile tile = this.entity.getTile();
		int i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(1);
			}
			i++;
		}
		tile = this.entity.getTile(Direction.FRONT);
		i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.takeDamage(1);
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
