package game.main.view.painters;

import java.awt.Graphics;
import java.awt.Image;

import game.main.model.Action;
import game.main.model.Direction;
import game.main.model.entities.DroppedItem;
import game.main.view.IPainter;
import game.main.view.TextureProvider;

public class DroppedItemPainter implements IPainter {
	DroppedItem droppedItem;
	Image texture;

	public DroppedItemPainter(DroppedItem droppedItem) {
		this.droppedItem = droppedItem;
		this.texture = TextureProvider.getInstance().getTexture("assets/items/"
				+ this.droppedItem.getItem().getName().toLowerCase() + ".png");
	}

	@Override
	public void step(long now) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void paint(Graphics g, int posX, int posY) {
		g.drawImage(this.texture, posX, posY, 32, 32, null);
	}

	@Override
	public void changeActionAnimation(Action a, Direction d) {
		// TODO Auto-generated method stub
		
	}

}
