package game.main.view.painters;

import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Entity;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class FlowerPainter implements IPainter {

	Entity entity;
	Image texture;

	public FlowerPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/flower.png");
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
