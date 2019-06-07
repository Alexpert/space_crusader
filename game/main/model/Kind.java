package game.main.model;

public enum Kind {
	MONSTER,DANGER,GATE,JUMP_ELEMENT,MISSILE,OBSTACLE,ITEM,TEAM,VOID,PLAYER,ANYTHING;
	
	public Kind get(int i) {
		if (i == 0) {
			return Kind.MONSTER;
		}
		if (i == 1) {
			return Kind.DANGER;
		}
		if (i == 2) {
			return Kind.GATE;
		}
		if (i == 3) {
			return Kind.JUMP_ELEMENT;
		}
		if (i == 4) {
			return Kind.MISSILE;
		}
		if (i == 5) {
			return Kind.OBSTACLE;
		}
		if (i == 6) {
			return Kind.ITEM;
		}
		if (i == 7) {
			return Kind.TEAM;
		}
		if (i == 8) {
			return Kind.PLAYER;
		}
		if (i == 9) {
			return Kind.ANYTHING;
		}
		return Kind.VOID;
	}
}
