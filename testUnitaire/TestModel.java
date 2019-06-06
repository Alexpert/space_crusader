package testUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;
import game.main.model.*;


public class TestModel {
	
	@Test
	public void testModelMove() {
		World world = new World(20, 20);
		Player p = new Player(0, 0, 10, Direction.NORTH, true, world);
		assertTrue(p.getY()==0);
		p.move(Direction.SOUTH);
		assertTrue(p.getY()==1);
		p.move();
		assertTrue(p.getY()==0);
		p.move();
		assertTrue(p.getY()==19);
		
	}
	
	@Test
	public void testModelTurn() {
		World world = new World(42, 42);
		Player p = new Player(0, 0, 10, Direction.NORTH, true, world);
		assertTrue(p.getOrientation() == Direction.NORTH);
		p.turn(Direction.SOUTH);
		assertTrue(p.getOrientation() == Direction.SOUTH);
		p.turn(Direction.LEFT);
		assertTrue(p.getOrientation() == Direction.EAST);
	}
	
	@Test
	public void testAddClearEntityFromTile() {
		World world = new World(42, 42);
		Player p = new Player(0, 0, 10, Direction.NORTH, true, world);
		world.add(p);
		assertTrue(world.getTile(0, 0).getEntity(0) == p);
		world.getTile(0, 0).clear();
		assertTrue(world.getTile(0, 0).isEmpty());
	}
	
	@Test
	public void testHitAndTakeDamage() {
		World world = new World(42, 42);
		Player p1 = new Player(0, 0, 10, Direction.NORTH, true, world);
		Player p2 = new Player(1, 0, 10, Direction.NORTH, true, world);
		world.add(p1);
		world.add(p2);
		assertTrue(p2.getHealth()==10);
		p1.hit(Direction.EAST);
		assertTrue(p2.getHealth()==5);
	}
	
	@Test
	public void testGetTile() {
		World world = new World(42, 42);
		Player p1 = new Player(0, 0, 10, Direction.NORTH, true, world);
		world.add(p1);
		Tile t1 = world.getTile(0, 0);
		Tile t2 = world.getTile(1, 0);
		assertTrue(p1.getTile(Direction.EAST)== t2);
		assertTrue(p1.getTile(Direction.RIGHT)== t2);
	}
}
