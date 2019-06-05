package game.main.model;

public class PlayerActionHandler extends AbstractActionHandler {

	Entity entity;
	
	PlayerActionHandler(Entity e) {
		this.entity = e;
	}
	@Override
	public void wizz(char direction) {
		System.out.println("wizz direction"+direction+"");

	}

	@Override
	public void pop(char direction) {
		System.out.println("pop direction"+direction+"");

	}

	@Override
	public boolean move(char direction) {
		if(direction == 'E') {
			this.entity.setX(this.entity.getX() + 32);
		}
		else if(direction == 'O') {
			this.entity.setX(this.entity.getX() - 32);
		}
		else if(direction == 'N') {
			this.entity.setY(this.entity.getY() - 32);
		}
		else if(direction == 'S') {
			this.entity.setY(this.entity.getY() + 32);
		}
		return false;
	}

	@Override
	public boolean jump(char direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean turn(char direction) {
		if((direction == 'E') || (direction == 'N') || (direction == 'O') || (direction == 'S')) {
			this.entity.setOrientation(direction);
			return true;
		}
		else if((direction == 'F') || (direction == 'B') || (direction == 'L') || (direction == 'R')) {
			this.entity.setOrientation(direction);
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public boolean hit(char direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean protect(char direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(char direction) {
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
