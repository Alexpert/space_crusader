package game.main.view;

import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Action;
import game.main.model.Direction;

public interface IPainter {

	public void step(long now);
	
	public void paint(Graphics g, int posX, int posY);
	
	public void changeActionAnimation(Action a, Direction d);
}
