package game.main.model.action_handler;

import java.util.ArrayList;
import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.entities.Player;
import game.main.music.SoundProvider;
import game.main.music.WorldSoundHandler;

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
		Random random = new Random();
		
		//We choose randomly two coordinates
		int randomX = random.nextInt(this.entity.getWorld().getWidth());
		int randomY = random.nextInt(this.entity.getWorld().getHeight());
		//If the case with this coordinates isn't empty, we select an other one
		while(! this.entity.getWorld().getTile(randomX, randomY).isEmpty()) {
			randomX = random.nextInt(this.entity.getWorld().getWidth());
			randomY = random.nextInt(this.entity.getWorld().getHeight());
		}
		//The entity is teleported to this new coordinates
		ArrayList<Entity> entities= new ArrayList<>(this.entity.getTile().getEntities());
		entities.removeIf(e -> e.getKind() == Kind.GATE);
		entities.get(0).moveToTile(randomX, randomY);
		
		System.out.println("Wizz Gate");
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
			
			//We manage the music
			otherWorld.getWorldSoundHander().stop();
			tmpWorld.setSoundHandler(new WorldSoundHandler(tmpWorld));
			tmpWorld.setMusic(SoundProvider.getInstance().getSound("assets/music/ambiance_vaisseau.wav"));
			tmpWorld.getWorldSoundHander().start();
		}
		else {
			//Case where the currentworld is the spaceship (and that the player gets out to the planet)
			otherWorld = this.entity.getWorld().getModel().getWorlds().get(0);
			//We give to the player its new Action HAndler
			player.setActionHandler(new ActionHandlerPlayer(player));
			
			//We manage the music
			otherWorld.getWorldSoundHander().stop();
			tmpWorld.setSoundHandler(new WorldSoundHandler(tmpWorld));
			tmpWorld.setMusic(SoundProvider.getInstance().getSound("assets/music/ambiance_monde.wav"));
			tmpWorld.getWorldSoundHander().start();
		}
		//We update the currentWorld
		this.entity.getWorld().getModel().setCurrenWorld(otherWorld);
		
		//The player spawns at the right of the gate
		Tile start = otherWorld.getTile(this.entity.getX() + 1, this.entity.getY());
		player.setTile(start);
		otherWorld.add(player);

		System.out.println("Pop Gate");
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
