package game.main.model;

import java.util.ArrayList;

public class World {

	private int width, height;
	private ArrayList<Entity> entities;
	private Tile[][] map;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.entities = new ArrayList<Entity>();
		this.map = new Tile[width][height];
	}
	
	public void add(Entity e) {
		this.entities.add(e);
	}
	
	
	
	
}
