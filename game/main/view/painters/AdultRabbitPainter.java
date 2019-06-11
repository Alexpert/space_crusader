package game.main.view.painters;

import java.awt.Color;
import java.awt.Graphics;

import game.main.model.Action;
import game.main.model.Direction;
import game.main.model.Entity;
import game.main.view.IPainter;

public class AdultRabbitPainter implements IPainter {

		Entity entity;
		
		public AdultRabbitPainter(Entity e) {
			this.entity = e;
		}

		@Override
		public void step(long now) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void paint(Graphics g,int posX,int posY) {
			g.setColor(Color.RED);
			g.fillRect(posX, posY, 32, 32);
		}

		@Override
		public void changeActionAnimation(Action a, Direction d) {
			// TODO Auto-generated method stub
			
		}
		
		
	}
