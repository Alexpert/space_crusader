package game.main.view;

import java.awt.Color;
import java.awt.Graphics;

import game.main.model.Entity;

public class RabbitPainter implements IPainter {

	Entity entity;

	public RabbitPainter(Entity e) {
		this.entity = e;
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(entity.getX() * 32, entity.getY() * 32, 32, 32);
	}

}
