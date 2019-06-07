package interpreter;

import java.util.ArrayList;

import parser.Ast;
import parser.Ast.AI_Definitions;
import parser.AutomataParser;

public class Interpreter {
	public static ArrayList<IAutomaton> initAutomata(String path) throws Exception {
		Ast ast = AutomataParser.from_file(path);
		return ((AI_Definitions) ast).make();
	}
}
