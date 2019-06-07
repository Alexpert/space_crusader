package game.main.view;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.model.Tile;
import game.main.model.World;

public class Viewport {

	int posX,posY;
	World w;
	
	public Viewport(World w) {
		this.posX=0;
		this.posY=0;
		this.w = w;
	}
	
	public void paint(Graphics g) {
		System.out.println("Cam Position x: " + posX + " y: " + posY);
		System.out.println("World Dimension w: " + this.w.getWidth() + " h: " + this.w.getHeight());
		
		for(int i = 0; i < w.getHeight(); i++) {
			for(int j = 0; j < w.getHeight(); j++) {
				Tile t = w.getTile(i, j);
				t.paint(g,posX,posY,640,640);
			}
		}
	}
}
