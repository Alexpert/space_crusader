package game.main.model.entities;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.World;
import game.main.view.RabbitPainter;

public class Rabbit extends Entity {
	
	
	Rabbit(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.MONSTER);
		this.setIPainter(new RabbitPainter(this));
	}

}
