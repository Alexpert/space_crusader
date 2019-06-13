package game.main.model.action_handler;

import java.util.ArrayList;
import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Model;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.entities.Player;

public class ActionHandlerButton extends AbstractActionHandler {

	public ActionHandlerButton(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void patient() {
		// TODO Auto-generated method stub
	}

	@Override
	public void pop(Direction d) {
		Model myModel = this.entity.getWorld().getModel();
		ArrayList<World> myworlds = myModel.getWorlds();
		int width = myModel.overworld.getWidth();
		int height = myModel.overworld.getHeight();
		myModel.overworld = new World(width, height, myModel,false);
	}

	@Override
	public void wizz(Direction d) {
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
