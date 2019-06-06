package game.main.model;

public class ActionHandlerAdultRabbit extends AbstractActionHandler{

	@Override
	public void patient() {
		// TODO Auto-generated method stub
		return;
	}

	@Override
	public void wizz(Direction d) {
		// TODO move faster
		
	}

	@Override
	public void pop(Direction d) {
		// TODO laser
		
	}

	@Override
	public boolean jump(Direction d) {
		// TODO don't jump
		return false;
	}

	@Override
	public boolean hit(Direction d) {
		Tile t = this.entity.getTile(d);
		if(t.isEmpty()) {
			return false;
		}
		else {
			for(int i = 0; i < t.nbEntity(); i ++) {
				t.getEntity(i).addHealth(-10);
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
		if(this.entity.getHealth() > 0) {
			this.entity.addHealth(1);
			return true;
		}
		return false;
	}

	@Override
	public boolean kamikaze() {
		this.entity.addHealth(0);
		return true;
	}

	@Override
	public boolean egg() {
		// TODO don't egg
		return false;
	}

}
