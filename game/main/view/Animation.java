package game.main.view;

import java.awt.Image;
import java.awt.image.BufferedImage;

public class Animation {
	
	private Image spriteBeforeAnimation;
	private Image[] animations;
	int indexAnim=0;
	int numberSprite;

	public Animation(Image sprite,int lineAnim, int numberSprite ) {
		this.spriteBeforeAnimation = sprite;
		this.splitSprite(lineAnim,numberSprite);
		this.numberSprite=numberSprite;
	}
	
	public void splitSprite(int lineAnim, int numberSprite) {
	    this.animations = new BufferedImage[numberSprite];
	    for (int i = 0; i < numberSprite; i++) {
	    	this.animations[i] = ((BufferedImage) this.spriteBeforeAnimation).getSubimage(i*32, lineAnim*32, 32, 32);
	    }
	}
	
	public void setIndexAnimation(int index) {
		this.indexAnim = index;
	}
	
	public Image getCurrentSprite() {
		return this.animations[this.indexAnim];
	}
	
	public int getNumberSprite() {
		return this.numberSprite;
	}
}
