package game.main.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import edu.ricm3.game.GameController;
import game.main.model.Model;
import game.main.view.View;

public class Controller extends GameController {
	
	private Model model;
	private View view;
	
	public Controller(Model m, View v) {
		this.model = m;
		this.view = v;
		
	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long now) {
		if (this.model.getCurrentWorld() != null) {
			this.model.getCurrentWorld().getWorldSoundHander().start();
			this.model.step(now);
			this.view.step(now);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Cast of the given character to a string (composed of lower letters)
		String key = String.valueOf(e.getKeyChar()).toLowerCase();
		model.writeHashMap(key, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Cast of the given character to a string (composed of lower letters)
		String key = String.valueOf(e.getKeyChar()).toLowerCase();
		model.writeHashMap(key, false);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	
	//Return if the given keyboard key is Pressed
	public boolean isPressed(String str) {
		return model.getBoolHashMap(str);
	}

}
