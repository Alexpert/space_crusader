package interpreter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import game.main.model.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	IState current;
	List<IBehaviour> behaviours;
	List<IState> states;

	public IAutomaton(IState initial) {
		this.current = initial;
		this.behaviours = new ArrayList<>();
		this.states = new ArrayList<>();
	}

	public void addBehaviour(IBehaviour behaviour) {
		IState foundState = this.getState(behaviour.source.name);
		
		if (foundState != null)
			behaviour.source = foundState;
		
		for(ITransition transition: behaviour.transitions) {
			foundState = this.getState(transition.target.name);
			if (foundState != null)
				transition.target = foundState;
			else
				this.states.add(foundState);
		}
			
		
		this.behaviours.add(behaviour);
	}

	private IState getState(String name) {
		int i = 0;
		while (i < this.states.size() && this.states.get(i).name != name)
			i++;
		
		return i == this.states.size() ? null : this.states.get(i);
	}
	
	private IBehaviour getBehaviour(IState state) {
		int i = 0;
		while (i < this.behaviours.size() && this.behaviours.get(i).source.equals(state))
			i++;
		
		return i == this.behaviours.size() ? null : this.behaviours.get(i);
	}
	
	boolean step(Entity e) {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		// true si une transition effectuée, false si aucune transition possible (=?=
		// mort de l'automate ?)
		IBehaviour behaviour = this.getBehaviour(this.current);
		
		if (behaviour != null)
			try {
				this.current = behaviour.step(e);
				
				return true;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				System.out.println("Behaviour could no find any valid transition");
				return false;
			}
		
		
		return false; 
	}
}
