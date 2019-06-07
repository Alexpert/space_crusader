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
	public void paint(Graphics g ,int posX,int posY) {
		g.setColor(Color.WHITE);
		g.fillRect(posX, posY, 32, 32);
	}

}
