package game.main.model;

import java.util.ArrayList;

public class Tile {
	
	private int x,y;
	private ArrayList<Entity> entities;
	
	Tile(int x, int y) {
		this.x = x;
		this.y = y;
		this.entities = new ArrayList<Entity>();
	}

	public void add(Entity e) {
		this.entities.add(e);
	}
	
	public Entity getEntity(int index) {
		return this.entities.get(index);
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

}
