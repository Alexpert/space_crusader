package game.main.view;

import java.awt.Color;
import java.awt.Graphics;

import game.main.model.Tile;

public class TilePainter implements IPainter{

	Tile tile;
	
	public TilePainter(Tile t) {
		this.tile = t;
	}
	
	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(tile.getX()*32, tile.getY()*32, 32, 32);
		for(int k = 0; k < tile.nbEntity(); k++) {
			tile.getEntity(k).paint(g);
		}
		
	}

}
