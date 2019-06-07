package interpreter;

import game.main.model.*;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class ITransition {
	private ICondition condition ;
	private IAction action ;
	private IState target ;
	
	public ITransition(ICondition condition, IAction action, IState target){
		this.setCondition(condition) ;
		this.setAction(action) ;
		this.setTarget(target) ;
	}
	
	boolean feasible(Entity e) {
		// teste si la condition de la transition est satisfaite
		return this.getCondition().eval(e);
	}
	
	IState exec(Entity e) {
		// execute l'action
		// return l'état cible de la transition 
		return this.getTarget();
	}

	public ICondition getCondition() {
		return condition;
	}

	private void setCondition(ICondition condition) {
		this.condition = condition;
	}

	public IAction getAction() {
		return action;
	}

	private void setAction(IAction action) {
		this.action = action;
	}

	public IState getTarget() {
		return target;
	}

	void setTarget(IState target) {
		this.target = target;
	}
}
