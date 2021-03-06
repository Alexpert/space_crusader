package game.main.model;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;

import game.main.model.entities.Player;
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
		if (index < this.entities.size())
			return this.entities.get(index);
		return null;
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
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
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
	
	public void paint(Graphics g, int posCamX, int posCamY, int camWidth, int camHeight, boolean paintOnlyBackground) {
		this.painter.paint(g,posCamX,posCamY,camWidth,camHeight, paintOnlyBackground);
	}
	
	public World getWorld() {
		return this.w;
	}
	
	public void setWorld(World w) {
		this.w = w;
	}

	public void step(long now) {
		if(this.getEntities().size()!=0) {
			int j=0;
			int nb = this.nbEntity()-1;
			while(nb >=j) {
				nb = this.nbEntity()-1;
				this.getEntity(nb-j).step(now);;
				j++;
				nb = this.nbEntity()-1;
			}
		}
		
	}

	public ArrayList<Entity> getEntities() {
		return this.entities;
	}
	
	public boolean isCollidable() {
		for(int i = 0; i < this.nbEntity(); i ++) {
			if(this.entities.get(i).collidable) {
				return true;
			}
		}
		return false;
	}

	public Player getPlayer() {
		Player player = null;
		int i = 0;
		
		while (i < this.getEntities().size() && player == null) {
			if (this.getEntity(i).getKind() == Kind.PLAYER)
				player = (Player) this.getEntity(i);
			i++;
		}
		
		return player;
	}

	public void setBiome(TileBiome biome) {
		this.biome = biome;
		this.painter = new TilePainter(this);
	}

}
