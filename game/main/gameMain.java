package game.main;

import java.awt.Dimension;

import edu.ricm3.game.GameUI;
import game.main.controller.Controller;
import game.main.model.Model;
import game.main.view.View;

public class gameMain {

	public static void main(String[] args) throws Exception {
		Model model = new Model();
	    View view = new View(model);
	    Controller c = new Controller(model,view);
	    
	    Dimension d = new Dimension(1024, 768);
	    new GameUI(model, view, c, d);
	}
}
