package game.main.model;

import game.main.model.entities.Rock;
import game.main.model.entities.Tree;

public class ShipBuilder {
	
	private static int width = 102;
	private static int height = 40;
	
	public static Tile[][] ship(World world) {
		Tile[][] tiles = new Tile[width][height];// 23 visible & 15 vide en hauteur   Rock==Wall & Tree=Gate & Flow
		//back of starship
		for (int i = 0; i < width; i++) {
			if (i%6 == 0 || i%6 == 1 || i%6 == 2) {
				tiles[i][0] = new Tile(i, 0, TileBiome.SPACE, world);
			} else {
				tiles[i][0] = new Tile(i, 0, TileBiome.SHIP, world);
				tiles[i][0].add(new Rock(tiles[i][0]));
			}
			tiles[i][1] = new Tile(i, 0, TileBiome.SHIP, world);
			tiles[i][1].add(new Rock(tiles[i][0]));
		}
		tiles[4][1].clear();
		tiles[4][1].add(new Tree(tiles[4][0]));
		//into starship
		for (int i = 2; i < 20; i++) {
			for (int j = 0; j < 102; j++) {
				tiles[j][i] = new Tile(j, i, TileBiome.SHIP, world);
				tiles[j][i].add(new Rock(tiles[j][i]));
			}
		}
		
				
		return tiles;
	}

}
