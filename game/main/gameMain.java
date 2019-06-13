package game.main;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;
import game.main.controller.Controller;
import game.main.model.Model;
import game.main.view.Launcher;
import game.main.view.View;

public class gameMain {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
		Launcher l = new Launcher(model);
	}
}
