package game.main.model;

public abstract class AbstractActionHandler {

	//public abstract void wait();
	
	public abstract void wizz(char direction);
	
	public abstract void pop(char direction);
	
	public abstract boolean move(char direction);
	
	public abstract boolean jump(char direction);
	
	public abstract boolean turn(char direction);
	
	public abstract boolean hit(char direction);
	
	public abstract boolean protect(char direction);
	
	public abstract boolean pick(char direction);
	
	//public abstract boolean throw(char direction);
	
	public abstract boolean store();
	
	public abstract boolean get();
	
	public abstract boolean power();
	
	public abstract boolean kamikaze();
	
	public abstract boolean egg();
}
