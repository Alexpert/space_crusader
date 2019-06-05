package interpreter;
import java.util.List;
import java.util.ListIterator;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IBehaviour {
	IState source ;
	List<ITransition> transitions ;
	
	public IBehaviour(IState source, List<ITransition> transitions){
		this.source = source ; 
		this.transitions = transitions ;
	}
	
	IState step() {
		// - selectionne la première transition faisable
		// - lève une exception si aucune transition possible
		// return l'état cible de la transition choisie
		int i = 0;
		int nbTransitions = this.transitions.size();
		
		while (i < nbTransitions && this.transitions.get(i).feasible()) {
			i++;
		}
		
		if (i == nbTransitions)
			throw new Exception("No transition feasible");
		
		return this.transitions.get(i).target;
	}
}
