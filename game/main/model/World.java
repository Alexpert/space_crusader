package game.main.model;

import java.util.ArrayList;
import java.util.Random;

import game.main.model.entities.Gate;
import game.main.model.entities.Player;
import interpreter.IAutomaton;

public class World {

	private int width, height;
	private Tile[][] map;
	private Model model;

	
	public World(int width, int height, Model model,boolean isShip) {
		this.width = width;
		this.height = height;
		this.model = model;
		if(isShip) {
			this.generate(isShip);
		}
		else {
			this.generate(isShip);
			Structure structure = new Structure();
			Random random = new Random();
			this.applyStructure(structure, random.nextInt(this.width - 1), random.nextInt(this.width - 1));
		}
	}

	public World(int width, int height, Model model, WorldType worldType) {
		this.width = width;
		this.height = height;
		this.model = model;
		if (worldType == WorldType.PLANET) {
			this.map = WorldBuilder.createTiles(width, height, this);
			WorldBuilder.populate(map);
			Structure structure = new Structure();
			Random random = new Random();
			this.applyStructure(structure, random.nextInt(this.width - 1), random.nextInt(this.width - 1));
		} else if (worldType == WorldType.SHIP) {
			this.width = 30;
			this.height = 32;
			this.map = ShipBuilder.ship(this.width, this.height, this);
		}
	}

	public void applyStructure(Structure structure, int x, int y) {
		ArrayList<Tile> tiles = structure.getTiles();
		for (Tile tile : tiles) {
			this.map[(tile.getX() + x) % this.width][(tile.getY() + y) % this.height] = tile;
			tile.setX((tile.getX() + x) % this.width);
			tile.setY((tile.getY() + y) % this.height);
			tile.setWorld(this);
		}
	}
	
	public void generate(boolean ship) {
		if(ship) {
			this.map = new ShipBuilder().ship(width, height, this);
		}
		else{
			this.map = WorldBuilder.createTiles(width, height, this);
			WorldBuilder.populate(map);
		}
		
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

	// delete all entities from the selected tile
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

		for (Tile[] tileArray : this.map) {
			for (Tile tile : tileArray) {
				entities.addAll(tile.getEntities());
			}
		}

		return entities;
	}

	public void step(long now) {
		for (Tile[] tileArray : this.map) {
			for (Tile tile : tileArray) {
				tile.step(now);
			}
		}
	}

	public Player getPlayer() {
		Player player = null;
		
		for (int i = 0; i < this.width && player == null; i++)
			for (int j = 0; j < this.width && player == null; j++)
				player = this.map[i][j].getPlayer();
		
		return player;
		
	}
}
