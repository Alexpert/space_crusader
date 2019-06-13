package testUnitaire;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Model;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.action_handler.ActionHandlerButton;
import game.main.model.entities.Button;

public class TestButton {
	
	static Model model;
	static Button button;
	static Entity entity;
	
	
	@BeforeClass
	public static void init() {
		model = new Model();
		button = new Button(model.getCurrentWorld().getTile(4, 2));
		button.setActionHandler(new ActionHandlerButton(button));
	}

	@Test
	public void testRecreatWorld() {
		Tile tileW1 = model.getWorlds().get(0).getTile(40, 40);
		button.pop(Direction.FRONT);
		Tile tileW2 = model.getWorlds().get(0).getTile(40, 40);
		assertTrue(tileW1 != tileW2);
		
	}
}
