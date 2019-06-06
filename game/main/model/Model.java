package game.main.model;

import edu.ricm3.game.GameModel;

public class Model extends GameModel{

	World currentWorld;
	
	public Model() {
		this.currentWorld = new World(20, 20);
		this.currentWorld.add(new Player(0, 0, 10, Direction.NORTH, true, currentWorld));
		this.currentWorld.add(new Player(0, 3, 10, Direction.NORTH, true, currentWorld));
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
