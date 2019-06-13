package game.main.model.action_handler;

import game.main.model.*;
import game.main.model.entities.*;

public class ActionHandlerAnvil extends AbstractActionHandler {

	public ActionHandlerAnvil(Entity e) {
		this.entity = e;
	}

	@Override
	public void patient() {
		System.out.println("Anvil: PATIENT");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void wizz(Direction d) { // Suppression item LEFT RIGHT
		this.entity.getTile(Direction.LEFT).clear();
		this.entity.getTile(Direction.RIGHT).clear();
	}

	@Override
	public void pop(Direction d) { // Craft résult en FRONT
		System.out.println("Anvil: POP");
		DroppedItem entity1 = (DroppedItem) this.entity.getTile(Direction.LEFT).getEntity(0);
		DroppedItem entity2 = (DroppedItem) this.entity.getTile(Direction.RIGHT).getEntity(0);
		
		if (entity1 != null && entity2 != null) {
			new DroppedItem(this.entity.getTile(Direction.FRONT), Recipes.craft(entity1, entity2));
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
