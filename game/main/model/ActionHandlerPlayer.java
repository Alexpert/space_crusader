package game.main.model;

public class ActionHandlerPlayer extends AbstractActionHandler {
	
	public ActionHandlerPlayer(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void wizz(Direction d) {
		System.out.println("wizz direction"+d+"");

	}

	@Override
	public void pop(Direction d) {
		System.out.println("pop direction"+d+"");

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
			this.entity.setHealth(this.entity.getHealth() + 1);
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
		// TODO Auto-generated method stub
		return false;
	}

}
