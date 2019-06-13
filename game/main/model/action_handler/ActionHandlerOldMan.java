package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Tile;
import game.main.model.World;
import game.main.model.entities.Player;
import game.main.model.items.Apple;
import game.main.model.items.Bomb;
import game.main.model.items.Fur;

public class ActionHandlerOldMan extends AbstractActionHandler {

	int maxApple = 5, maxBomb = 3, maxFur = 8;
	int currentApple, currentBomb, currentFur;
	int appleCost = 10, bombCost = 8, furCost = 3;
	boolean availableApple, availableBomb, availableFur;
	Tile appleTile, bombTile, furTile;
	
	public ActionHandlerOldMan(Entity e) {
		this.entity = e;
		this.appleTile = entity.getWorld().getTile(entity.getX()-1, entity.getY()+1);
		this.bombTile = entity.getWorld().getTile(entity.getX(), entity.getY()+1);
		this.furTile = entity.getWorld().getTile(entity.getX()+1, entity.getY()+1);
	}

	@Override
	public void patient() {
		return;
	}

	@Override
	public void pop(Direction d) {//stock up
		currentApple = maxApple;
		currentBomb = maxBomb;
		currentFur = maxFur;
	}

	@Override
	public void wizz(Direction d) {//sell
		Player player = entity.getWorld().getModel().getPlayer();
		if (availableApple & (player.getTile() == appleTile)) {
			availableApple = false;
			player.takeMoney(-appleCost);
			currentApple--;
		}
		if (availableBomb & (player.getTile() == bombTile)) {
			availableBomb = false;
			player.takeMoney(-bombCost);
			currentBomb--;
		}
		if (availableFur & (player.getTile() == furTile)) {
			availableFur = false;
			player.takeMoney(-furCost);
			currentFur--;
		}
		
	}

	@Override
	public boolean jump(Direction d) {
		return true;
	}

	@Override
	public boolean hit(Direction d) {
		Tile playerTile = entity.getWorld().getModel().getPlayer().getTile();
		if (!availableApple & (currentApple > 0) & !(appleTile.equals(playerTile))) {
			new Apple().dropAtTile(appleTile);
			availableApple = true;
		}
		if (!availableBomb & (currentBomb > 0) & !(bombTile.equals(playerTile))) {
			new Bomb().dropAtTile(bombTile);
			availableBomb = true;
		}
		if (!availableFur & (currentFur > 0) & !(furTile.equals(playerTile))) {
			new Fur().dropAtTile(furTile);
			availableFur = true;
		}
		return availableApple & availableBomb & availableFur;
	}

	@Override
	public boolean protect(Direction d) {
		return true;
	}

	@Override
	public boolean pick(Direction d) {
		return true;
	}

	@Override
	public boolean cast(Direction d) {
		return true;
	}

	@Override
	public boolean store() {
		return true;
	}

	@Override
	public boolean get() {
		return true;
	}

	@Override
	public boolean power() {
		if(this.entity.getHealth() > 0 ) {
			this.entity.takeDamage(-(Integer.MAX_VALUE-this.entity.getHealth()));
			return true;
		}
		return false;
	}

	@Override
	public boolean kamikaze() {
		this.entity.setHealth(0);
		return true;
	}

	@Override
	public boolean egg() {
		return true;
	}


}
