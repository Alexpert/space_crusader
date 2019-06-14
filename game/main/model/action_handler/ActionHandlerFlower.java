package game.main.model.action_handler;

import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.entities.Rock;

public class ActionHandlerFlower extends AbstractActionHandler {

	public ActionHandlerFlower(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void patient() {
		Random random = new Random();
		this.entity.setActionTimer(random.nextInt(10000) + 80000);

	}

	@Override
	public void wizz(Direction d) {
		this.entity.egg();

	}

	@Override
	public void pop(Direction d) {
		Tile tile = this.entity.getTile(Direction.WEST);
		if(tile.isEmpty()) {
			Rock rock = new Rock(tile);
			rock.setTile(tile);
		}
		else {
			tile = this.entity.getTile(Direction.EAST);
			if(tile.isEmpty()) {
				Rock rock = new Rock(tile);
				rock.setTile(tile);
			}
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
