package testUnitaire;

import static org.junit.Assert.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import org.junit.Test;
import game.main.controller.Controller;
import game.main.model.Model;
import game.main.view.View;

public class TestController {

	@Test
	public void testIsPressed() {
		Model m = new Model();
		View v = new View(m);
		Controller controller = new Controller(m,v);
		assertTrue(controller.isPressed("a") == false);
		assertTrue(controller.isPressed("A") == false);
	}
	
	@Test
	public void testKeyPressed() {
		Model m = new Model();
		View v = new View(m);
		Controller controller = new Controller(m,v);
		Container component = new Container();
		KeyEvent e = new KeyEvent(component, 0, 0, 0, 0x43, 'c', 0);
		controller.keyPressed(e);
		assertTrue(controller.isPressed("c") == true);
	}
	
	@Test
	public void testKeyRealeased() {
		Model m = new Model();
		View v = new View(m);
		Controller controller = new Controller(m,v);
		Container component = new Container();
		KeyEvent e = new KeyEvent(component, 0, 0, 0, 0x43, 'c', 0);
		controller.keyPressed(e);
		assertTrue(controller.isPressed("c") == true);
		controller.keyReleased(e);
		assertTrue(controller.isPressed("c") == false);
	}

}
