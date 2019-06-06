package interpreter;

/* Michael PÉRIN, Verimag / Univ. Grenoble Alpes, may 2019 */

public class IState {
	private int id;
	private String name ;
	// Le nom de l'état (Waiting, Hungry, Angry, ...) peut vous servir à adapter la représentation de l'entité à son humeur. 
	
	public IState(String name){
		this.setName(name) ;
	}
	
	public String toString() {
		return this.getName();
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
}
