package game.main.model.action_handler;

import java.util.Random;

import game.main.model.Direction;
import game.main.model.Entity;

public class ActionHandlerRabbit extends AbstractActionHandler {
	
	public ActionHandlerRabbit(Entity e) {
		this.entity = e;
	}
	
	@Override
	public boolean move(Direction d) {
		super.move(d);
		this.entity.setActionTimer(500);
		return true;
	}
	
	@Override
	public void patient() {
		Random random = new Random();
		this.entity.setActionTimer(random.nextInt(10000));
	}
	
	@Override
	public void wizz(Direction d) {
		// TODO dig a hole and disappear 
	}
	
	@Override
	public  void pop(Direction d){
		// TODO transform if surround by rabbit
	}
	
	@Override
	public  boolean jump(Direction d){
		// TODO 
		return false;
	}
	
	@Override
	public  boolean hit(Direction d){
		// TODO don't hit
		return false;
	}
	
	@Override
	public  boolean protect(Direction d){
		// TODO don't protect
		return false;
	}
	
	@Override
	public  boolean pick(Direction d){
		// TODO don't pick
		return false;
	}
	
	@Override
	public  boolean store(){
		// TODO don't store
		return false;
	}
	
	@Override
	public  boolean get(){
		// TODO don't get
		return false;
	}
	
	@Override
	public  boolean power(){
		if(this.entity.getHealth() > 0) {
			this.entity.addHealth(1);
			return true;
		}
		return false;
	}
	
	@Override
	public  boolean kamikaze(){
		this.entity.addHealth(0);
		return true;
	}
	
	@Override
	public  boolean egg(){
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean cast(Direction d) {
		// TODO don't cast
		return false;
	}
}
