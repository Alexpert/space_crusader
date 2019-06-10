package game.main.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import game.main.view.TilePainter;

public class Tile {
	
	private int x,y;
	private ArrayList<Entity> entities;
	private TileBiome biome;
	private TilePainter painter;
	private World w;
	
	Tile(int x, int y, TileBiome biome, World world) {
		this.x = x;
		this.y = y;
		this.w = world;
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
		int i = this.getEntities().size() - 1;
		while(i >= 0) {
			this.getEntities().get(i).removeTile();
			this.getEntities().remove(i);
			i--;
		}
	}
	
	public int nbEntity() {
		return this.entities.size();
	}
	
	public void remove(Entity e) {
		this.entities.remove(e);
	}
	
	public void paint(Graphics g, int posCamX, int posCamY, int camWidth, int camHeight) {
		this.painter.paint(g,posCamX,posCamY,camWidth,camHeight);
	}
	
	public World getWorld() {
		return this.w;
	}

	public void step(long now) {
		int i = this.getEntities().size() - 1;
		
		//A dirty way to go through the array knowing that the
		//current element could quit the array at any moment
		while(i >= 0) {
			this.getEntities().get(i).step(now);
			i--;
		}
	}

	ArrayList<Entity> getEntities() {
		return this.entities;
	}

}
