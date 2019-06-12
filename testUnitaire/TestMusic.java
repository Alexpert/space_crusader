package testUnitaire;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import game.main.model.Model;
import game.main.model.World;

public class TestMusic {

	static Model model;
	static World world;
	
	@Test
	public void testInitializeMusic() {
		model = new Model();
		world = model.getCurrentWorld();
		File f = new File("assets/music/Survivor-EyeOfTheTiger.wav");
	}
}
