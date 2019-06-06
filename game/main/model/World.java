package game.main.model;

import java.util.ArrayList;

public class World {

	private int width, height;
	private ArrayList<Entity> entities;
	private Tile[][] map;
	private Model model;
	
	public World(int width, int height, Model model) {
		this.width = width;
		this.height = height;
		this.model = model;
		this.entities = new ArrayList<Entity>();
		this.map = new Tile[width][height];
		for(int i = 0; i < width; i ++) {
			for(int j = 0; j < height; j++) {
				this.map[i][j] = new Tile(i, j);
			}
		}
	}
	
	public void add(Entity e) {
		this.entities.add(e);
		this.map[e.getX()][e.getY()].add(e);
	}
	
	public Tile getTile(int x, int y) {
		return this.map[x][y];
	}
	
	public Model getModel() {
		return this.model;
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
