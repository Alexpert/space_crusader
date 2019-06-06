package testUnitaire;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.Test;

import interpreter.IAutomaton;
import parser.Ast;
import parser.Ast.AI_Definitions;
import parser.AutomataParser;

public class TestAutomate {
	@Test
	public void testCreationMultipleAutomata(){
		String pathFile = ;//TODO
		Ast ast = new AutomataParser(new BufferedReader(new FileReader(pathFile))).Run();
		ArrayList<IAutomaton> automatons = ((AI_Definitions) ast).make();
		assertTrue(automatons.size() == ???); //TODO

	}
	
	@Test
	public void testCountStates(){
		String pathFile = ;//TODO
		Ast ast = new AutomataParser(new BufferedReader(new FileReader(pathFile))).Run();
		ArrayList<IAutomaton> automatons = ((AI_Definitions) ast).make();
		assertTrue(automatons.size() == ???); //TODO

	}
	
	@Test
	public void testCountBehaviours(){

	}
	
	@Test
	public void testCoherenceAutomaton(){

	}
	
	@Test
	public void testBasicExecution(){

	}
	
	@Test
	public void testComplexExecution(){

	}
}
