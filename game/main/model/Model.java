package game.main.model;

import java.util.ArrayList;
import java.util.HashMap;

import edu.ricm3.game.GameModel;
import game.main.model.entities.Player;
import interpreter.Interpreter;
import interpreter.IAutomaton;

public class Model extends GameModel {

	World currentWorld = null;
	public boolean isInGame = false;
	private HashMap<String, Boolean> map;
	private String automataPath = "assets/automata.txt";

	public Model() {
		// Initialization of the HashMap with the keyboard key :
		// Initialization of the alphabet's letters
		this.map = new HashMap<String, Boolean>();
		char c;
		String s;
		for (c = 'a'; c <= 'z'; ++c) {
			s = String.valueOf(c);
			this.map.put(s, false);
		}

		// Initilization of the figures
		for (int i = 0; i < 10; i++) {
			s = String.valueOf(i);
			this.map.put(s, false);
		}

		// Initialization of SPACE and ENTER
		this.map.put("SPACE".toLowerCase(), false);
		this.map.put("ENTER".toLowerCase(), false);

		// Initialization of the four arrows
		this.map.put("FU".toLowerCase(), false);
		this.map.put("FD".toLowerCase(), false);
		this.map.put("FR".toLowerCase(), false);
		this.map.put("FL".toLowerCase(), false);

		try {
			AutomatonProvider.getInstance().loadAutomataFromFile(automataPath);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		this.initGame();
	}
	
	public void initGame() {
		World newWorld = new World(200, 200, this);
		new Player(newWorld.getTile(0, 0));
		
		this.currentWorld = newWorld;
	}

	public void writeHashMap(String key, boolean bool) {
		this.map.put(key, bool);
	}

	public boolean getBoolHashMap(String str) {
		return this.map.get(str.toLowerCase());
	}

	@Override
	public void step(long now) {
		this.getCurrentWorld().step(now);
	}

	@Override
	public void shutdown() {
		// TODO Auto-generated method stub

	}

	public World getCurrentWorld() {
		return this.currentWorld;
	}

}
