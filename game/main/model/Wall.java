package game.main.model;

public class Wall extends Entity {
	
	Wall(int x, int y, World world) {
		super(x, y, Integer.MAX_VALUE, Direction.NORTH, false, world, Kind.OBSTACLE);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void step() {
		// TODO Auto-generated method stub
		
	}
}
