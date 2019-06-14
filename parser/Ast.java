package parser;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import interpreter.*;
import interpreter.IAction.*;
import interpreter.ICondition.*;

import game.main.model.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, june 2018
 *
 * Constructors of the Abstract Syntax Tree of Game Automata
 */

public class Ast {

	// All this is only for the graphical .dot output of the Abstract Syntax Tree

	public String kind; // the name of the non-terminal node

	public int id = Id.fresh(); // a unique id used as a graph node

	// AST as tree

	public String dot_id() {
		return Dot.node_id(this.id);
	}

	public String as_tree_son_of(Ast father) {
		return Dot.edge(father.dot_id(), this.dot_id()) + this.as_dot_tree();
	}

	public String as_dot_tree() {
		return this.as_tree_node() + this.tree_edges();
	}

	public String as_tree_node() {
		return Dot.declare_node(this.dot_id(), this.kind, "");
	}

	public String tree_edges() {
		return "undefined: " + this.kind + ".tree_edges";
	}

	// AST as automata in .dot format

	public String as_dot_aut() {
		return "undefined " + this.kind + ".as_dot_aut";
	}

	// AST as active automata (interpreter of transitions)

	public Object make() throws Exception {
		return null; // TODO à définir dans la plupart des classes internes ci-dessous.
	}

	public static class Terminal extends Ast {
		String value;

		Terminal(String string) {
			this.kind = "Terminal";
			this.value = string;
		}

		public String toString() {
			return value;
		}

		public String tree_edges() {
			String value_id = Dot.node_id(-this.id);
			return Dot.declare_node(value_id, value, "shape=none, fontsize=10, fontcolor=blue")
					+ Dot.edge(this.dot_id(), value_id);
		}
	}

	// Value = Constant U Variable

	public static class Value extends Ast {
	}

	public static class Constant extends Value {

		Terminal value;

		Constant(String string) {
			this.kind = "Constant";
			this.value = new Terminal(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}
	}

	public static class Variable extends Value {

		Terminal name;

		Variable(String string) {
			this.kind = "Variable";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public String toString() {
			return name.toString();
		}
	}

	// Parameter = Underscore U Key U Direction U Entity
	// Parameter are not Expression (no recursion)

	public static abstract class Parameter extends Ast {
	}

	public static class Underscore extends Parameter {
		Underscore() {
			this.kind = "Underscore";
		}

		public String tree_edges() {
			return "";
		}

		public String toString() {
			return "_";
		}
	}

	public static class Number_as_String extends Parameter {

		Constant value;

		Number_as_String(String string) {
			this.kind = "Number";
			this.value = new Constant(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}
	}

	public static class Key extends Parameter {

		Constant value;

		Key(String string) {
			this.kind = "Key";
			this.value = new Constant(string);
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}
	}

	public static class Direction extends Parameter {

		Value value;

		Direction(Value value) {
			this.kind = "Direction";
			this.value = value;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}

		public game.main.model.Direction getDirection() throws Exception {
			game.main.model.Direction direction;
			String strDebug = (((Terminal) ((Constant) this.value).value).value).toLowerCase();

			switch (strDebug) {
			case ("f"):
				direction = game.main.model.Direction.FRONT;
				break;
			case ("r"):
				direction = game.main.model.Direction.RIGHT;
				break;
			case ("b"):
				direction = game.main.model.Direction.BACK;
				break;
			case ("l"):
				direction = game.main.model.Direction.LEFT;
				break;
			case ("n"):
				direction = game.main.model.Direction.NORTH;
				break;
			case ("e"):
				direction = game.main.model.Direction.EAST;
				break;
			case ("s"):
				direction = game.main.model.Direction.SOUTH;
				break;
			case ("w"):
				direction = game.main.model.Direction.WEST;
				break;

			default:
				throw new Exception("Unrecognized Direction");
			}

			return direction;
		}
	}

	public static class Entity extends Parameter {

		Value value;

		Entity(Value expression) {
			this.kind = "Entity";
			this.value = expression;
		}

		public String tree_edges() {
			return value.as_tree_son_of(this);
		}

		public String toString() {
			return value.toString();
		}

		public Kind getKind() throws Exception {
			Kind kind;
			switch (this.value.toString()) {
			case ("A"):
				kind = Kind.MONSTER;
				break;
			case ("D"):
				kind = Kind.DANGER;
				break;
			case ("G"):
				kind = Kind.GATE;
				break;
			case ("J"):
				kind = Kind.JUMP_ELEMENT;
				break;
			case ("M"):
				kind = Kind.MISSILE;
				break;
			case ("O"):
				kind = Kind.OBSTACLE;
				break;
			case ("P"):
				kind = Kind.ITEM;
				break;
			case ("T"):
				kind = Kind.TEAM;
				break;
			case ("V"):
				kind = Kind.VOID;
				break;
			case ("@"):
				kind = Kind.PLAYER;
				break;
			case ("_"):
				kind = Kind.ANYTHING;
				break;
			default:
				throw new Exception("Entity type invalid");
			}
			return kind;
		}
	}

