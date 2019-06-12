package game.main.view;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.model.Action;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.World;

public class Viewport {

	private int width, height;
	private World world;
	private Entity entity;
	
	public Viewport(World w, int width, int height) {
		this.world = w;
		this.width = width;
		this.height = height;
	}
	
	public void setEntity(Entity e) {
		this.entity = e;
		this.entity.setViewport(this);
	}
	
	public Entity getEntity() {
		return this.entity;
	}
	
	public void paint(Graphics g) {
		/*System.out.println("Cam Position x: " + this.getX() + " y: " + this.getY());
		System.out.println("World Dimension w: " + this.w.getWidth() + " h: " + this.w.getHeight());
		System.out.println("Position Entity e: " + this.entity.getX() + ", "
				+ this.entity.getY());*/
		
		for(int i = 0; i < world.getWidth(); i++) {
			for(int j = 0; j < world.getHeight(); j++) {
				Tile t = world.getTile(i, j);
				t.paint(g,this.getDisplayX(),this.getDisplayY(),this.width,this.height,true);
			}
		}
		
		for(int i = 0; i < world.getWidth(); i++) {
			for(int j = 0; j < world.getHeight(); j++) {
				Tile t = world.getTile(i, j);
				t.paint(g,this.getDisplayX(),this.getDisplayY(),this.width,this.height,false);
			}
		}
	}

	public int getX() {
		int px = this.entity.getX() * 32;
		px -= this.width / 2;
		
		if (px < 0)
			px += this.world.getWidth() * 32;
		if (px >= this.world.getWidth() * 32)
			px -= this.world.getWidth() * 32;
		
		return px;
	}

	public int getY() {
		int py = this.entity.getY() * 32;
		py -= this.height / 2;
		
		if (py < 0)
			py += this.world.getHeight() * 32;
		if (py >= this.world.getHeight() * 32)
			py -= this.world.getHeight() * 32;
		
		return py;
	}
	
	public int getDisplayX() {
		int paddingX=0, paddingY=0;
		if(this.entity.getCurrentAction() == Action.MOVE){
			if(this.entity.myDir(Direction.EAST)) {
				paddingX = (int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))-32;
			}
			else if(this.entity.myDir(Direction.WEST)) {
				paddingX = -(int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))+32;		
			}
		}
		return this.getX()+paddingX;
	}
	
	public int getDisplayY() {
		int paddingX=0, paddingY=0;
		if(this.entity.getCurrentAction() == Action.MOVE){
			if(this.entity.myDir(Direction.NORTH)) {
				paddingY = -(int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))+32;
			}
			else if(this.entity.myDir(Direction.SOUTH)) {
				paddingY = (int) (this.entity.getCurrentTimeAction()/(this.entity.getTotalTimeAction()/32))-32;
			}
		}
		return this.getY()+paddingY;
	}

	public void setWorld(World world) {
		this.world = world;
	}
}
