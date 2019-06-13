package game.main.model.action_handler;

import game.main.model.*;
import game.main.model.entities.*;
import game.main.model.items.*;

public class ActionHandlerOldMan extends AbstractActionHandler {
	
	public ActionHandlerOldMan(Entity e) {
		this.entity = e;
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
		if(this.entity.getHealth() > 0 ) {
			this.entity.takeDamage(-(Integer.MAX_VALUE-this.entity.getHealth()));
			return true;
		}
		return false;
	}

	@Override
	public boolean kamikaze() {
		this.entity.setHealth(0);
		return true;
	}

	@Override
	public boolean egg() {
		return true;
	}

	@Override
	public void wizz(Direction d) {
		Tile tile = this.entity.getTile(Direction.RIGHT);
		Bomb bomb = new Bomb();
		tile.getWorld().getPlayer().addMoney(-bomb.getValue());
	}

	@Override
	public void pop(Direction d) {
		Tile tile = this.entity.getTile(Direction.FRONT);
		System.out.println(tile.getEntities().size());
		int i = tile.getEntities().size() - 1;
		
		while (i >= 0) {
			if (tile.getEntities().get(i).getKind() == Kind.ITEM) {
				int value = ((DroppedItem) tile.getEntities().get(i)).getItem().getValue();
				tile.getWorld().getPlayer().addMoney(value);
				tile.getEntities().remove(i);
			}
			i--;
		}
		
	}

	@Override
	public boolean jump(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(Direction d) {
		Tile tile = this.entity.getTile(Direction.RIGHT);
		Bomb bomb = new Bomb();
		bomb.dropAtTile(tile);
		return false;
	}

	@Override
	public void patient() {
		this.entity.setActionTimer(200);
	}


}
