package testUnitaire;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import game.main.model.Entity;
import interpreter.IAutomaton;
import interpreter.IBehaviour;
import parser.Ast;
import parser.Ast.AI_Definitions;
import parser.AutomataParser;

public class TestAutomate {
	private static String automata =
			"ReboundPW(MvEast){\n" + 
			"  * (MvEast):\n" + 
			"    | Cell(E,V,1) ? Move(E) : (MvEast)\n" + 
			"    | !Cell(E,V,1) ? Pop() : (MvWest)\n" + 
			"  * (MvWest):\n" + 
			"    | Cell(O,V,1) ? Move(O) : (MvWest)\n" + 
			"    | !Cell(O,V,1) ? Pop() : (MvEast)\n" + 
			"}\n" + 
			"\n" + 
			"Rebound(Mv){\n" + 
			"  *(Mv):\n" + 
			"    |Cell(F,V,1) ? Move(F) : (Mv)\n" + 
			"    |!Cell(F,V,1) ? Turn(B) : (Mv)\n" + 
			"}\n" + 
			"\n" + 
			"LittleTurn(Mv){\n" + 
			"  *(Mv): True ? Move(F) : (Trn)\n" + 
			"  *(Trn): True ? Turn(R) : (Mv)\n" + 
			"}" +
			"SmoothDebugger(D1){"
			+ "* (D1):"
			+ "  | True ? Pop : (D2)"
			+ "* (D2):"
			+ "  | !True ? Wizz : (D2)"
			+ "}";
	private Ast ast;
	private ArrayList<IAutomaton> automatons;
	
	@Test
	public void testMain() throws Exception{
		this.ast = new AutomataParser(new java.io.StringReader(this.automata)).Run();
		this.automatons = ((AI_Definitions) ast).make();
		
		testCreationMultipleAutomata();
		testCountStates();
		testCountBehaviours();
		testCoherenceAutomaton();
		testBasicExecution();
	}
	
	public void testCreationMultipleAutomata() throws Exception {
		assertTrue(this.automatons.size() == 4);
	}
	
	public void testCountStates() throws Exception {
		assertTrue(this.automatons.get(0).getNbStates() == 2);
		assertTrue(this.automatons.get(1).getNbStates() == 1);
		assertTrue(this.automatons.get(2).getNbStates() == 2);
	}
	
	public void testCountBehaviours() throws Exception {
		assertTrue(this.automatons.get(0).getNbBehaviours() == 2);
		assertTrue(this.automatons.get(1).getNbBehaviours() == 1);
		assertTrue(this.automatons.get(2).getNbBehaviours() == 2);
	}
	
	public void testCoherenceAutomaton(){
		for (IAutomaton iAutomaton: this.automatons) {
			for(IBehaviour behaviour:iAutomaton.getBehaviours()) {
				assert(behaviour.getSource() != null);
				assert(iAutomaton.getState(behaviour.getSource().getName()) != null);
			}
		}
	}
	
	public void testBasicExecution(){
		Entity e = null;
		IAutomaton automaton =  this.automatons.get(3);
		assertTrue(automaton.getCurrent().getName().equals("D1"));
		assertTrue(automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("D2"));
		assertTrue(!automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("D2"));
	}
	
	public void testComplexExecution(){

	}
}
