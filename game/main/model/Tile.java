package game.main.model;

import java.awt.Graphics;
import java.util.ArrayList;

import game.main.view.IPainter;
import game.main.view.TilePainter;

public class Tile {
	
	private int x,y;
	private ArrayList<Entity> entities;
	private IPainter painter;
	private TileBiome biome;
	
	Tile(int x, int y, TileBiome biome) {
		this.x = x;
		this.y = y;
		this.biome = biome;
		this.entities = new ArrayList<Entity>();
		this.painter = new TilePainter(this);
	}

	public void add(Entity e) {
		this.entities.add(e);
	}
	
	public Entity getEntity(int index) {
		return this.entities.get(index);
	}
	
	public TileBiome getBiome() {
		return this.biome;
	}
	
	public boolean isEmpty() {
		return this.entities.isEmpty();
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getY() {
		return this.y;
	}
	
	public void clear() {
		this.entities.clear();
	}
	
	public int nbEntity() {
		return this.entities.size();
	}
	
	public void paint(Graphics g) {
		this.painter.paint(g);
	}

}
