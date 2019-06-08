package game.main.view.painters;

import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Entity;
import game.main.model.TileBiome;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class TreePainter implements IPainter {

	Entity entity;
	Image texture;

	public TreePainter(Entity e, TileBiome biome) {
		this.entity = e;
		String treeType;
		
		switch(biome) {
		case DESERT:
		case STONE:
			treeType = "dead_bush";
			break;
		case ICE:
		case TAIGA:
			treeType = "spruce";
			break;
		case DRYLAND:
			treeType = "acacia";
			break;
		case FOREST:
			treeType = "dark_oak";
			break;
		case JUNGLE:
			treeType = "jungle";
			break;
		case PLAIN:
			treeType = "oak";
			break;
		case SWAMP:
			treeType = "birch";
			break;
		default:
			treeType = "oak";
			break;
		}
		
		
		this.texture = TextureProvider.getInstance().getTexture("assets/entities/trees/" + treeType + ".png");
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paint(Graphics g ,int posX,int posY) {
		g.drawImage(this.texture, posX, posY, 32, 32, null);
	}

}
