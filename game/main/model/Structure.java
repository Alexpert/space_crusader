package game.main.model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import game.main.model.entities.Flower;
import game.main.model.entities.Rock;
import game.main.model.entities.Wall;

public class Structure {
	private ArrayList<Tile> tiles;
	
	Structure() {
		this.tiles = new ArrayList<>();
		
//		this.tiles.add(new Tile(0, 0, TileBiome.DRYLAND, null));
//		this.tiles.add(new Tile(1, 0, TileBiome.DRYLAND, null));
//		this.tiles.add(new Tile(2, 0, TileBiome.DRYLAND, null));
//		
//		for (Tile tile: this.tiles)
//			new Flower(tile);

		int labWidth = 5, labHeight = 5;
		LabGrid grid = LabyrinthBuilder.recursiveBacktracking(labWidth, labHeight);
		
		for (int x = 0; x < labWidth; x++) {
			for (int y = 0; y < labHeight; y++) {
				for (int dx = 0; dx < 4; dx++) {
					for (int dy = 0; dy < 4; dy++) {
						Tile tile = new Tile(x * 4 + dx, y * 4 + dy, TileBiome.STONE, null);
						if ((dx == 0 || dy == 0) && (dx != 2 || grid.get(x, y).openTop == false)
												 && (dy != 2 || grid.get(x, y).openLeft == false))
							new Wall(tile, WallType.DUNGEON);
						this.tiles.add(tile);
					}
				}
				if (!grid.get(x, y).visited)
					new Flower(this.getTile(x * 4 + 1, y * 4 + 1));
			}
		}
		
		for (int x = 0; x < labWidth * 4; x++) {
			Tile tile = new Tile(x, labHeight * 4, TileBiome.STONE, null);
			new Wall(tile, WallType.DUNGEON);
			this.tiles.add(tile);
		}
		
		for (int y = 0; y < labHeight * 4 + 1; y++) {
			Tile tile = new Tile(labWidth * 4, y, TileBiome.STONE, null);
			new Wall(tile, WallType.DUNGEON);
			this.tiles.add(tile);
		}
		
		this.getTile(0, (labHeight * 4) / 2).clear();
	}
	
	Tile getTile(int x, int y) {
		int  i = 0; 
		while (i < this.tiles.size() && !(this.tiles.get(i).getX() == x && 
										  this.tiles.get(i).getY() == y)) {
			i++;
		}
		return i == this.tiles.size() ? null : this.tiles.get(i);
	}
	
	public ArrayList<Tile> getTiles() {
		return this.tiles;
	}
}


class LabyrinthBuilder {
	public static LabGrid recursiveBacktracking(int w, int h) {
		LabGrid labGrid = new LabGrid(w, h);
		Stack<LabCell> stack = new Stack<>();
		
		LabCell currentCell = labGrid.get(0, h / 2);
		currentCell.visited = true;
		stack.push(currentCell);
		
		while (!stack.isEmpty()){
			LabCell nextCell = currentCell.getUnvisitedNeighbour();
			
			if (nextCell != null) {
				if (currentCell.x < nextCell.x) {
					nextCell.openLeft = true;
				} else if (currentCell.y < nextCell.y) {
					nextCell.openTop = true;
				} else if (currentCell.x > nextCell.x) {
					currentCell.openLeft = true;
				} else {
					currentCell.openTop = true;
				}
				stack.push(currentCell);
				currentCell = nextCell;
				currentCell.visited = true;
			} else {
				System.out.println("POP!");
				currentCell = stack.pop();
			}
		}
 		
		
		return labGrid;
	}
}

class LabGrid {
	int w, h;
	ArrayList<LabCell> cells;
	
	LabGrid(int w, int h) {
		this.w = w;
		this.h = h;
		
		this.cells = new ArrayList<>();
		
		for (int y = 0; y < h; y++) {
			for (int x = 0; x < w; x++) {
				this.cells.add(new LabCell(x, y, this));
			}
		}
	}
	
	LabCell get(int x, int y) {
		return this.cells.get(y * this.w + x);
	}
}

class LabCell {
	int x, y;
	LabGrid grid;
	boolean visited = false;
	boolean openTop = false;
	boolean openLeft = false;
	
	LabCell(int x, int y, LabGrid grid) {
		this.grid = grid;
		this.x = x;
		this.y = y;
	}

	public LabCell getUnvisitedNeighbour() {
		ArrayList<LabCell> neighbours = new ArrayList<>();
		
		if (this.x > 0)
			neighbours.add(this.grid.get(this.x - 1, this.y));
		if (this.x < grid.w - 1)
			neighbours.add(this.grid.get(this.x + 1, this.y));
		if (this.y > 0)
			neighbours.add(this.grid.get(this.x, this.y - 1));
		if (this.y < grid.h - 1)
			neighbours.add(this.grid.get(this.x, this.y + 1));
		
		neighbours.removeIf(cell -> cell.visited);
		
		Random random = new Random();
		
		if (neighbours.size() > 0)
			return (neighbours.get(random.nextInt(neighbours.size())));
		else
			return null;
	}
}