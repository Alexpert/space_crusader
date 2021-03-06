package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Item;
import game.main.model.Tile;
import game.main.model.entities.DroppedBomb;
import game.main.model.entities.Player;
import game.main.model.items.Apple;
import game.main.model.items.Bomb;

public class ActionHandlerPlayer extends AbstractActionHandler {
	
	public ActionHandlerPlayer(Entity e) {
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
		//System.out.println("pop direction"+d+"");
		Item i;
		if(this.entity instanceof Player) {
			Player p = (Player) this.entity;
			i = p.getSelectedItem();
			if(i instanceof Bomb) {
				Bomb b = (Bomb) i;
				this.entity.setActionTimer(200);
				Tile t = this.entity.getTile(d);
				DroppedBomb bomb = new DroppedBomb(t,b);
				bomb.setBeginTimer(this.entity.getBeginTimer());
				p.removeItem(i);
			} else if (i instanceof Apple) {
				p.takeDamage(-5);
				p.removeItem(i);
				System.out.println("player at " + p.getHealth() + "hp");
			}
		}	
	}

	@Override
	public boolean jump(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override			// return false has nothing was on the tile
	public boolean hit(Direction d) {
		
		Tile t = this.entity.getTile(d);
		if(t.isEmpty()) {
			return false;
		}
		else {
			for(int i = 0; i < t.nbEntity(); i ++) {
				t.getEntity(i).takeDamage(5);
			}
			return true;
		}
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
		if(this.entity.getHealth() > 0) {
			this.entity.takeDamage(-1);
			return true;
		}
		return false;
	}

	@Override
	public boolean kamikaze() {
		this.entity.setActionTimer(300);
		this.entity.getTile().remove(this.entity);
		return true;
	}

	@Override
	public boolean egg() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean cast(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

}
