package testUnitaire;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Model;
import game.main.model.World;
import game.main.model.action_handler.ActionHandlerGate;
import game.main.model.entities.Flower;
import game.main.model.entities.Gate;
import game.main.model.entities.Player;

public class TestGate {
	
	static Model model;
	static Gate gate;
	static Entity entity;
	
	
	@BeforeClass
	public static void init() {
		model = new Model();
		gate = new Gate(model.getCurrentWorld().getTile(4, 2));
		gate.setActionHandler(new ActionHandlerGate(gate));
		World otherWorld = new World(500, 500, model, false);
	}

	@Test
	public void testChangeWorld() {
		assertTrue(model.getCurrentWorld().getWidth() == 200);
		model.getPlayer().patient();
		assertTrue(model.getPlayer().getTotalTimeAction() == 10);
		gate.pop(Direction.EAST);
		assertTrue(model.getCurrentWorld().getWidth() == 500);
		model.getPlayer().patient();
		System.out.println("Total Time Action Player "+model.getPlayer().getTotalTimeAction());
		assertTrue(model.getPlayer().getTotalTimeAction() != 500);
		assertTrue(model.getPlayer().getX() == gate.getX() + 1);
		assertTrue(model.getPlayer().getY() == gate.getY());
	}
	
	@Test
	public void testWizz() {
		int xGate = gate.getX();
		int yGate = gate.getY();
		gate.wizz(Direction.NORTH);
		assertTrue((gate.getX() != xGate) || (gate.getY() != yGate));
		assertTrue(gate.getTile().getEntity(0) instanceof Gate);
	}
	

}
