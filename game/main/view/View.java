package game.main.view;

import java.awt.Color;
import java.awt.Graphics;

import edu.ricm3.game.GameView;
import game.main.model.Model;
import game.main.model.Tile;
import game.main.model.World;

public class View extends GameView {

	private static final long serialVersionUID = 1L;

	Color m_background = Color.BLACK;
	long m_last;
	int m_npaints;
	int m_fps;
	Model m_model;
	// Controller m_ctr;

	public View(Model m) {
		m_model = m;
		// m_ctr = c;
	}

	public void step(long now) {

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
		World w = m_model.getCurrentWorld();
		for(int i = 0; i < w.getHeight(); i++) {
			for(int j = 0; j < w.getWidth(); j++) {
				Tile t = w.getTile(j, i);
				t.paint(g);
			}
		}
		
	}
}
