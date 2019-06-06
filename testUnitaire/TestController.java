package testUnitaire;

import static org.junit.Assert.*;

import java.awt.Container;
import java.awt.event.KeyEvent;

import org.junit.Test;
import game.main.controller.Controller;

public class TestController {

	@Test
	public void testIsPressed() {
		Controller controller = new Controller();
		assertTrue(controller.isPressed("a") == false);
		assertTrue(controller.isPressed("A") == false);
	}
	
	@Test
	public void testKeyPressed() {
		Controller controller = new Controller();
		Container component = new Container();
		KeyEvent e = new KeyEvent(component, 0, 0, 0, 0x43, 'c', 0);
		controller.keyPressed(e);
		assertTrue(controller.isPressed("c") == true);
	}
	
	@Test
	public void testKeyRealeased() {
		Controller controller = new Controller();
		Container component = new Container();
		KeyEvent e = new KeyEvent(component, 0, 0, 0, 0x43, 'c', 0);
		controller.keyPressed(e);
		assertTrue(controller.isPressed("c") == true);
		controller.keyReleased(e);
		assertTrue(controller.isPressed("c") == false);
	}

}
