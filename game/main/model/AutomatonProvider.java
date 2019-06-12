package game.main.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import interpreter.IAutomaton;
import interpreter.Interpreter;

public class AutomatonProvider {
	private static AutomatonProvider instance = null;
	
	private HashMap<String, IAutomaton> automata;
	
	private AutomatonProvider() {
		this.automata = new HashMap<>();
	}
	
	public static AutomatonProvider getInstance() {
		if (instance == null) {
			instance = new AutomatonProvider();
		}
		
		return instance;
	}
	
	public void loadAutomataFromFile(String path) throws Exception {
		ArrayList<IAutomaton> newAutomata = Interpreter.initAutomata(path);
		
		for (IAutomaton iAutomaton: newAutomata) {
			this.automata.put(iAutomaton.getName(), iAutomaton);
			System.out.println("loaded automaton '" + iAutomaton.getName() + "' from '" + path +"'");
		}
	}
	
	public IAutomaton getAutomaton(String name) {
		IAutomaton refAutomaton = this.automata.get(name);
		
		if (refAutomaton == null) {
			System.out.println("Automaton not found: " + name);
			System.out.println(this.automata);
			return null;
		}
		
		return new IAutomaton(refAutomaton);
	}
	
	public String[] getAllAutomatonNames(){
		String[] l = new String[automata.size()];
		Iterator<String> iter = this.automata.keySet().iterator();
		int i=0;
		while(iter.hasNext()) {
			l[i]=iter.next();
			i++;
		}
		return l;
	}
}
