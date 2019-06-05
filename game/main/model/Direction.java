package game.main.model;

public enum Direction {
	FRONT,LEFT,BACK,RIGHT,NORTH,WEST,SOUTH,EAST;
	
	public Direction get(int i) {
		if (i == 0) {
			return Direction.FRONT;
		}
		if (i == 1) {
			return Direction.LEFT;
		}
		if (i == 2) {
			return Direction.BACK;
		}
		if (i == 3) {
			return Direction.RIGHT;
		}
		if (i == 4) {
			return Direction.NORTH;
		}
		if (i == 5) {
			return Direction.WEST;
		}
		if (i == 6) {
			return Direction.SOUTH;
		}
		if (i == 7) {
			return Direction.EAST;
		}
		return Direction.FRONT;
	}
}
