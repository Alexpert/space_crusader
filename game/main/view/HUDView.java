package game.main.view;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.util.ArrayList;
import java.util.Collections;

import game.main.model.Entity;
import game.main.model.Item;
import game.main.model.Kind;
import game.main.model.entities.Player;

public class HUDView extends Container {
	private static final long serialVersionUID = 1L;
	private Entity entity;
	private StatusView statusView;
	private InventoryView inventoryView;
	static int i = 0;
	

	HUDView(Entity entity) {
		this.entity = entity;
		this.setLayout(new GridLayout(1, 2));
		
		this.statusView = new StatusView(this);
		if (this.getEntity().getKind() == Kind.PLAYER)
			this.inventoryView = new InventoryView(this);
		
		
		this.add(statusView);
		this.add(inventoryView);
	}
	
	public Entity getEntity() {
		return this.entity;
	}
}

class StatusView extends Container {
	private static final long serialVersionUID = 1L;
	private HUDView parent;
	private HealthBar healthBar;
	private WealthView wealthView;
	

	StatusView(HUDView parent) {
		this.parent = parent;
		this.setLayout(new GridLayout(2, 1));
		
		this.healthBar = new HealthBar(this);
		this.wealthView = new WealthView(this);
		
		this.add(this.healthBar);
		if (this.getEntity().getKind() == Kind.PLAYER)
			this.add(this.wealthView);
	} 
	
	public Entity getEntity() {
		return this.parent.getEntity();
	}
}

class InventoryView extends Container {
	private static final long serialVersionUID = 1L;
	HUDView parent;
	ArrayList<Item> lastValue;
	private int lastIndex;
	
	InventoryView(HUDView parent) {
		this.lastValue = new ArrayList<>();
		this.lastIndex = 0;
		this.parent = parent;
		this.setLayout(new GridLayout(2, 5));
	}
	
	public Player getPlayer() {
		return (Player) this.parent.getEntity();
	}
	
	@Override
	public void paint(Graphics g) {
		if (this.lastValue.size() == this.getPlayer().getInventory().size() 
				&& this.lastIndex == this.getPlayer().getSelectedItemIndex())
			return;
		
		this.lastIndex  = this.getPlayer().getSelectedItemIndex();
		this.lastValue = new ArrayList<>(this.getPlayer().getInventory());
		this.removeAll();
		for (int i = 0; i < 10; i++) {
			Canvas canvas;
			if (i < this.lastValue.size())
				canvas = new SpriteCanvas("assets/items/" + this.lastValue.get(i).getName().toLowerCase() + ".png");
			else
				canvas = new Canvas();
			
			if (this.getPlayer().getSelectedItemIndex() == i)
				canvas.setBackground(Color.GRAY);
			
			
			this.add(canvas);
		}
		super.paint(g);
	}
}

class HealthBar extends Container {
	private static final long serialVersionUID = 1L;
	StatusView parent;
	private Label label;
	private int lastValue;
	
	HealthBar(StatusView parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());
		this.add(new SpriteCanvas("assets/hud/health.png"), BorderLayout.WEST);
		this.label = new Label();
		this.add(this.label, BorderLayout.CENTER);
	}
	
	public Entity getEntity() {
		return this.parent.getEntity();
	}
	
	@Override
	public void paint(Graphics g) {
		this.lastValue = this.getEntity().getHealth();
		label.setText(this.lastValue + "/" + this.getEntity().getMaxHealth());
		label.paint(g);
	}
}

class WealthView extends Container{
	private static final long serialVersionUID = 1L;
	StatusView parent;
	Label label;
	int lastValue = 0;
	
	WealthView(StatusView parent) {
		this.parent = parent;
		this.setLayout(new BorderLayout());
		this.add(new SpriteCanvas("assets/hud/coin.png"), BorderLayout.WEST);
		this.label = new Label();
		this.add(this.label, BorderLayout.CENTER);
	}
	
	public Player getPlayer() {
		return (Player) this.parent.getEntity();
	}
	
	@Override
	public void paint(Graphics g) {
		this.lastValue = this.getPlayer().getMoney();
		label.setText("$" + this.lastValue);
		label.paint(g);
	}
}

class SpriteCanvas extends Canvas {
	private static final long serialVersionUID = 1L;
	Image sprite;

	SpriteCanvas(String path) {
		super();
		this.setSize(new Dimension(30, 30));
		this.sprite = TextureProvider.getInstance().getTexture(path);
	}

	@Override
	public void paint(Graphics g) {
		int size = Math.min(this.getWidth(), this.getHeight());
		g.drawImage(this.sprite, 0, 0, size, size, null);
	}
}
