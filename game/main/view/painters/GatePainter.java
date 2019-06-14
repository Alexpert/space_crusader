package game.main.view.painters;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import game.main.model.Action;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class GatePainter implements IPainter {

	Entity entity;
	Image texture;

	public GatePainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/gate.png");
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g ,int posX,int posY) {
		g.drawImage(this.texture, posX, posY, 32, 32, null);
	}

	@Override
	public void changeActionAnimation(Action a, Direction d) {
		// TODO Auto-generated method stub
		
	}

}
