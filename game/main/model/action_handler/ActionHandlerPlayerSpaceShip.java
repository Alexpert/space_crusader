package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Item;
import game.main.model.Tile;
import game.main.model.entities.DroppedBomb;
import game.main.model.entities.DroppedItem;
import game.main.model.entities.Player;
import game.main.model.items.Bomb;

public class ActionHandlerPlayerSpaceShip extends AbstractActionHandler {

	public ActionHandlerPlayerSpaceShip(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void patient() {
		this.entity.setActionTimer(100);
	}
	
	@Override
	public void wizz(Direction d) {
		if(this.entity instanceof Player) {
			Player p = (Player) this.entity;
			p.incrementSelectedItem();
		}
		System.out.println("wizz direction"+d+"");

	}

	@Override
	public void pop(Direction d) {
		System.out.println("pop direction"+d+"");
		Item i;
		if(this.entity instanceof Player) {
			Player p = (Player) this.entity;
			i = p.getSelectedItem();
			Tile tile = this.entity.getTile(Direction.FRONT);
			if(tile.isEmpty()) {
				new DroppedItem(tile, i);
				((Player) this.entity).getInventory().remove(i);
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
