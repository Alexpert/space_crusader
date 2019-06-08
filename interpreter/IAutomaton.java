package interpreter;

import java.util.ArrayList;

import game.main.model.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IAutomaton {
	private String name;
	private IState current;
	private ArrayList<IBehaviour> behaviours;
	private ArrayList<IState> states;

	public IAutomaton(String name, IState initial) {
		this.setName(name);
		this.setCurrent(initial);
		this.setBehaviours(new ArrayList<>());
		this.setStates(new ArrayList<>());
		this.getStates().add(initial);
	}

	public IAutomaton(IAutomaton iAutomaton) {
		this.setName(iAutomaton.getName());
		this.setCurrent(iAutomaton.getCurrent());
		this.setBehaviours(iAutomaton.getBehaviours());
		this.setStates(iAutomaton.getStates());
	}

	public void addBehaviour(IBehaviour behaviour) {
		IState foundState = this.getState(behaviour.getSource().getName());
		
		if (foundState != null)
			behaviour.setSource(foundState);
		
		for(ITransition transition: behaviour.getTransitions()) {
			foundState = this.getState(transition.getTarget().getName());
			if (foundState != null)
				transition.setTarget(foundState);
			else
				this.getStates().add(transition.getTarget());
		}
			
		
		this.getBehaviours().add(behaviour);
	}

	public IState getState(String name) {
		int i = 0;
		while (i < this.getStates().size() && !this.getStates().get(i).getName().equals(name))
			i++;
		
		return i == this.getStates().size() ? null : this.getStates().get(i);
	}
	
	public IBehaviour getBehaviour(IState state) {
		int i = 0;
		while (i < this.getBehaviours().size() && !this.getBehaviours().get(i).getSource().equals(state))
			i++;
		
		return i == this.getBehaviours().size() ? null : this.getBehaviours().get(i);
	}
	
	public boolean step(Entity e) {
		// - selectionne le comportement en fonction de l'état courant
		// - effectue une transition
		// - met à jour l'état courant
		// - gère l'exception "aucune transition possible"
		// true si une transition effectuée, false si aucune transition possible (=?=
		// mort de l'automate ?)
		IBehaviour behaviour = this.getBehaviour(this.getCurrent());
		
		if (behaviour != null)
			try {
				this.setCurrent(behaviour.step(e));
				
				return true;
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				//System.out.println("Behaviour could no find any valid transition");
				return false;
			}
		
		
		return false; 
	}

	public int getNbStates() {
		return this.getStates().size();
	}
	
	public int getNbBehaviours() {
		return this.getBehaviours().size();
	}

	public IState getCurrent() {
		return current;
	}

	private void setCurrent(IState current) {
		this.current = current;
	}

	public ArrayList<IBehaviour> getBehaviours() {
		return behaviours;
	}

	private void setBehaviours(ArrayList<IBehaviour> behaviours) {
		this.behaviours = behaviours;
	}

	public ArrayList<IState> getStates() {
		return states;
	}

	private void setStates(ArrayList<IState> states) {
		this.states = states;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
