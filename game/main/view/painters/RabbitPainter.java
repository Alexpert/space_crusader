package game.main.view.painters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import game.main.model.Action;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.view.Animation;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class RabbitPainter implements IPainter {

	Entity entity;
	Image texture;
	ArrayList<Animation> listAnimation;
	Animation currentAnimation;

	public RabbitPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/rabbit.png");
		this.initAnimation();
		this.currentAnimation = this.listAnimation.get(0);
	}

	@Override
	public void step(long now) {
		long timePerFrame = this.entity.getTotalTimeAction()/this.currentAnimation.getNumberSprite();
		int index = (int) (this.entity.getCurrentTimeAction()/timePerFrame);
		this.currentAnimation.setIndexAnimation(index%this.currentAnimation.getNumberSprite());	
	}

	@Override
	public void paint(Graphics g ,int posX,int posY) {
		int paddingX=0, paddingY=0;
		if(this.entity.getCurrentAction() == Action.MOVE){
			if(this.entity.myDir(Direction.EAST)) {
				paddingX = (int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))-32;
			}
			else if(this.entity.myDir(Direction.WEST)) {
				paddingX = -(int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))+32;		
			}
			else if(this.entity.myDir(Direction.NORTH)) {
				paddingY = -(int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))+32;
			}
			else if(this.entity.myDir(Direction.SOUTH)) {
				paddingY = (int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))-32;
			}
		}
		g.drawImage(this.currentAnimation.getCurrentSprite(), posX+paddingX, posY+paddingY, 32, 32, null);
	}

	@Override
	public void changeActionAnimation(Action a, Direction d) {
		if(a == Action.PATIENT) {
			this.currentAnimation = this.listAnimation.get(0);
		}
		if(a == Action.MOVE) {
			if(d == Direction.EAST) {
				this.currentAnimation = this.listAnimation.get(3);
			}
			else if(d == Direction.WEST) {
				this.currentAnimation = this.listAnimation.get(4);
			}
			else if(d == Direction.NORTH) {
				this.currentAnimation = this.listAnimation.get(2);
			}
			else if(d == Direction.SOUTH) {
				this.currentAnimation = this.listAnimation.get(1);
			}
		}
	}
	
	public void initAnimation() {
		this.listAnimation = new ArrayList<>();
		Image animSprite = TextureProvider.getInstance().getTexture("assets/entities/rabbit_animation.png");
		this.listAnimation.add(new Animation(animSprite, 0, 1));	//default image
		this.listAnimation.add(new Animation(animSprite, 0, 8));	//walk to the south
		this.listAnimation.add(new Animation(animSprite, 1, 8));	//walk to the north
		this.listAnimation.add(new Animation(animSprite, 2, 8));	//walk to the east
		this.listAnimation.add(new Animation(animSprite, 3, 8));	//walk to the west
	}

}
