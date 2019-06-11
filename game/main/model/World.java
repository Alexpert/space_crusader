package game.main.model;

import java.util.ArrayList;

import game.main.model.entities.Gate;
import interpreter.IAutomaton;

public class World {

	private int width, height;
	private Tile[][] map;
	private Model model;
	
	public World(int width, int height, Model model) {
		this.width = width;
		this.height = height;
		this.model = model;
		this.map = WorldBuilder.createTiles(width, height, this);
		WorldBuilder.populate(map);
	}
	
	public void add(Entity e) {
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
	
	public ArrayList<Entity> getEntities() {
		ArrayList<Entity> entities = new ArrayList<>();
		
		for(Tile[] tileArray: this.map) {
			for (Tile tile: tileArray) {
				entities.addAll(tile.getEntities());
			}
		}
		
		return entities;
	}

	public void step(long now) {
		for(Tile[] tileArray: this.map) {
			for (Tile tile: tileArray) {
				tile.step(now);
			}
		}
	}
}
