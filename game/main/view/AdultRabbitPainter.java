package game.main.view;

import java.awt.Color;
import java.awt.Graphics;

import game.main.model.Entity;

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
		public void paint(Graphics g) {
			g.setColor(Color.RED);
			g.fillRect(entity.getX()*32, entity.getY()*32, 32, 32);
		}
		
		
	}
