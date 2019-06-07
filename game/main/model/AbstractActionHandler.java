package game.main.model;

public abstract class AbstractActionHandler {

	protected Entity entity;

	public abstract void patient();

	public abstract void wizz(Direction d);

	public abstract void pop(Direction d);

	public boolean move() {
		this.entity.setActionTimer(100);
		Direction d = this.entity.getOrientation();
		int newX = this.entity.getX();
		int newY = this.entity.getY();
		
		if(d == Direction.EAST) {
			newX++;
		}
		else if(d == Direction.WEST) {
			newX--;
		}
		else if(d == Direction.NORTH) {
			newY--;
		}
		else if(d == Direction.SOUTH) {
			newY++;
		}
		
		if(newX >= this.entity.getWorld().getWidth()) {		//controlling the torus property
			newX -= this.entity.getWorld().getWidth();
		}
		if(newX < 0) {
			newX += this.entity.getWorld().getWidth();
		}
		if(newY >= this.entity.getWorld().getHeight()) {
			newY -= this.entity.getWorld().getHeight();
		}
		if(newY < 0) {
			newY += this.entity.getWorld().getHeight();
		}
		
		this.entity.moveToTile(newX, newY);
		this.entity.setX(newX);
		this.entity.setY(newY);
		
		return false;
	}
	
	public boolean move(Direction d) {
		Direction lastDirection = this.entity.getOrientation();
		this.entity.turn(d);
		this.move();
		this.entity.turn(lastDirection);
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

	public abstract boolean cast(Direction d);

	public abstract boolean store();

	public abstract boolean get();

	public abstract boolean power();

	public abstract boolean kamikaze();

	public abstract boolean egg();
}
