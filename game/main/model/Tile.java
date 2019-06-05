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
	
	int getX() {
		return this.x;
	}
	
	int getY() {
		return this.y;
	}
}
