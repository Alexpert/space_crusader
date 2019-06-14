package game.main.view.painters;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import game.main.model.Action;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.view.Animation;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class DroppedBombPainter implements IPainter{

	Entity entity;
	Image texture;
	ArrayList<Animation> listAnimation;
	Animation currentAnimation;
	
	public DroppedBombPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/rabbit.png");
		this.initAnimation();
		this.currentAnimation = this.listAnimation.get(0);
	}
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g, int posX, int posY) {
		g.drawImage(this.currentAnimation.getCurrentSprite(), posX, posY, 32, 32, null);
	}

	@Override
	public void changeActionAnimation(Action a, Direction d) {
		// TODO Auto-generated method stub
		
	}
	
	public void initAnimation() {
		this.listAnimation = new ArrayList<>();
		Image animSprite = TextureProvider.getInstance().getTexture("assets/entities/bomb.png");
		this.listAnimation.add(new Animation(animSprite, 0, 1));	//0		default looking
	}

}
