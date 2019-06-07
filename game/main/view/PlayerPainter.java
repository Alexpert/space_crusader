package game.main.view;

import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Entity;
import game.main.model.TextureProvider;

public class PlayerPainter  implements IPainter{

	Entity entity;
	Image texture;
	
	public PlayerPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/player.png");
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g,int posX,int posY) {
		g.drawImage(this.texture, posX, posY, 32, 32, null);
	}
	
}
