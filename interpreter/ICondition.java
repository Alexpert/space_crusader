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
			this.operandL = operandL;
			this.operandR = operandR;
		}

		boolean eval(Entity e) {
			return this.operandL.eval(e) || this.operandR.eval(e);
		}
	}
	

	public class And extends ICondition {
		private ICondition operandL;
		private ICondition operandR;

		public And(ICondition operandL, ICondition operandR) {
			this.operandL = operandL;
			this.operandR = operandR;
		}

		boolean eval(Entity e) {
			return this.operandL.eval(e) && this.operandR.eval(e);
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
			this.key = key;
		}

		boolean eval(Entity e) {
			//return e.key(key);
			return true;
		}
	}

	public class MyDir extends ICondition {
		private Direction direction;

		public MyDir(Direction direction) {
			this.direction = direction;
		}

		boolean eval(Entity e) {
			return e.getOrientation() == this.direction;
		}
	}

	public class Cell extends ICondition {
		Direction direction;
		Kind kind;
		int distance;

		public Cell(Direction direction, Kind kind, int distance) {
			this.direction = direction;
			this.kind = kind;
			this.distance = distance;
		}

		public Cell(Direction direction, Kind kind) {
			this.direction = direction;
			this.kind = kind;
			this.distance = 1;
		}

		boolean eval(Entity e) {
			//return e.cell(this.kind, this.direction, this.distance);
			return true;
		}
		
		public void addDistance(int distance) {
			this.distance = distance;
		}
	}

	public class Closest extends ICondition {
		private Kind kind;
		private Direction direction;

		public Closest(Kind kind, Direction direction) {
			this.kind = kind;
			this.direction = direction;
		}

		boolean eval(Entity e) {
			//return e.closest(kind, direction);
			return true;
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
			//return e.gotStuff();
			return true;
		}
	}
	
	

}