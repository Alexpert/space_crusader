package game.main.view.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Entity;
import game.main.view.IPainter;

public class GatePainter implements IPainter {
	
	Entity entity;
	Image texture;

	public GatePainter(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g, int posX, int posY) {
		g.setColor(Color.MAGENTA);
		g.fillRect(posX, posY, 32, 32);

	}

}
