package game.main.model.action_handler;

import game.main.model.Direction;
import game.main.model.Entity;
import game.main.model.Tile;
import game.main.model.entities.CompanyRabbit;
import game.main.model.entities.DroppedBomb;
import game.main.model.items.Apple;
import game.main.model.items.Bomb;
import game.main.view.TextureProvider;
import game.main.view.painters.CompanyRabbitPainter;

public class ActionHandlerCompanyRabbit extends AbstractActionHandler {

	public ActionHandlerCompanyRabbit(Entity e) {
		this.entity = e;
	}
	
	@Override
	public void patient() {
		this.entity.setActionTimer(500);

	}

	@Override
	public void wizz(Direction d) {
		if(this.entity instanceof CompanyRabbit) {
			CompanyRabbit rabbit = (CompanyRabbit) this.entity;
			CompanyRabbitPainter painter = (CompanyRabbitPainter) rabbit.getIPainter();
			if(rabbit.getInitialColor()) {
				rabbit.setInitialColor(false);
				painter.setTextureWizz();
			}
			else {
				rabbit.setInitialColor(true);
				painter.resetTextureWizz();
			}
		}

	}

	@Override
	public void pop(Direction d) {
		this.entity.setActionTimer(100);
		System.out.println(this.entity.getClass());
		if(this.entity instanceof CompanyRabbit) {
			CompanyRabbit rabbit = (CompanyRabbit) this.entity;
			rabbit.setCounter();
			System.out.println(rabbit.getCounter());
			if(rabbit.getCounter() == 5) {
				Tile tile = this.entity.getTile(Direction.EAST);
				if(tile.isEmpty()) {
					Apple a = new Apple();
					a.dropAtTile(tile);
				}
			}
			else if(rabbit.getCounter() == 20) {
				Tile tile = this.entity.getTile(Direction.NORTH);
				if(tile.isEmpty()) {
					Bomb b = new Bomb();
					b.dropAtTile(tile);
				}
			}
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
