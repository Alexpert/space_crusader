package game.main.model;

import java.awt.Graphics;
import java.util.ArrayList;

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
		this.entities.clear();
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

}
