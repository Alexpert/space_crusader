package testUnitaire;

import static org.junit.Assert.*;

import org.junit.Test;


import game.main.model.Direction;
import game.main.model.Kind;
import game.main.model.Model;
import game.main.model.Player;
import game.main.model.World;


public class TestEntity {
	
	@Test
	public void testEntityCell0() {
		Model m = new Model();
		Kind kind = Kind.TEAM;
		Player p1 = new Player(0, 0, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		m.getCurrentWorld().add(p1);
		assertTrue(p1.Cell(Direction.SOUTH, Kind.TEAM, 1) == false);
		Player p2 = new Player(0, 1, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		m.getCurrentWorld().add(p2);
		assertTrue(p1.Cell(Direction.SOUTH, Kind.TEAM, 1) == true);
		Player p3 = new Player(1, 1, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		m.getCurrentWorld().add(p3);
		assertTrue(p1.Cell(Direction.SOUTH, Kind.TEAM, 2) == true);
		assertTrue(p1.Cell(Direction.EAST, Kind.TEAM, 2) == true);
	}
	
	@Test
	public void testEntityCell1() {
		Model m = new Model();
		Kind kind = Kind.TEAM;
		Player p1 = new Player(0, 0, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		Player p2 = new Player(19, 0, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		Player p3 = new Player(0, 19, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		m.getCurrentWorld().add(p1);
		m.getCurrentWorld().add(p2);
		m.getCurrentWorld().add(p3);
		assertTrue(p1.Cell(Direction.NORTH, Kind.TEAM, 1) == true);
		assertTrue(p1.Cell(Direction.WEST, Kind.TEAM, 1) == true);
	}
	
	@Test
	public void testEntityCell2() {
		Model m = new Model();
		Kind kind = Kind.TEAM;
		Player p1 = new Player(0, 0, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		Player p2 = new Player(0, 18, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		Player p3 = new Player(16, 0, 10, Direction.NORTH, true, m.getCurrentWorld(), kind);
		m.getCurrentWorld().add(p1);
		m.getCurrentWorld().add(p2);
		m.getCurrentWorld().add(p3);
		assertTrue(p1.Cell(Direction.NORTH, Kind.TEAM, 2) == true);
		assertTrue(p1.Cell(Direction.WEST, Kind.TEAM, 4) == true);
	}
}
