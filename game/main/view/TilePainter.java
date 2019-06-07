package game.main.view;

import java.awt.Color;
import java.awt.Graphics;

import game.main.model.Tile;
import game.main.model.TileBiome;

public class TilePainter implements IPainter{

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
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(this.color);
		g.fillRect(tile.getX()*32, tile.getY()*32, 32, 32);
		for(int k = 0; k < tile.nbEntity(); k++) {
			tile.getEntity(k).paint(g);
		}
		
	}

}
