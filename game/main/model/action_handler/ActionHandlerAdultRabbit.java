package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.entities.AdultRabbit;
import game.main.model.entities.Laser;

public class ActionHandlerAdultRabbit extends AbstractActionHandler{

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
		// TODO move faster
		
	}

	@Override
	public void pop(Direction d) {
		this.entity.setActionTimer(5000);
		Tile tile = this.entity.getTile(d);
		new Laser(tile);
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
		if(t.isEmpty()) {
			return false;
		}
		else {
			for(int i = 0; i < t.nbEntity(); i ++) {
				t.getEntity(i).takeDamage(1);;
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
		if(this.entity.getHealth() > 0) {
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
