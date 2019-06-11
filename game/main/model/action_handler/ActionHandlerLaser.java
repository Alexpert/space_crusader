package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;

public class ActionHandlerLaser extends AbstractActionHandler {

	@Override
	public void patient() {
		return;
	}

	@Override
	public void wizz(Direction d) {
		// TODO fous le feu
		
	}

	@Override
	public void pop(Direction d) {
		// TODO boom
		
	}

	@Override
	public boolean jump(Direction d) {
		return true;
	}

	@Override
	public boolean hit(Direction d) {
		Tile tile = this.entity.getTile();
		int i = 0;
		while (i < tile.nbEntity()) {
			Entity entity = tile.getEntity(i);
			if (entity.getKind() == Kind.MONSTER || entity.getKind() == Kind.PLAYER) {
				entity.addHealth(-10);
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
		this.entity.addHealth(0);
		return true;
	}

	@Override
	public boolean egg() {
		return true;
	}

}
