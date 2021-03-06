/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import tk.hausplant.model.Movel;
import tk.hausplant.model.Planta;
import tk.hausplant.model.Parede;

/**
 * Responsável por gerar uma visualização 2D de uma planta
 */
public class Renderizador2DPlanta extends JPanel {

	private static final long serialVersionUID = 1L;

	private final Color backgroundColor;

	private final Planta planta;

	public Renderizador2DPlanta(final Planta planta, final Color backgroundColor) {
		ManipuladorMouse mouseHandler = new ManipuladorMouse(planta, this);

		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);
		addMouseWheelListener(mouseHandler);

		this.planta = planta;
		this.backgroundColor = backgroundColor;

		setBackground(backgroundColor);

		setDoubleBuffered(true);
		setIgnoreRepaint(true);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(final ComponentEvent e) {
				atualizar();
			}
		});
	}

	private void clear() {
		Graphics g = getGraphics();

		if (g != null) {
			g.setColor(backgroundColor);
			g.fillRect(0, 0, getWidth(), getHeight());
		}
	}

	public void atualizar() {
		clear();

		for (Parede parede : planta.getParedes()) {
			parede.desenhar2DEm(getGraphics());
		}

		for (Movel movel : planta.getMoveis()) {
			movel.desenhar2DEm(getGraphics());
		}
	}
}
