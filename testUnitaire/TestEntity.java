package testUnitaire;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;


import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Model;
import game.main.model.World;
import game.main.model.entities.Flower;
import game.main.model.entities.Player;


public class TestEntity {
	static Model model;
	static Entity entity1;
	static Entity entity2;
	static Entity entity3;
	
	@BeforeClass
	public static void init() {
		model = new Model();
		
		entity1 = new Player(model.getCurrentWorld().getTile(0, 0));
		new Player(model.getCurrentWorld().getTile(1, 0));
		
		entity2 = new Player(model.getCurrentWorld().getTile(20, 0));
		new Player(model.getCurrentWorld().getTile(1, 0));
		
		entity3 = new Flower(model.getCurrentWorld().getTile(0, 0));
	}
	
	@Test
	public void testEntitycell0() {
		assertTrue(entity1.cell(Direction.SOUTH, Kind.PLAYER, 0) == true);
		assertTrue(entity2.cell(Direction.EAST, Kind.PLAYER, 0) == false);
	}
	
	@Test
	public void testEntitycell1() {
		assertTrue(entity2.cell(Direction.SOUTH, Kind.PLAYER, 1) == false);
		assertTrue(entity2.cell(Direction.EAST, Kind.PLAYER, 1) == true);
	}
	
	@Test
	public void testEntitycell2() {
		assertTrue(entity2.cell(Direction.WEST, Kind.PLAYER, 20) == true);
		assertTrue(entity2.cell(Direction.WEST, Kind.PLAYER, 15) == false);
	}
	
	@Test
	public void testEntityClosest1() {
		assertTrue(entity1.closest(Kind.PLAYER, Direction.EAST) == true);
		assertTrue(entity1.closest(Kind.MONSTER, Direction.EAST) == false);
		assertTrue(entity1.closest(Kind.PLAYER, Direction.WEST) == false);
	}
}
