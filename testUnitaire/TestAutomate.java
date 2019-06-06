package testUnitaire;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.BeforeClass;
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
			"}\n" +
			"SmoothDebugger(D1){\n"
			+ "* (D1):\n"
			+ "  | True ? Pop : (D2)\n"
			+ "* (D2):\n"
			+ "  | !True ? Wizz : (D2)\n"
			+ "}\n" +
			"OrDebugger(Or1){\n"
			+ "* (Or1):\n"
			+ " | True / True ? Pop : (Or2)\n"
			+ "* (Or2):\n"
			+ " | True / !True ? Pop : (Or1)\n"
			+ "}\n"+
			"AndDebugger(And1){\n"
			+ "* (And1):\n"
			+ " | True & !True ? Pop : (And1)\n"
			+ " | True & True ? Pop : (And2)\n"
			+ "* (And2):\n"
			+ "}\n";
	private static Ast ast;
	private static ArrayList<IAutomaton> automatons;
	
	@BeforeClass
	public static void init() throws Exception{
		ast = new AutomataParser(new java.io.StringReader(TestAutomate.automata)).Run();
		automatons = ((AI_Definitions) ast).make();
	}
	
	@Test
	public void testCreationMultipleAutomata() throws Exception {
		assertTrue(TestAutomate.automatons.size() == 6);
	}
	
	@Test
	public void testCountStates() throws Exception {
		assertTrue(TestAutomate.automatons.get(0).getNbStates() == 2);
		assertTrue(TestAutomate.automatons.get(1).getNbStates() == 1);
		assertTrue(TestAutomate.automatons.get(2).getNbStates() == 2);
	}
	
	@Test
	public void testCountBehaviours() throws Exception {
		assertTrue(TestAutomate.automatons.get(0).getNbBehaviours() == 2);
		assertTrue(TestAutomate.automatons.get(1).getNbBehaviours() == 1);
		assertTrue(TestAutomate.automatons.get(2).getNbBehaviours() == 2);
	}
	
	@Test
	public void testCoherenceAutomaton(){
		for (IAutomaton iAutomaton: TestAutomate.automatons) {
			for(IBehaviour behaviour:iAutomaton.getBehaviours()) {
				assert(behaviour.getSource() != null);
				assert(iAutomaton.getState(behaviour.getSource().getName()) != null);
			}
		}
	}
	
	@Test
	public void testBasicExecution(){
		Entity e = null;
		IAutomaton automaton =  TestAutomate.automatons.get(3);
		assertTrue(automaton.getCurrent().getName().equals("D1"));
		assertTrue(automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("D2"));
		assertTrue(!automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("D2"));
	}
	
	@Test
	public void testOr(){
		Entity e = null;
		IAutomaton automaton =  TestAutomate.automatons.get(4);
		assertTrue(automaton.getCurrent().getName().equals("Or1"));
		assertTrue(automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("Or2"));
		assertTrue(automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("Or1"));
	}
	
	@Test
	public void testAnd(){
		Entity e = null;
		IAutomaton automaton =  TestAutomate.automatons.get(5);
		assertTrue(automaton.getCurrent().getName().equals("And1"));
		assertTrue(automaton.step(e));
		assertTrue(automaton.getCurrent().getName().equals("And2"));

	}
}
