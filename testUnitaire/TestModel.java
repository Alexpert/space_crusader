package testUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;
import game.main.model.*;


public class TestModel {
	
	@Test
	public void testModelMove() {
		World world = new World(1024, 56);
		Player p = new Player(0, 0, 10, Direction.NORTH, true, world);
		assertTrue(p.getY()==0);
		p.move(Direction.SOUTH);
		assertTrue(p.getY()==32);
		p.move();
		assertTrue(p.getY()==0);
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
}
