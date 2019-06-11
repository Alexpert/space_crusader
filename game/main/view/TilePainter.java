package game.main.view;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

import game.main.model.Tile;

public class TilePainter{

	Tile tile;
	Image texture;
	
	public TilePainter(Tile t) {
		this.tile = t;
		this.texture = TextureProvider.getInstance()
				.getTexture(t.getBiome().getTexturePath());
	}
	
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	public void paint(Graphics g, int posCamX, int posCamY, int camWidth, int camHeight, boolean paintOnlyBackground) {
		ArrayList<Integer> allPosX = new ArrayList<>();
		ArrayList<Integer> allPosY = new ArrayList<>();
		
		int posX = this.tile.getX()*32 - posCamX, posY = this.tile.getY()*32 - posCamY;
		
		while (posX < camWidth) {
			if (posX >= -32)
				allPosX.add(posX);
			posX += this.tile.getWorld().getWidth()*32;
		}
		
		while (posY < camHeight) {
			if (posY >= -32)
				allPosY.add(posY);
			posY += this.tile.getWorld().getHeight()*32;
		}
		
		for (int px: allPosX) {
			for (int py: allPosY) {
				if(paintOnlyBackground) {
					g.drawImage(this.texture, px, py, 32, 32, null);
				}
				else {
					for(int k = 0; k < tile.nbEntity(); k++) {
						tile.getEntity(k).paint(g,px,py);
					}
				}
			}	
		}
		
	}

}
