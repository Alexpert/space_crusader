package game.main.model;

import java.util.ArrayList;
import java.util.Random;

import game.main.music.Music;
import game.main.music.SoundProvider;
import game.main.music.WorldSoundHandler;
import game.main.model.entities.Gate;
import interpreter.IAutomaton;

public class World {

	private int width, height;
	private Tile[][] map;
	private Model model;
	private Music sound;
	private WorldSoundHandler soundHandler;
	
	public World(int width, int height, Model model) {
		this.width = width;
		this.height = height;
		this.model = model;
		this.map = WorldBuilder.createTiles(width, height, this);
		WorldBuilder.populate(map);
		Structure structure = new Structure();
		Random random = new Random();
		this.applyStructure(structure, random.nextInt(this.width - 1), random.nextInt(this.width - 1));
		this.setSoundHandler(new WorldSoundHandler(this));
		this.setMusic(SoundProvider.getInstance().getSound("assets/music/ambiance_monde.wav"));
	}

	public World(int width, int height, Model model, WorldType worldType) {
		this.width = width;
		this.height = height;
		this.model = model;
		this.setSoundHandler(new WorldSoundHandler(this));
		if (worldType == WorldType.PLANET) {
			this.map = WorldBuilder.createTiles(width, height, this);
			WorldBuilder.populate(map);
			Structure structure = new Structure();
			Random random = new Random();
			this.applyStructure(structure, random.nextInt(this.width - 1), random.nextInt(this.width - 1));
			this.setMusic(SoundProvider.getInstance().getSound("assets/music/ambiance_monde.wav"));
		} else if (worldType == WorldType.SHIP) {
			this.width = 30;
			this.height = 32;
			this.map = ShipBuilder.ship(this.width, this.height, this);
			this.setMusic(SoundProvider.getInstance().getSound("assets/music/ambiance_vaisseau.wav"));
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
	
	public void generate() {
		this.map = WorldBuilder.createTiles(width, height, this);
		WorldBuilder.populate(map);
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
//		
//		ArrayList<Entity> copy = new ArrayList<>(entities);
//		copy.removeIf(e -> e.getKind() != Kind.PLAYER);
//		System.out.println(this.getWidth() + ": " + copy.size());

		return entities;
	}

	public void step(long now) {
		for (Tile[] tileArray : this.map) {
			for (Tile tile : tileArray) {
				tile.step(now);
			}
		}
	}
	
	public void setMusic(Music m) {
		this.sound = m;
	}
	
	public Music getMusic() {
		return this.sound;
	}
	
	public void setSoundHandler(WorldSoundHandler so) {
		this.soundHandler = so;
	}
	
	public WorldSoundHandler getWorldSoundHander() {
		return this.soundHandler;
	}
}
