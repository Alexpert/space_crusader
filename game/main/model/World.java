package game.main.model;

import java.util.ArrayList;

public class World {

	private int width, height;
	private ArrayList<Entity> entities;
	private Tile[][] map;
	
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		this.map = WorldBuilder.createTiles(width, height);
		this.entities = new ArrayList<Entity>();
	}
	
	public void add(Entity e) {
		this.entities.add(e);
		this.map[e.getX()][e.getY()].add(e);
	}
	
	public Tile getTile(int x, int y) {
		return this.map[x][y];
	}
	
	//delete all entities from the selected tile
	public void clearTile(int x, int y) {
		this.map[x][y].clear();
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
