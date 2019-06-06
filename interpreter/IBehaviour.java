package interpreter;
import java.util.List;

import game.main.model.Entity;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	private IState source ;
	private List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.setSource(source) ; 
		this.setTransitions(transitions) ;
	}
	
	public IState step(Entity e) throws Exception {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		// return l'état cible de la transition choisie
		int i = 0;
		int nbTransitions = this.getTransitions().size();
		
		while (i < nbTransitions && this.getTransitions().get(i).feasible(e)) {
			i++;
		}
		
		if (i == nbTransitions)
			throw new Exception("No transition feasible");
		
		return this.getTransitions().get(i).target;
	}

	public IState getSource() {
		return source;
	}

	void setSource(IState source) {
		this.source = source;
	}

	public List<ITransition> getTransitions() {
		return transitions;
	}

	private void setTransitions(List<ITransition> transitions) {
		this.transitions = transitions;
	}
}
