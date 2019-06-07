package game.main.model;

import game.main.view.RabbitPainter;

public class Rabbit extends Entity {
	
	
	Rabbit(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.MONSTER);
		this.setIPainter(new RabbitPainter(this));
	}

}