	// Expression = UnaryOp Expression U Expression BinaryOp Expression U
	// FunCall(Parameters)

	public static abstract class Expression extends Ast {
		public abstract String toString();
	}
	
	public static class None extends Expression {
		None(){}
		public final String toString() { return "none" ; }
		public String tree_edges() { return "" ; } 
	}
	

	public static class UnaryOp extends Expression {

		Terminal operator;
		Expression operand;

		UnaryOp(String operator, Expression operand) {
			this.kind = "UnaryOp";
			this.operator = new Terminal(operator);
			this.operand = operand;
		}

		public String tree_edges() {
			return operator.as_tree_son_of(this) + operand.as_tree_son_of(this);
		}

		public String toString() {
			return operator + "(" + operand + ")";
		}
	}

	public static class BinaryOp extends Expression {

		Terminal operator;
		Expression left_operand;
		Expression right_operand;

		BinaryOp(Expression l, String operator, Expression r) {
			this.kind = "BinaryOp";
			this.operator = new Terminal(operator);
			this.left_operand = l;
			this.right_operand = r;
		}

		public String tree_edges() {
			return left_operand.as_tree_son_of(this) + operator.as_tree_son_of(this)
					+ right_operand.as_tree_son_of(this);
		}

		public String toString() {
			return "(" + left_operand + " " + operator + " " + right_operand + ")";
		}
	}

	public static class FunCall extends Expression {

		Terminal name;
		List<Parameter> parameters;

		FunCall(String name, List<Parameter> parameters) {
			this.kind = "FunCall";
			this.name = new Terminal(name);
			this.parameters = parameters;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				output += parameter.as_tree_son_of(this);
			}
			return output;
		}

		public String toString() {
			String string = new String();
			ListIterator<Parameter> Iter = this.parameters.listIterator();
			while (Iter.hasNext()) {
				Parameter parameter = Iter.next();
				string += parameter.toString();
				if (Iter.hasNext()) {
					string += ",";
				}
			}
			return name + "(" + string + ")";
		}
	}

	public static class Condition extends Ast {

		Expression expression;

		Condition(Expression expression) {
			this.kind = "Condition";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public String toString() {
			return expression.toString();
		}

		public ICondition make() throws Exception {
			ICondition condition = new ICondition();
			switch (expression.kind) {
			case ("UnaryOp"):
				condition = condition.new Not(new Condition(((UnaryOp) this.expression).operand).make());
				break;
			case ("BinaryOp"):
				if (((BinaryOp) this.expression).operator.value.equals("&")) {
					condition = condition.new And(new Condition((((BinaryOp) this.expression).left_operand)).make(),
							new Condition((((BinaryOp) this.expression).right_operand)).make());
				} else {
					condition = condition.new Or(new Condition((((BinaryOp) this.expression).left_operand)).make(),
							new Condition((((BinaryOp) this.expression).right_operand)).make());
				}
				break;
			case ("FunCall"):
				FunCall exprAsFunCall = (FunCall) expression;
				switch (exprAsFunCall.name.value.toLowerCase()) {
				case ("true"):
					condition = condition.new True();
					break;
				case ("key"):
					if (exprAsFunCall.parameters.size() != 1 || exprAsFunCall.parameters.get(0).kind != "Key")
						throw new Exception("Not a Key");
					condition = condition.new Key(((Key) exprAsFunCall.parameters.get(0)).value.toString());
					break;
				case ("mydir"):
					if (exprAsFunCall.parameters.size() != 1 || exprAsFunCall.parameters.get(0).kind != "Direction")
						throw new Exception("Not a Key");
					condition = condition.new MyDir(((Direction) exprAsFunCall.parameters.get(0)).getDirection());
					break;
				case ("cell"):
					if (exprAsFunCall.parameters.size() < 2 || exprAsFunCall.parameters.size() > 3)
						throw new Exception("Wrong argument count");
					if (exprAsFunCall.parameters.get(0).kind != "Direction")
						throw new Exception("Not a Direction");
					if (exprAsFunCall.parameters.get(1).kind != "Entity" && (exprAsFunCall.parameters.get(1).kind != "Underscore"))
						throw new Exception("Not an Entity");
					if (exprAsFunCall.parameters.size() > 2 && exprAsFunCall.parameters.get(2).kind != "Number")
						throw new Exception("Not a Distance");

					Kind k = exprAsFunCall.parameters.get(1).kind == "Entity"
							? (((Entity) exprAsFunCall.parameters.get(1)).getKind())
							: Kind.ANYTHING;
					condition = condition.new Cell(((Direction) exprAsFunCall.parameters.get(0)).getDirection(),k);
					if (exprAsFunCall.parameters.size() >2)
						((Cell) condition).addDistance(
							Integer.parseInt(((Number_as_String) exprAsFunCall.parameters.get(2)).value.toString()));
					break;
				case ("closest"):
					if (exprAsFunCall.parameters.size() != 2)
						throw new Exception("Wrong argument count");
					if (exprAsFunCall.parameters.get(1).kind != "Direction")
 					//C'est pas joli mais le parser reconnais "O" comme Direction et non comme en entité
					if (exprAsFunCall.parameters.get(0).kind != "Entity"
							&& exprAsFunCall.parameters.get(1).kind != "Underscore")
						throw new Exception("Not an Entity");
					Kind entity;
					if (exprAsFunCall.parameters.get(1).kind == "Direction") {
						entity = Kind.OBSTACLE;
					} else if (exprAsFunCall.parameters.get(1).kind.equals("Underscore")) {
						entity = Kind.ANYTHING;
					} else {
						entity = ((Entity) exprAsFunCall.parameters.get(0)).getKind();
					}
					condition = condition.new Closest(entity, ((Direction) exprAsFunCall.parameters.get(1)).getDirection());
					break;
				case ("gotpower"):
					condition = condition.new GotPower();
					break;
				case ("gotstuff"):
					condition = condition.new GotStuff();
					break;

				default:
					throw new Exception("Not a valid Condition FunCall");
				}
				break;

			default:
				throw new Exception("Not a valid FunCall");
			}
			return condition;
		}
	}

