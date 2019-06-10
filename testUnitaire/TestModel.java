package testUnitaire;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.main.controller.Controller;
import game.main.model.*;
import game.main.model.entities.Player;
import game.main.view.View;


public class TestModel {
	static Model model;
	static Player player;
	static Player player2;
	static Player player3;
	
	@BeforeClass
	public static void init() {
		model = new Model();
		player = new Player(model.getCurrentWorld().getTile(0, 0));
		player2 = new Player(model.getCurrentWorld().getTile(1, 0));
		player3 = new Player(model.getCurrentWorld().getTile(0, 0));
		new Player(model.getCurrentWorld().getTile(5, 5));
		
	}
	
	@Test
	public void testModelMove() {
		assertTrue(player.getY()==0);
		player.move(Direction.SOUTH);
		assertTrue(player.getY()==1);
		player.move();
		assertTrue(player.getY()==0);
		player.move();
		assertTrue(player.getY()==model.getCurrentWorld().getHeight() - 1);
		player.move(Direction.SOUTH);
		assertTrue(player.getY()==0);
		
	}
	
	@Test
	public void testModelTurn() {
		assertTrue(player.getOrientation() == Direction.NORTH);
		player.turn(Direction.SOUTH);
		assertTrue(player.getOrientation() == Direction.SOUTH);
		player.turn(Direction.LEFT);
		assertTrue(player.getOrientation() == Direction.EAST);
	}
	
	@Test
	public void testAddClearEntityFromTile() {
		assertTrue(!model.getCurrentWorld().getTile(5, 5).isEmpty());
		model.getCurrentWorld().getTile(5, 5).clear();
		assertTrue(model.getCurrentWorld().getTile(5, 5).isEmpty());
	}

//	@Test
//	public void testHitAndTakeDamage() {
//		assertTrue(player2.getHealth()==10);
//		player.hit(Direction.EAST);
//		assertTrue(player2.getHealth()==5);
//	}
	
	@Test
	public void testGetTile() {
		Tile t1 = model.getCurrentWorld().getTile(0, 0);
		Tile t2 = model.getCurrentWorld().getTile(1, 0);
		Tile t3 = model.getCurrentWorld().getTile(0, model.getCurrentWorld().getWidth() - 1);
		assertTrue(player3.getTile(Direction.EAST)== t2);
		assertTrue(player3.getTile(Direction.RIGHT)== t2);
		assertTrue(player3.getTile(Direction.NORTH)==t3);
		assertTrue(player3.getTile(Direction.FRONT)==t3);
		assertTrue(player3.getTile(Direction.WEST)==model.getCurrentWorld().getTile(model.getCurrentWorld().getWidth() - 1,0));
		assertTrue(player3.getTile(Direction.LEFT)==model.getCurrentWorld().getTile(model.getCurrentWorld().getWidth() - 1,0));
		player3.move(Direction.WEST);
		assertTrue(player3.getTile(Direction.EAST)== t1);
		assertTrue(player3.getTile(Direction.RIGHT)== t1);
		assertTrue(player3.getTile(Direction.NORTH)==model.getCurrentWorld().getTile(model.getCurrentWorld().getWidth() - 1,model.getCurrentWorld().getWidth() - 1));
		assertTrue(player3.getTile(Direction.FRONT)==model.getCurrentWorld().getTile(model.getCurrentWorld().getWidth() - 1,model.getCurrentWorld().getWidth() - 1));
		assertTrue(player3.getTile(Direction.SOUTH)==model.getCurrentWorld().getTile(model.getCurrentWorld().getWidth() - 1,1));
		assertTrue(player3.getTile(Direction.BACK)==model.getCurrentWorld().getTile(model.getCurrentWorld().getWidth() - 1,1));
	}
}
