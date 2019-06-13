package game.main.model.action_handler;

import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.entities.Player;

public class ActionHandlerGate extends AbstractActionHandler {

	public ActionHandlerGate(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void patient() {
		// TODO Auto-generated method stub

	}

	@Override
	public void wizz(Direction d) {
		//We choose randomly two coordinates
		int randomX = new Random().nextInt(this.entity.getTile().getWorld().getWidth());
		int randomY = new Random().nextInt(this.entity.getTile().getWorld().getHeight());
		//If the case with this coordinates isn't empty, we select an other one
		while(! this.entity.getTile().getWorld().getTile(randomX, randomY).isEmpty()) {
			randomX = new Random().nextInt(this.entity.getTile().getWorld().getWidth());
			randomY = new Random().nextInt(this.entity.getTile().getWorld().getHeight());
		}
		//The gate is teleported to this new coordinates
		this.entity.setTile(this.entity.getTile().getWorld().getTile(randomX, randomY));
	}

	@Override
	public void pop(Direction d) {
		//The first world in the arraylist is the planet
		//The second world in the arraylist is the spaceship
		World tmpWorld = this.entity.getWorld();
		World otherWorld;
		Player player = tmpWorld.getModel().getPlayer();
		//We withdraw the entity of its world
		tmpWorld.getTile(this.entity.getX(), this.entity.getY()).remove(player);
		//Case where the currentworld is the planet (and that the player would like to return to its ship)
		if(tmpWorld == tmpWorld.getModel().getWorlds().get(0)) {
			otherWorld = this.entity.getWorld().getModel().getWorlds().get(1);
			//We give to the player its new Action HAndler
			player.setActionHandler(new ActionHandlerRabbit(player));
		}
		else {
			//Case where the currentworld is the spaceship (and that the player gets out to the planet)
			otherWorld = this.entity.getWorld().getModel().getWorlds().get(0);
			//We give to the player its new Action HAndler
			player.setActionHandler(new ActionHandlerPlayer(player));
		}
		//We update the currentWorld
		this.entity.getWorld().getModel().setCurrenWorld(otherWorld);
		
		//The player spawns at the right of the gate
		Tile start = otherWorld.getTile(this.entity.getX() + 1, this.entity.getY());
		player.setTile(start);
		otherWorld.add(player);
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
