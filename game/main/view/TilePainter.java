package game.main.view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import game.main.model.Tile;

public class TilePainter{

	Tile tile;
	Color color;
	
	public TilePainter(Tile t) {
		this.tile = t;
		switch(t.getBiome()) {
		case DESERT:
			this.color = Color.YELLOW;
			break;
		case DRYLAND:
			this.color = Color.ORANGE;
			break;
		case FOREST:
			this.color = Color.GREEN;
			break;
		case ICE:
			this.color = Color.BLUE;
			break;
		case JUNGLE:
			this.color = Color.CYAN;
			break;
		case PLAIN:
			this.color = Color.PINK;
			break;
		case STONE:
			this.color = Color.GRAY;
			break;
		case SWAMP:
			this.color = Color.LIGHT_GRAY;
			break;
		case TAIGA:
			this.color = Color.MAGENTA;
			break;
		default:
			this.color = Color.BLACK;
			break;
		}
	}
	
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	public void paint(Graphics g, int posCamX, int posCamY, int camWidth, int camHeight) {
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
				g.setColor(this.color);
				g.fillRect(px, py, 32, 32);
				for(int k = 0; k < tile.nbEntity(); k++) {
					tile.getEntity(k).paint(g,px,py);
				}
			}	
		}
		
		/*g.setColor(Color.GREEN);
		g.fillRect(tile.getX()*32, tile.getY()*32, 32, 32);
		for(int k = 0; k < tile.nbEntity(); k++) {
			tile.getEntity(k).paint(g);
		}*/
		
	}

}
