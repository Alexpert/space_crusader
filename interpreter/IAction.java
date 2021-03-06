package interpreter;

import game.main.model.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public abstract class IAction {
	
	IAction(){}
	abstract boolean exec(Entity e);
	
	public String toString() {
		return this.getClass().toString();
	}
	
	
	public static class Wait extends IAction {
		public Wait(){}
		
		boolean exec(Entity e){
			e.patient();
			return true;
		}
	}
	
	public static class Wizz extends IAction {
		private Direction direction;
		
		public Wizz(){
			this.setDirection(Direction.FRONT);
		}
		
		public boolean exec(Entity e){
			e.wizz(Direction.FRONT);
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Pop extends IAction {
		private Direction direction;
		
		public Pop(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.pop(Direction.FRONT);
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Move extends IAction {
		private Direction direction ;
		
		public Move(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.move(this.direction) ;
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Jump extends IAction {
		private Direction direction ;
		
		public Jump(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.jump(this.direction) ;
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Turn extends IAction {
		private Direction direction ;

		public Turn(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.turn(this.direction) ;
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Hit extends IAction {
		private Direction direction ;
		private Integer power;
		
		public Hit(Direction direction, Integer power){
			this.direction = direction ;
			this.setPower(power);
		}
		public Hit(Direction direction){
			this.direction = direction ;
			this.setPower(1) ; // valeur par défaut
		}
		public Hit(){
			this.direction = Direction.FRONT; // Front par défaut
			this.setPower(1) ; // puissance par défaut
		}
		
		public boolean exec(Entity e){
			e.hit(this.direction);
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
		public Integer getPower() {
			return power;
		}
		public void setPower(Integer power) {
			this.power = power;
		}
	}
	
	public static class Protect extends IAction {
		private Direction direction ;

		public Protect(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.protect(this.direction) ;
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Pick extends IAction {
		private Direction direction ;

		public Pick(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.pick(this.direction) ;
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Throw extends IAction {
		private Direction direction ;

		public Throw(){
			this.direction = Direction.FRONT;
		}
		
		public boolean exec(Entity e){
			e.cast(this.direction);
			return true;
		}

		public Direction getDirection() {
			return direction;
		}

		public void setDirection(Direction direction) {
			this.direction = direction;
		}
	}
	
	public static class Store extends IAction {
		public Store() {}
		
		public boolean exec(Entity e) {
			e.store();
			return true;
		}
	}
	
	public static class Get extends IAction {
		public Get() {}
		
		public boolean exec(Entity e) {
			e.get();
			return true;
		}
	}
	
	public static class Power extends IAction {
		public Power() {}
		
		public boolean exec(Entity e) {
			e.getHealth();
			return true;
		}
	}
	
	public static class Kamikaze extends IAction {
		public Kamikaze() {}
		
		public boolean exec(Entity e) {
			e.kamikaze();
			return true;
		}
	}
	
	public static class Egg extends IAction {
		public Egg() {}
		
		public boolean exec(Entity e) {
			e.egg();
			return true;
		}
	}
	
}
