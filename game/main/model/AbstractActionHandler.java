package game.main.model;

public abstract class AbstractActionHandler {

	Entity entity;

	// public abstract void wait();

	public abstract void wizz(Direction d);

	public abstract void pop(Direction d);

	public boolean move() {
		Direction d = this.entity.getOrientation();
		if(d == Direction.EAST) {
			this.entity.setX(this.entity.getX() + 32);
		}
		else if(d == Direction.WEST) {
			this.entity.setX(this.entity.getX() - 32);
		}
		else if(d == Direction.NORTH) {
			this.entity.setY(this.entity.getY() - 32);
		}
		else if(d == Direction.SOUTH) {
			this.entity.setY(this.entity.getY() + 32);
		}
		return false;
	}
	
	public boolean move(Direction d) {
		if(d == Direction.EAST) {
			this.entity.setX(this.entity.getX() + 32);
		}
		else if(d == Direction.WEST) {
			this.entity.setX(this.entity.getX() - 32);
		}
		else if(d == Direction.NORTH) {
			this.entity.setY(this.entity.getY() - 32);
		}
		else if(d == Direction.SOUTH) {
			this.entity.setY(this.entity.getY() + 32);
		}
		return false;
	}

	public abstract boolean jump(Direction d);

	public boolean turn(Direction d) {
		if (d.ordinal() >= 4 &&  d.ordinal() <= 7) {	//if the direction is absolute, it's directly set
			this.entity.setOrientation(d);
			return true;
		}
		else {
			if(d == Direction.LEFT) {
				this.entity.setOrientation( d.get(((this.entity.getOrientation().ordinal()+1)%4)+4) );	//return WEST if the direction is NORTH
				return true;
			}
			else if(d == Direction.RIGHT) {
				this.entity.setOrientation( d.get(((this.entity.getOrientation().ordinal()+3)%4)+4) );
				return true;
			}
			else if(d == Direction.BACK) {
				this.entity.setOrientation( d.get(((this.entity.getOrientation().ordinal()+2)%4)+4) );
				return true;
			}
		}
		return false;
	}

	public abstract boolean hit(Direction d);

	public abstract boolean protect(Direction d);

	public abstract boolean pick(Direction d);

	// public abstract boolean throw(Direction d);

	public abstract boolean store();

	public abstract boolean get();

	public abstract boolean power();

	public abstract boolean kamikaze();

	public abstract boolean egg();
}
