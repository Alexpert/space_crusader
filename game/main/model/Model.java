package game.main.model;

import edu.ricm3.game.GameModel;

public class Model extends GameModel{

	World currentWorld;
	
	public Model() {
		this.currentWorld = new World(20, 20);
		Player p = new Player(0, 0, 10, Direction.NORTH, true, currentWorld);
		this.currentWorld.add(p);
		//p.move();
		//p.move(Direction.WEST);
		this.currentWorld.add(new Rabbit(3, 1, 2, Direction.SOUTH, true, currentWorld));
		this.currentWorld.add(new AdultRabbit(4, 4, 20, Direction.WEST, true, currentWorld));
	}
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		
	}
	
	public World getCurrentWorld() {
		return this.currentWorld;
	}

}
