package game.main.model;

import game.main.view.AdultRabbitPainter;

public class AdultRabbit extends Entity {

	AdultRabbit(int x, int y, int health, Direction d, boolean moveable, World world) {
		super(x, y, health, d, moveable, world, Kind.MONSTER);
		this.setIPainter(new AdultRabbitPainter(this));
	}

}
