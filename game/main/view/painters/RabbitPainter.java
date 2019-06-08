package game.main.view.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Entity;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class RabbitPainter implements IPainter {

	Entity entity;
	Image texture;

	public RabbitPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/rabbit.png");
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g ,int posX,int posY) {
		g.drawImage(this.texture, posX, posY, 32, 32, null);
	}

}