	public static class Action extends Ast {

		Expression expression;
		
		Action(){
			this.kind = "Action" ;
			this.expression = new None();
		}

		Action(Expression expression) {
			this.kind = "Action";
			this.expression = expression;
		}

		public String tree_edges() {
			return expression.as_tree_son_of(this);
		}

		public String toString() {
			return expression.toString();
		}

		public IAction make() throws Exception {
			if (this.expression.kind != "FunCall" && this.expression.kind != null)
				throw new Exception("IAction can only be built unsing a FunCall Expression");

			IAction iAction;
			
			if(this.expression.kind == null)
				return null;
			
			FunCall exprAsFunCall = (FunCall) this.expression;

			game.main.model.Direction direction = null;
			if (exprAsFunCall.parameters.size() > 0 && exprAsFunCall.parameters.get(0).kind == "Direction") {
				direction = ((Direction) exprAsFunCall.parameters.get(0)).getDirection();
			}
			int power = 0;
			if (exprAsFunCall.parameters.size() > 1 && exprAsFunCall.parameters.get(1).kind == "Number") {
				power = Integer.parseInt(((Number_as_String) exprAsFunCall.parameters.get(1)).value.toString());
			}

			switch (exprAsFunCall.name.value.toLowerCase()) {
			case ("wait"):
				iAction = new Wait();
				break;
			case ("wizz"):
				iAction = new Wizz();
				if (direction != null)
					((Wizz) iAction).setDirection(direction);
				break;
			case ("pop"):
				iAction = new Pop();
				if (direction != null)
					((Pop) iAction).setDirection(direction);
				break;
			case ("move"):
				iAction = new Move();
				if (direction != null)
					((Move) iAction).setDirection(direction);
				break;
			case ("jump"):
				iAction = new Jump();
				if (direction != null)
					((Jump) iAction).setDirection(direction);
				break;
			case ("turn"):
				iAction = new Turn();
				if (direction != null)
					((Turn) iAction).setDirection(direction);
				break;
			case ("hit"):
				iAction = new Hit();
				if (direction != null)
					((Hit) iAction).setDirection(direction);
				if (power != 0)
					((Hit) iAction).setPower(power);
				break;
			case ("protect"):
				iAction = new Protect();
				if (direction != null)
					((Protect) iAction).setDirection(direction);
				break;
			case ("pick"):
				iAction = new Pick();
				if (direction != null)
					((Pick) iAction).setDirection(direction);
				break;
			case ("throw"):
				iAction = new Throw();
				if (direction != null)
					((Throw) iAction).setDirection(direction);
				break;
			case ("store"):
				iAction = new Store();
				break;
			case ("get"):
				iAction = new Get();
				break;
			case ("power"):
				iAction = new Power();
				break;
			case ("kamikaze"):
				iAction = new Kamikaze();
				break;
			case ("egg"):
				iAction = new Egg();
				break;

			default:
				throw new Exception("FunCall not found");
			}

			return iAction;
		}
	}

	public static class State extends Ast {

		Terminal name;

		State(String string) {
			this.kind = "State";
			this.name = new Terminal(string);
		}

