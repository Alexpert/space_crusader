package game.main.model;

import java.net.Socket;

import game.main.model.entities.*;
import game.main.model.items.*;

public class ShipBuilder {

	public static Tile[][] ship(int width, int height, World world) {
		Tile[][] tiles = new Tile[width][height];// 23 visible & 15 vide en hauteur DUNGEON==autre chause & Tree=Gate &
													// Flower=buton
		// back of starship
		for (int i = 0; i < width; i++) {
			if (i % 6 == 3 || i % 6 == 4 || i % 6 == 5) {
				tiles[i][0] = new Tile(i, 0, TileBiome.SPACE, world);
			} else {
				tiles[i][0] = new Tile(i, 0, TileBiome.SHIP, world);
				new Wall(tiles[i][0], WallType.DUNGEON);
			}
			tiles[i][1] = new Tile(i, 1, TileBiome.SHIP, world);
			new Wall(tiles[i][1], WallType.DUNGEON);
		}

		tiles[1][1].clear();
		new Gate(tiles[1][1]);
		// into starship
		for (int i = 2; i < height - 20; i++) {
			for (int j = 0; j < width; j++) {
				tiles[j][i] = new Tile(j, i, TileBiome.SHIP, world);
			}
		}
		// front wall
		for (int j = 4; j < width - 1; j++) {
			new Wall(tiles[j][height - 21], WallType.DUNGEON);
		}
		// cockpite

		for (int j = height - 20; j < height - 18; j++) {
			for (int i = 0; i < 5; i++) {
				tiles[i][j] = new Tile(i, j, TileBiome.SHIP, world);
			}
			for (int i = 5; i < width - 2; i++) {
				tiles[i][j] = new Tile(i, j, TileBiome.SPACE, world);
			}
		}
		for (int j = height - 18; j < height - 16; j++) {
			for (int i = 0; i < 3; i++) {
				tiles[i][j] = new Tile(i, j, TileBiome.SHIP, world);
			}
			for (int i = 4; i < width - 1; i++) {
				tiles[i][j] = new Tile(i, j, TileBiome.SPACE, world);
			}
		}
		tiles[3][height - 18] = new Tile(3, height - 18, TileBiome.SHIP, world);
		tiles[width-1][height - 18] = new Tile(width-1, height - 18, TileBiome.SHIP, world);
		tiles[width-1][height - 19] = new Tile(width-1, height - 19, TileBiome.SHIP, world);
		tiles[width-2][height - 19] = new Tile(width-2, height - 19, TileBiome.SHIP, world);
		tiles[width-1][height - 20] = new Tile(width-1, height - 20, TileBiome.SHIP, world);
		tiles[width-2][height - 20] = new Tile(width-2, height - 20, TileBiome.SHIP, world);
		tiles[3][height - 17] = new Tile(3, height - 17, TileBiome.SPACE, world);
		tiles[width-1][height - 17] = new Tile(width-1, height - 17, TileBiome.SPACE, world);
		

		new Wall(tiles[width-1][height - 18], WallType.DUNGEON);
		new Wall(tiles[width-2][height - 19], WallType.DUNGEON);
		new Wall(tiles[width-2][height - 20], WallType.DUNGEON);
		new Wall(tiles[3][height - 18], WallType.DUNGEON);
		new Wall(tiles[4][height - 19], WallType.DUNGEON);
		new Wall(tiles[4][height - 20], WallType.DUNGEON);
		new Wall(tiles[0][height - 17], WallType.DUNGEON);
		new Wall(tiles[1][height - 17], WallType.DUNGEON);
		new Wall(tiles[2][height - 17], WallType.DUNGEON);

		new Flower(tiles[1][height - 20]);		
		// space
		for (int i = height - 16; i < height; i++) {
			for (int j = 0; j < width; j++) {
				tiles[j][i] = new Tile(j, i, TileBiome.SPACE, world);
			}
		}
		new Anvil(tiles[1][height - 27]);
		new DroppedItem(tiles[0][height - 27], new Fur());
		new DroppedItem(tiles[2][height - 27], new Fur());
		return tiles;
	}

}
