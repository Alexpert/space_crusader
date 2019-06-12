package game.main.model.action_handler;

import java.util.ArrayList;

import game.main.model.*;
import game.main.model.entities.*;

public class DroppedItemActionHandler extends AbstractActionHandler{
	
	public DroppedItemActionHandler(Entity e) {
		this.entity = e;
	}

	@Override
	public void patient() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wizz(Direction d) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pop(Direction d) {
		ArrayList<Entity> entities = this.entity.getTile().getEntities();
		
		System.out.println("DroppedItem Pops");
		
		int i = 0;
		while (i < entities.size() && entities.get(i).getKind() != Kind.PLAYER)
			i++;
			
		if (i != entities.size())
			if (((Player) entities.get(i)).addItem(((DroppedItem) this.entity).getItem()))
				((DroppedItem) this.entity).die();
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
