package game.main.view;

import java.awt.Graphics;
import java.awt.Image;

public interface IPainter {

	public void step(long now);
	
	public void paint(Graphics g);
}
