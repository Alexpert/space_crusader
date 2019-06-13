package game.main.view;

import java.awt.Button;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import edu.ricm3.game.GameView;
import game.main.model.Entity;
import game.main.model.Kind;
import game.main.model.Model;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.BLACK;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	Viewport viewport;
	HUDView hud;

	public View(Model m) {
		m_model = m;
		viewport = new Viewport(this.m_model.getCurrentWorld(), 1024, 768);
		ArrayList<Entity> entities = m_model.getCurrentWorld().getEntities();
		int i = 0;
		while(i < entities.size() && entities.get(i).getKind() != Kind.PLAYER) {
			i++;
		}
		if(i < entities.size()) {
			this.viewport.setEntity(entities.get(i));
		}
	}

	public void step(long now) {
		this.viewport.step(now);
	}

	private void computeFPS() {
		long now = System.currentTimeMillis();
		if (now - m_last > 1000L) {
			m_fps = m_npaints;
			m_last = now;
			m_npaints = 0;
		}
		m_game.setFPS(m_fps, null);
		// m_game.setFPS(m_fps, "npaints=" + m_npaints);
		m_npaints++;
	}

	@Override
	protected void _paint(Graphics g) {
		computeFPS();
				
		g.setColor(m_background);
		g.fillRect(0, 0, getWidth(), getHeight());
		viewport.paint(g);
		
		if (this.hud != null)
			this.hud.paint(g);
		/*if(this.m_model.isInGame) {
			viewport.paint(g);
		}
		else {	//menu de demarage
			
		}*/
		
	}

	public void setHUD() {
		this.hud = new HUDView(viewport.getEntity());
		this.m_game.addSouth(this.hud);
	}
}
