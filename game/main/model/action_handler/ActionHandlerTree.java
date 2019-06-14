package game.main.model.action_handler;

import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.items.Apple;

public class ActionHandlerTree extends AbstractActionHandler {

	public ActionHandlerTree(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void patient() {
		Random random = new Random();
		this.entity.setActionTimer(random.nextInt(100000) + 190000);

	}

	@Override
	public void wizz(Direction d) {
		Random random = new Random();
		int i = random.nextInt(3);
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
			this.entity.setTile(tile);
		}
	}

	@Override
	public void pop(Direction d) {
		Apple a = new Apple();
		Tile tile = this.entity.getTile(Direction.EAST);
		if(tile.isEmpty()) {
			a.dropAtTile(tile);
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
		// TODO Auto-generated method stub
		return false;
	}

}
