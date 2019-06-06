package game.main.controller;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;

import edu.ricm3.game.GameController;
import game.main.model.Model;
import game.main.view.View;

public class Controller extends GameController {
	
	private HashMap<String, Boolean> map;
	private Model model;
	private View view;
	
	public Controller(Model m, View v) {
		this.map = new HashMap<>();
		this.model = m;
		this.view = v;
		
		//Initialization of the HashMap with the keyboard key :
		//Initialization of the alphabet's letters
		char c;
		String s;
		for(c = 'a'; c <= 'z'; ++c) {
			s = String.valueOf(c);
			this.map.put(s, false);
		}
		
		//Initilization of the figures
		for(int i = 0; i < 10; i++) {
			s = String.valueOf(i);
			this.map.put(s, false);
		}
		
		//Initialization of SPACE and ENTER
		this.map.put("SPACE".toLowerCase(), false);
		this.map.put("ENTER".toLowerCase(), false);
		
		//Initialization of the four arrows
		this.map.put("FU".toLowerCase(), false);
		this.map.put("FD".toLowerCase(), false);
		this.map.put("FR".toLowerCase(), false);
		this.map.put("FL".toLowerCase(), false);
		
		
	}

	@Override
	public void notifyVisible() {
		// TODO Auto-generated method stub

	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		//Cast of the given character to a string (composed of lower letters)
		String key = String.valueOf(e.getKeyChar()).toLowerCase();
		this.map.put(key, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		//Cast of the given character to a string (composed of lower letters)
		String key = String.valueOf(e.getKeyChar()).toLowerCase();
		this.map.put(key, false);

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
		return this.map.get(str.toLowerCase());
	}

}
