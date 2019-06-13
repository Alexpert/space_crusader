package game.main.model.action_handler;

import java.awt.Label;

import javax.swing.JFrame;

import game.main.model.Direction;
import game.main.model.Entity;

public class GraalActionHandler extends AbstractActionHandler {
	
	static boolean hasWon = false;
	
	public GraalActionHandler(Entity entity) {
		this.entity = entity;
	}

	@Override
	public void patient() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz(Direction d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pop(Direction d) {
		System.out.println("FIN DU JEU");

		if (!hasWon) {
			JFrame frame= new JFrame();	
			frame.setTitle("Félécitations");
			frame.setSize(500, 300);
			frame.setLocation(200, 200);
			frame.setVisible(true);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.add(new Label("Bien joué, \n Vous avez trouvé le Graal!"));
			hasWon = true;
		}
	}

	@Override
	public boolean jump(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hit(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean protect(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean pick(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cast(Direction d) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean store() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean power() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean kamikaze() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean egg() {
		// TODO Auto-generated method stub
		return false;
	}

}