		public String tree_edges() {
			return name.as_tree_son_of(this);
		}

		public String dot_id_of_state_of(Automaton automaton) {
			return Dot.name(automaton.id + "." + name.toString());
		}

		public String as_state_of(Automaton automaton) {
			return Dot.declare_node(this.dot_id_of_state_of(automaton), name.toString(), "shape=circle, fontsize=4");
		}

		public IState make() {
			return new IState(this.name.value);
		}
	}

	public static class AI_Definitions extends Ast {

		List<Automaton> automata;

		AI_Definitions(List<Automaton> list) {
			this.kind = "AI_Definitions";
			this.automata = list;
		}

		public String tree_edges() {
			String output = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				output += automaton.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_tree() {
			return Dot.graph("AST", this.as_tree_node() + this.tree_edges());
		}

		public String as_dot_aut() {
			String string = new String();
			ListIterator<Automaton> Iter = this.automata.listIterator();
			while (Iter.hasNext()) {
				Automaton automaton = Iter.next();
				string += automaton.as_dot_aut();
			}
			return Dot.graph("Automata", string);
		}

		public ArrayList<IAutomaton> make() throws Exception {
			ArrayList<IAutomaton> iAutomata = new ArrayList<>();
			for (Automaton iAutomaton : this.automata)
				iAutomata.add(iAutomaton.make());

			return iAutomata;
		}

	}

	public static class Automaton extends Ast {

		Terminal name;
		State entry;
		List<Behaviour> behaviours;

		Automaton(String name, State entry, List<Behaviour> behaviours) {
			this.kind = "Automaton";
			this.name = new Terminal(name);
			this.entry = entry;
			this.behaviours = behaviours;
		}

		public IAutomaton make() throws Exception {
			IState istate_initial = entry.make();

			IAutomaton iAutomaton = new IAutomaton(name.value, istate_initial);

			// construction de la liste des IBehaviours
			for (Behaviour behaviour : this.behaviours)
				iAutomaton.addBehaviour((IBehaviour) behaviour.make());

			return iAutomaton;
		}

		public String tree_edges() {
			String output = new String();
			output += name.as_tree_son_of(this);
			output += entry.as_tree_son_of(this);
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				output += behaviour.as_tree_son_of(this);
			}
			return output;
		}

		public String as_dot_aut() {
			String string = new String();
			string += Dot.declare_node(this.dot_id(), name.toString(), "shape=box, fontcolor=red");
			string += Dot.edge(this.dot_id(), entry.dot_id_of_state_of(this));
			ListIterator<Behaviour> Iter = this.behaviours.listIterator();
			while (Iter.hasNext()) {
				Behaviour behaviour = Iter.next();
				string += behaviour.as_transition_of(this);
			}
			return Dot.subgraph(this.id, string);
		}

	}

	public static class Behaviour extends Ast {

		State source;
		List<Transition> transitions;

		Behaviour(State state, List<Transition> transitions) {
			this.kind = "Behaviour";
			this.source = state;
			this.transitions = transitions;
		}

		public String tree_edges() {
			String output = new String();
			output += source.as_tree_son_of(this);
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				output += transition.as_tree_son_of(this);
			}
			return output;
		}

		public String as_transition_of(Automaton automaton) {
			String string = new String();
			ListIterator<Transition> Iter = this.transitions.listIterator();
			while (Iter.hasNext()) {
				Transition transition = Iter.next();
				string += transition.as_transition_from(automaton, source);
			}
			return source.as_state_of(automaton) + string;
		}

		public IBehaviour make() throws Exception {
			IState iState = source.make();

			ArrayList<ITransition> iTransitions = new ArrayList<>();
			for (Transition transition : this.transitions)
				iTransitions.add(transition.make());

			return new IBehaviour(iState, iTransitions);
		}
	}

	public static class Transition extends Ast {

		Condition condition;
		Action action;
		State target;

		Transition(Condition condition, Action action, State target) {
			this.kind = "Transition";
			this.condition = condition;
			this.action = action;
			this.target = target;
		}

		public ITransition make() throws Exception {
			return new ITransition(condition.make(), action.make(), target.make());
		}

		public String tree_edges() {
			return condition.as_tree_son_of(this) + action.as_tree_son_of(this) + target.as_tree_son_of(this);
		}

		public String toString() {
			return condition + "? " + action;
		}

		public String as_transition_from(Automaton automaton, State source) {
			String string = new String();
			string += Dot.declare_node(this.dot_id(), this.toString(), "shape=box, fontcolor=blue, fontsize=6");
			string += Dot.edge(source.dot_id_of_state_of(automaton), this.dot_id());
			string += Dot.edge(this.dot_id(), target.dot_id_of_state_of(automaton));
			return string;
		}
	}
}
