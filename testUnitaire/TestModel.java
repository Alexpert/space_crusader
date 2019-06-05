package testUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;
import game.main.model.*;


public class TestModel {
	
	@Test
	public void testModelMove() {
		Player p = new Player(0, 0, 10, Direction.NORTH, true);
		assertTrue(p.getY()==0);
		p.move(Direction.SOUTH);
		assertTrue(p.getY()==32);
		p.move();
		assertTrue(p.getY()==0);
	}
	
	@Test
	public void testModelTurn() {
		Player p = new Player(0, 0, 10, Direction.NORTH, true);
		assertTrue(p.getOrientation() == Direction.NORTH);
		p.turn(Direction.SOUTH);
		assertTrue(p.getOrientation() == Direction.SOUTH);
		p.turn(Direction.LEFT);
		assertTrue(p.getOrientation() == Direction.EAST);
	}
}
