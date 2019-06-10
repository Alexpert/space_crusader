package game.main.model;

import java.util.ArrayList;

public class Donjon {

	private int width, height, positionX, positionY;
	private ArrayList<Entity> entities;
	private Tile[][] map;
	
	public Donjon(World w) {
		this.positionX = (int)(Math.random() * w.getWidth());
		this.positionY = (int)(Math.random() * w.getHeight());
		this.width = 30;
		this.height = 33;
		this.entities = new ArrayList<Entity>();
		this.map = new Tile[width][height];
		for(int i = 0; i < width; i ++) {
			for(int j = 0; j < height; j++) {
				this.map[i][j] = new Tile(i, j/*, TileBiome.STONE */);
			}
			
			// extern walls
			this.map[i][0].add(new Wall(i, 0, w));
			this.map[i][height-1].add(new Wall(i, height-1, w));
		}
		for(int i = 0; i < height; i++) {
			this.map[0][i].add(new Wall(0, i, w));
			this.map[width-1][i].add(new Wall(width-1, i, w));
		}
		
		// rooms separations
		for(int i = 1; i < width-1; i++) {
			this.map[i][20].add(new Wall(i, 20, w));
		}
		for(int i = 21; i < height-1; i++) {
			this.map[11][i].add(new Wall(11, i, w));
			this.map[22][i].add(new Wall(22, i, w));
		}
		
		//doors
		this.map[width][10].clear();
		this.map[1][20].clear();
		this.map[11][26].clear();
		this.map[22][26].clear();
		
		// labyrinth
		this.map[7][4].add(new Wall(7, 4, w));
		this.map[5][5].add(new Wall(5, 5, w));
		this.map[6][5].add(new Wall(6, 5, w));
		for(int i = 8; i < 29; i++) {	
			this.map[5][i].add(new Wall(5, i, w));		
		}
		this.map[4][6].add(new Wall(4, 6, w));
		for(int i = 7; i < 10; i++) {	
			this.map[5][i].add(new Wall(5, i, w));		
		}
		this.map[6][7].add(new Wall(6, 7, w));
		for(int i = 6; i < 10; i++) {	
			this.map[9][i].add(new Wall(9, i, w));		
		}
		for(int i = 11; i < 14; i++) {
			this.map[i][7].add(new Wall(i, 7, w));
		}
		this.map[11][8].add(new Wall(11, 8, w));
		for(int i = 6; i < 9; i++) {	
			this.map[15][i].add(new Wall(15, i, w));		
		}
		for(int i = 11; i < 18; i++) {	
			this.map[i][9].add(new Wall(i, 9, w));		
		}
		this.map[13][10].add(new Wall(13, 10, w));
		this.map[13][11].add(new Wall(13, 11, w));
		this.map[17][10].add(new Wall(17, 10, w));
		for(int i = 15; i < 18; i++) {			
			this.map[i][11].add(new Wall(i, 11, w));
		}
		this.map[15][12].add(new Wall(15, 12, w));
		for(int i = 11; i < 16; i++) {			
			this.map[i][13].add(new Wall(i, 13, w));
		}
		this.map[21][6].add(new Wall(21, 6, w));
		this.map[21][7].add(new Wall(21, 7, w));
		this.map[28][9].add(new Wall(28, 9, w));
		for(int i = 9; i < 12; i++) {	
			this.map[27][i].add(new Wall(27, i, w));		
		}
		for(int i = 25; i < 29; i++) {	
			this.map[i][13].add(new Wall(i, 13, w));		
		}
		this.map[28][19].add(new Wall(28, 19, w));
		for(int i = 19; i < 28; i++) {			
			this.map[i][17].add(new Wall(i, 17, w));
		}
		this.map[19][16].add(new Wall(19, 16, w));
		for(int i = 9; i < 28; i++) {			
			this.map[i][15].add(new Wall(i, 15, w));
		}
		this.map[8][16].add(new Wall(8, 16, w));
		this.map[6][15].add(new Wall(6, 15, w));
		this.map[7][15].add(new Wall(7, 15, w));
		for(int i = 11; i < 15; i++) {			
			this.map[5][i].add(new Wall(5, i, w));
			this.map[9][i].add(new Wall(9, i, w));
		}
		this.map[10][11].add(new Wall(10, 11, w));
		this.map[11][11].add(new Wall(11, 11, w));
		this.map[17][13].add(new Wall(17, 13, w));
		this.map[17][14].add(new Wall(17, 14, w));
		this.map[21][14].add(new Wall(21, 14, w));
		for(int i = 19; i < 24; i++) {			
			this.map[i][13].add(new Wall(i, 13, w));
		}
		this.map[19][11].add(new Wall(19, 11, w));
		this.map[19][12].add(new Wall(19, 12, w));
		this.map[23][12].add(new Wall(23, 12, w));
		for(int i = 21; i < 25; i++) {			
			this.map[i][11].add(new Wall(i, 11, w));
		}
		this.map[21][10].add(new Wall(21, 10, w));
		for(int i = 19; i < 22; i++) {			
			this.map[i][9].add(new Wall(i, 9, w));
		}
		this.map[19][8].add(new Wall(19, 8, w));
		for(int i = 17; i < 20; i++) {			
			this.map[i][7].add(new Wall(i, 7, w));
		}
		for(int i = 8; i < 12; i++) {	
			this.map[25][i].add(new Wall(25, i, w));		
		}
		for(int i = 24; i < 28; i++) {		
			this.map[i][7].add(new Wall(i, 7, w));	
		}
		for(int i = 7; i < 10; i++) {	
			this.map[23][i].add(new Wall(23, i, w));		
		}
		
		// room 1
		for(int i = 24; i < 29; i++) {	
			this.map[2][i].add(new Wall(2, i, w));	
			this.map[8][i].add(new Wall(8, i, w));		
		}
		for(int i = 21; i < 25; i++) {	
			this.map[5][i].add(new Wall(5, i, w));		
		}
		for(int i = 28; i < 32; i++) {	
			this.map[5][i].add(new Wall(5, i, w));			
		}
		
		// room 2
		for(int i = 25; i < 28; i++) {	
			for(int j = 13; j < 16; j++) {	
				this.map[j][i].add(new Wall(j, i, w));		
			}	
		}
		for(int j = 17; j < 20; j++) {	
			for(int i = 22; i < 25; i++) {	
				this.map[j][i].add(new Wall(j, i, w));		
			}	
			for(int i = 28; i < 31; i++) {	
				this.map[j][i].add(new Wall(j, i, w));		
			}	
		}
		
		// Graal room
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}

	public int getPositionX() {
		return positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public Tile getTile(int x, int Y) {
		return map[0][0];
	}

	public ArrayList<Entity> getEntities() {
		return entities;
	}

	public Tile[][] getMap() {
		return map;
	}
	
	

}
