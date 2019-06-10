package game.main.view.painters;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import game.main.model.Entity;
import game.main.view.Animation;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class PlayerPainter  implements IPainter{

	Entity entity;
	Image texture;
	ArrayList<Animation> listAnimation;
	Animation currentAnimation;
	
	public PlayerPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/player.png");
		this.initAnimation();
		this.currentAnimation = this.listAnimation.get(1);
	}

	@Override
	public void step(long now) {
		long timePerFrame = this.entity.getTotalTimeAction()/this.currentAnimation.getNumberSprite();
		int index = (int) (this.entity.getCurrentTimeAction()/timePerFrame);
		this.currentAnimation.setIndexAnimation(index%this.currentAnimation.getNumberSprite());	
	}

	@Override
	public void paint(Graphics g,int posX,int posY) {
		g.drawImage(this.currentAnimation.getCurrentSprite(), posX, posY, 32, 32, null);
	}
	
	public void initAnimation() {
		this.listAnimation = new ArrayList<>();
		Image animSprite = TextureProvider.getInstance().getTexture("assets/entities/player_animation.png");
		this.listAnimation.add(new Animation(animSprite, 0, 1));	//default image
		this.listAnimation.add(new Animation(animSprite, 2, 6));	//walk to the right
	}
	
}
