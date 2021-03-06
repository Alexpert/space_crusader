package testUnitaire;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import interpreter.IAction;
import interpreter.IAutomaton;
import interpreter.IBehaviour;
import interpreter.ICondition;
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
			"    | Cell(W,V,1) ? Move(O) : (MvWest)\n" + 
			"    | !Cell(W,V,1) ? Pop() : (MvEast)\n" + 
			"}\n" 
			+ 
			"Rebound(Mv){\n" + 
			"  *(Mv):\n" + 
			"    |Cell(F,V,1) ? Move(F) : (Mv)\n" + 
			"    |!Cell(F,V,1) ? Turn(B) : (Mv)\n" + 
			"}\n" + 
			"\n" + 
			"LittleTurn(Mv){\n" + 
			"  *(Mv): True ? Move(F) : (Trn)\n" + 
			"  *(Trn): True ? Turn(R) : (Mv)\n" + 
			"}\n" 
			+
			"SmoothDebugger(D1){\n"
			+ "* (D1):\n"
			+ "  | True ? Pop : (D2)\n"
			+ "* (D2):\n"
			+ "  | !True ? Wizz : (D2)\n"
			+ "}\n" 
			+
			"OrDebugger(Or1){\n"
			+ "* (Or1):\n"
			+ " | True / True ? Pop : (Or2)\n"
			+ "* (Or2):\n"
			+ " | True / !True ? Pop : (Or1)\n"
			+ "}\n"
			+
			"AndDebugger(And1){\n"
			+ "* (And1):\n"
			+ " | True & !True ? Pop : (And1)\n"
			+ " | True & True ? Pop : (And2)\n"
			+ "* (And2):\n"
			+ "}\n" 
			+
			"ActionDubugger(WaitAction){"
			+ "* (WaitAction):"
			+ "| True ? Wait : (WizzAction)"
			+ "* (WizzAction):"
			+ "| True ? Wizz : (PopAction)"
			+ "* (PopAction):"
			+ "| True ? Pop : (StoreAction)"
			+ "* (StoreAction):"
			+ "| True ? Store : (GetAction)"
			+ "* (GetAction):"
			+ "| True ? Get : (PowerAction)"
			+ "* (PowerAction):"
			+ "| True ? Power : (KamikazeAction)"
			+ "* (KamikazeAction):"
			+ "| True ? Kamikaze : (EggAction)"
			+ "* (EggAction):"
			+ "| True ? Egg : (EggAction)"
			+ "}"
			+
			"WizzDebugger(W1){"
			+ "* (W1):"
			+ "| True ? Wizz(F) : (W1)"
			+ "| True ? Wizz(B) : (W1)"
			+ "| True ? Wizz(L) : (W1)"
			+ "| True ? Wizz(R) : (W1)"
			+ "| True ? Wizz(N) : (W1)"
			+ "| True ? Wizz(S) : (W1)"
			+ "| True ? Wizz(E) : (W1)"
			+ "| True ? Wizz(W) : (W1)"
			+ "}"
			+
			"WizzDebugger(W1){"
			+ "* (W1):"
			+ "| True ? Wizz() : (P1)"
			+ "| True ? Wizz(N) : (P1)"
			+ "}"
			+
			"PopDebugger(W1){"
			+ "* (P1):"
			+ "| True ? Pop() : (P1)"
			+ "| True ? Pop(N) : (P1)"
			+ "}"
			+
			"MoveDebugger(W1){"
			+ "* (M1):"
			+ "| True ? Move() : (M1)"
			+ "| True ? Move(N) : (M1)"
			+ "}"
			+
			"JumpDebugger(W1){"
			+ "* (J1):"
			+ "| True ? Jump() : (J1)"
			+ "| True ? Jump(N) : (J1)"
			+ "}"
			+
			"TurnDebugger(W1){"
			+ "* (T1):"
			+ "| True ? Turn() : (T1)"
			+ "| True ? Turn(N) : (T1)"
			+ "}"
			+
			"HitDebugger(W1){"
			+ "* (H1):"
			+ "| True ? Hit() : (H1)"
			+ "| True ? Hit(N) : (H1)"
			+ "| True ? Hit(N, 3) : (H1)"
			+ "}"
			+
			"ProtectDebugger(W1){"
			+ "* (P1):"
			+ "| True ? Protect() : (P1)"
			+ "| True ? Protect(N) : (P1)"
			+ "}"
			+
			"PickDebugger(W1){"
			+ "* (P1):"
			+ "| True ? Pick() : (P1)"
			+ "| True ? Pick(N) : (P1)"
			+ "}"
			+
			"ThrowDebugger(W1){"
			+ "* (T1):"
			+ "| True ? Throw() : (T1)"
			+ "| True ? Throw(N) : (T1)"
			+ "}"
			+
			"ConditionDebugger(C1){"
			+ "* (C1):"
			+ "| GotPower() ? Pop() : (C1)"
			+ "| GotStuff() ? Pop() : (C1)"
			+ "}"
			+
			"MyDirDebugger(MD1){"
			+ "* (MD1):"
			+ "| MyDir(N) ? Pop() : (MD1)"
			+ "}"
			+
			"ClosestDebugger(MD1){"
			+ "* (MD1):"
			+ "| Closest(A, N) ? Pop() : (MD1)"
			+ "}"
			+
			"CellDebugger(C1){"
			+ "* (C1):"
			+ "| Cell(N, A) ? Pop() : (C1)"
			+ "| Cell(N, A, 5) ? Pop() : (C1)"
			+ "}"
			+
			"EntityDebugger(E1){"
			+ "* (E1):"
			+ "| Closest(A, N) ? Pop() : (E1)"
			+ "| Closest(D, N) ? Pop() : (E1)"
			+ "| Closest(G, N) ? Pop() : (E1)"
			+ "| Closest(J, N) ? Pop() : (E1)"
			+ "| Closest(M, N) ? Pop() : (E1)"
			+ "| Closest(O, N) ? Pop() : (E1)"
			+ "| Closest(P, N) ? Pop() : (E1)"
			+ "| Closest(T, N) ? Pop() : (E1)"
			+ "| Closest(V, N) ? Pop() : (E1)"
			+ "| Closest(@, N) ? Pop() : (E1)"
			+ "| Closest(_, N) ? Pop() : (E1)"
			+ "}" +
			"OptionalAction(OA1) {"
			+ "* (OA1)"
			+ "| True ? : (OA2)"
			+ "* (OA2)"
			+ "}"
			;
	private static Ast ast;
	private static ArrayList<IAutomaton> automatons;
	
	@BeforeClass
	public static void init() throws Exception{
		ast = new AutomataParser(new java.io.StringReader(TestAutomate.automata)).Run();
		automatons = ((AI_Definitions) ast).make();
		assertTrue(automatons.get(0).getName().equals("ReboundPW"));
		assertTrue(automatons.get(1).getName().equals("Rebound"));
		assertTrue(automatons.get(2).getName().equals("LittleTurn"));
	}
	
	@Test
	public void testCreationMultipleAutomata() throws Exception {
		assertTrue(TestAutomate.automatons.size() == automata.chars().filter(ch -> ch == '{').count());
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
	
	@Test
	public void testActions(){
		Entity e = null;
		IAutomaton automaton =  TestAutomate.automatons.get(6);
		assertTrue(automaton.getCurrent().getName().equals("WaitAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Wait.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("WizzAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Wizz.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("PopAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Pop.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("StoreAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Store.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("GetAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Get.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("PowerAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Power.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("KamikazeAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Kamikaze.class);
		
		automaton.step(e);
		
		assertTrue(automaton.getCurrent().getName().equals("EggAction"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction().getClass() == IAction.Egg.class);
	}
	
	@Test
	public void testDirection() {
		IAutomaton automaton =  TestAutomate.automatons.get(7);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.BACK);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(2).getAction()).getDirection() == Direction.LEFT);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(3).getAction()).getDirection() == Direction.RIGHT);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(4).getAction()).getDirection() == Direction.NORTH);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(5).getAction()).getDirection() == Direction.SOUTH);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(6).getAction()).getDirection() == Direction.EAST);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(7).getAction()).getDirection() == Direction.WEST);
	}
	
	@Test
	public void testWizz() {
		IAutomaton automaton =  TestAutomate.automatons.get(8);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Wizz)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testPop() {
		IAutomaton automaton =  TestAutomate.automatons.get(9);
		assertTrue(((IAction.Pop)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Pop)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testMove() {
		IAutomaton automaton =  TestAutomate.automatons.get(10);
		assertTrue(((IAction.Move)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Move)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testJump() {
		IAutomaton automaton =  TestAutomate.automatons.get(11);
		assertTrue(((IAction.Jump)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Jump)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testTurn() {
		IAutomaton automaton =  TestAutomate.automatons.get(12);
		assertTrue(((IAction.Turn)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Turn)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testHit() {
		IAutomaton automaton =  TestAutomate.automatons.get(13);
		assertTrue(((IAction.Hit)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Hit)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getPower() == 1);
		
		assertTrue(((IAction.Hit)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
		assertTrue(((IAction.Hit)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getPower() == 1);
		
		assertTrue(((IAction.Hit)automaton.getBehaviours().get(0)
				.getTransitions().get(2).getAction()).getDirection() == Direction.NORTH);
		assertTrue(((IAction.Hit)automaton.getBehaviours().get(0)
				.getTransitions().get(2).getAction()).getPower() == 3);
	}
	
	@Test
	public void testProtect() {
		IAutomaton automaton =  TestAutomate.automatons.get(14);
		assertTrue(((IAction.Protect)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Protect)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testPick() {
		IAutomaton automaton =  TestAutomate.automatons.get(15);
		assertTrue(((IAction.Pick)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Pick)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testThrow() {
		IAutomaton automaton =  TestAutomate.automatons.get(16);
		assertTrue(((IAction.Throw)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getAction()).getDirection() == Direction.FRONT);
		assertTrue(((IAction.Throw)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getAction()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testConditions() {
		IAutomaton automaton =  TestAutomate.automatons.get(17);
		assertTrue(automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition().getClass() == ICondition.GotPower.class);
		assertTrue(automaton.getBehaviours().get(0)
				.getTransitions().get(1).getCondition().getClass() == ICondition.GotStuff.class);
	}
	
	@Test
	public void testMyDir() {
		IAutomaton automaton =  TestAutomate.automatons.get(18);
		assertTrue(((ICondition.MyDir)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getDirection() == Direction.NORTH);
	}
	
	@Test
	public void testClosest() {
		IAutomaton automaton =  TestAutomate.automatons.get(19);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getDirection() == Direction.NORTH);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getKind() == Kind.MONSTER);
	}
	
	@Test
	public void testCell() {
		IAutomaton automaton =  TestAutomate.automatons.get(20);

		assertTrue(((ICondition.Cell)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getDirection() == Direction.NORTH);
		assertTrue(((ICondition.Cell)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getKind() == Kind.MONSTER);
		assertTrue(((ICondition.Cell)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getDistance() == 1);
		
		assertTrue(((ICondition.Cell)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getCondition()).getDirection() == Direction.NORTH);
		assertTrue(((ICondition.Cell)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getCondition()).getKind() == Kind.MONSTER);
		assertTrue(((ICondition.Cell)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getCondition()).getDistance() == 5);
	}
	
	@Test
	public void testEntities() {
		IAutomaton automaton =  TestAutomate.automatons.get(21);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(0).getCondition()).getKind() == Kind.MONSTER);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(1).getCondition()).getKind() == Kind.DANGER);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(2).getCondition()).getKind() == Kind.GATE);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(3).getCondition()).getKind() == Kind.JUMP_ELEMENT);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(4).getCondition()).getKind() == Kind.MISSILE);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(5).getCondition()).getKind() == Kind.OBSTACLE);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(6).getCondition()).getKind() == Kind.ITEM);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(7).getCondition()).getKind() == Kind.TEAM);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(8).getCondition()).getKind() == Kind.VOID);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(9).getCondition()).getKind() == Kind.PLAYER);
		assertTrue(((ICondition.Closest)automaton.getBehaviours().get(0)
				.getTransitions().get(10).getCondition()).getKind() == Kind.ANYTHING);
	}
	
	@Test
	public void testOptionalAction() {
		IAutomaton automaton =  TestAutomate.automatons.get(22);
		assertTrue(automaton.getCurrent().getName().equals("OA1"));
		assertTrue(automaton.getBehaviour(automaton.getCurrent()).getTransitions()
				.get(0).getAction() == null);
		automaton.step(null);
		assertTrue(automaton.getCurrent().getName().equals("OA2"));
		
	}
}
