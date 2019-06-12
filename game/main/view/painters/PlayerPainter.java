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

public class PlayerPainter  implements IPainter{

	Entity entity;
	Image texture;
	ArrayList<Animation> listAnimation;
	Animation currentAnimation;
	
	public PlayerPainter(Entity e) {
		this.entity = e;
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/player.png");
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
	public void paint(Graphics g,int posX,int posY) {
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
	
	public void initAnimation() {
		this.listAnimation = new ArrayList<>();
		Image animSprite = TextureProvider.getInstance().getTexture("assets/entities/player_animation.png");
		this.listAnimation.add(new Animation(animSprite, 0, 1));	//0		default looking right
		this.listAnimation.add(new Animation(animSprite, 1, 1));	//1		default looking left
		this.listAnimation.add(new Animation(animSprite, 4, 1,1));	//2		default looking north
		this.listAnimation.add(new Animation(animSprite, 5, 1,1));	//3		default looking south
		
		this.listAnimation.add(new Animation(animSprite, 2, 6));	//4		walk to the right
		this.listAnimation.add(new Animation(animSprite, 3, 6));	//5		walk to the left
		this.listAnimation.add(new Animation(animSprite, 4, 4));	//6		walk to the north
		this.listAnimation.add(new Animation(animSprite, 5, 4));	//7		walk to the south
	}
	
	@Override
	public void changeActionAnimation(Action a, Direction d) {
		if(a == Action.PATIENT) {
			if(d == Direction.EAST) {
				this.currentAnimation = this.listAnimation.get(0);
			}
			else if(d == Direction.WEST) {
				this.currentAnimation = this.listAnimation.get(1);
			}
			else if(d == Direction.NORTH) {
				this.currentAnimation = this.listAnimation.get(2);
			}
			else if(d == Direction.SOUTH) {
				this.currentAnimation = this.listAnimation.get(3);
			}
		}
		if(a == Action.HIT) {
			this.currentAnimation = this.listAnimation.get(0);
		}
		if(a == Action.MOVE) {
			if(d == Direction.EAST) {
				this.currentAnimation = this.listAnimation.get(4);
			}
			else if(d == Direction.WEST) {
				this.currentAnimation = this.listAnimation.get(5);
			}
			else if(d == Direction.NORTH) {
				this.currentAnimation = this.listAnimation.get(6);
			}
			else if(d == Direction.SOUTH) {
				this.currentAnimation = this.listAnimation.get(7);
			}
		}
	}
	
}
