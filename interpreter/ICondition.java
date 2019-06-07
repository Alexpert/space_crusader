package interpreter;

import game.main.model.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ICondition {

	public ICondition() {
	}

	boolean eval(Entity e) {
		return true;
	} // à redéfinir dans chaque sous-classe
	

	public class Not extends ICondition {
		private ICondition operand;

		public Not(ICondition operand) {
			this.operand = operand;
		}

		boolean eval(Entity e) {
			return !this.operand.eval(e);
		}
	}
	

	public class Or extends ICondition {
		private ICondition operandL;
		private ICondition operandR;

		public Or(ICondition operandL, ICondition operandR) {
			this.setOperandL(operandL);
			this.setOperandR(operandR);
		}

		boolean eval(Entity e) {
			return this.getOperandL().eval(e) || this.getOperandR().eval(e);
		}

		public ICondition getOperandL() {
			return operandL;
		}

		private void setOperandL(ICondition operandL) {
			this.operandL = operandL;
		}

		public ICondition getOperandR() {
			return operandR;
		}

		private void setOperandR(ICondition operandR) {
			this.operandR = operandR;
		}
	}
	

	public class And extends ICondition {
		private ICondition operandL;
		private ICondition operandR;

		public And(ICondition operandL, ICondition operandR) {
			this.setOperandL(operandL);
			this.setOperandR(operandR);
		}

		boolean eval(Entity e) {
			return this.getOperandL().eval(e) && this.getOperandR().eval(e);
		}

		public ICondition getOperandL() {
			return operandL;
		}

		private void setOperandL(ICondition operandL) {
			this.operandL = operandL;
		}

		public ICondition getOperandR() {
			return operandR;
		}

		private void setOperandR(ICondition operandR) {
			this.operandR = operandR;
		}
	}

	public class True extends ICondition {
		public True() {
		}

		boolean eval(Entity e) {
			return true;
		}
	}

	public class Key extends ICondition {
		private String key;

		public Key(String key) {
			this.setKey(key);
		}

		boolean eval(Entity e) {
			return e.key(key);
		}

		public String getKey() {
			return key;
		}

		private void setKey(String key) {
			this.key = key;
		}
	}

	public class MyDir extends ICondition {
		private Direction direction;

		public MyDir(Direction direction) {
			this.setDirection(direction);
		}

		boolean eval(Entity e) {
			return e.getOrientation() == this.getDirection();
		}

		public Direction getDirection() {
			return direction;
		}

		private void setDirection(Direction direction) {
			this.direction = direction;
		}
	}

	public class Cell extends ICondition {
		private Direction direction;
		private Kind kind;
		private int distance;

		public Cell(Direction direction, Kind kind, int distance) {
			this.setDirection(direction);
			this.setKind(kind);
			this.setDistance(distance);
		}

		public Cell(Direction direction, Kind kind) {
			this.setDirection(direction);
			this.setKind(kind);
			this.setDistance(1);
		}

		boolean eval(Entity e) {
			return e.cell(this.direction, this.kind, this.distance);
		}
		
		public void addDistance(int distance) {
			this.setDistance(distance);
		}

		public Direction getDirection() {
			return direction;
		}

		private void setDirection(Direction direction) {
			this.direction = direction;
		}

		public Kind getKind() {
			return kind;
		}

		private void setKind(Kind kind) {
			this.kind = kind;
		}

		public int getDistance() {
			return distance;
		}

		private void setDistance(int distance) {
			this.distance = distance;
		}
	}

	public class Closest extends ICondition {
		private Kind kind;
		private Direction direction;

		public Closest(Kind kind, Direction direction) {
			this.setKind(kind);
			this.setDirection(direction);
		}

		boolean eval(Entity e) {
			return e.closest(kind, direction);
		}

		public Kind getKind() {
			return kind;
		}

		private void setKind(Kind kind) {
			this.kind = kind;
		}

		public Direction getDirection() {
			return direction;
		}

		private void setDirection(Direction direction) {
			this.direction = direction;
		}
	}

	public class GotPower extends ICondition {
		public GotPower() {
		}

		boolean eval(Entity e) {
			return e.getHealth() > 0;
		}
	}

	public class GotStuff extends ICondition {
		public GotStuff() {
		}

		boolean eval(Entity e) {
			return e.gotStuff();
		}
	}
	
	

}