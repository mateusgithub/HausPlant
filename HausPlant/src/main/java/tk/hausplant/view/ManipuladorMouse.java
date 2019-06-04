/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import tk.hausplant.model.Movel;
import tk.hausplant.model.Planta;
import tk.hausplant.model.Parede;

/**
 * Receberá eventos ocorridos sobre a Prancheta e fará modificações na planta
 * conforme solicitado pelo usuário
 */
public class ManipuladorMouse extends MouseAdapter {

	private enum Canto {
		CantoA, CantoB;
	}

	private static final int RAIO_ALCANCE = 12;

	private final Planta planta;

	private final Renderizador2DPlanta renderizador;

	private Point posMouseAntigo = null;

	private boolean criandoParede = false;

	private boolean editandoParede = false;

	private boolean movendoMovel = false;

	private Canto cantoSelecinado = Canto.CantoA;

	private Parede paredeSelecionada = null;

	private Movel movelSelecionado = null;

	public ManipuladorMouse(final Planta planta, final Renderizador2DPlanta viewer) {
		this.planta = planta;
		this.renderizador = viewer;
	}

	private void clearSelection() {
		if (paredeSelecionada != null) {
			paredeSelecionada.setSelecionado(false);
		}
		renderizador.atualizar();
	}

	private void selecionarParede(final Parede parede) {
		paredeSelecionada = parede;
		parede.setSelecionado(true);
		renderizador.atualizar();
	}

	@Override
	public void mouseClicked(final MouseEvent e) {
		// Manter
	}

	@Override
	public void mousePressed(final MouseEvent e) {
		clearSelection();

		if (criandoParede || editandoParede) {
			criandoParede = false;
			editandoParede = false;
		} else {
			Point posicaoMouse = e.getPoint();

			// Verificar se a posição do cursor está dentro da área de um móvel
			for (int i = planta.getMoveis().size() - 1; i >= 0; i--) {
				Movel movel = planta.getMoveis().get(i);

				Rectangle retangulo = movel.getRetangulo();

				int largura = retangulo.width;
				int altura = retangulo.height;

				float xMovel = retangulo.x;
				float yMovel = retangulo.y;

				if (posicaoMouse.x >= xMovel && posicaoMouse.x <= xMovel + largura && posicaoMouse.y >= yMovel
						&& posicaoMouse.y <= yMovel + altura) {
					movendoMovel = true;
					movelSelecionado = movel;
					break;
				}
			}

			// Verificar se a posição do cursor está próxima à algum canto de parede
			for (Parede parede : planta.getParedes()) {
				if (parede.getA().distance(posicaoMouse) <= RAIO_ALCANCE) {
					selecionarParede(parede);
					editandoParede = true;
					cantoSelecinado = Canto.CantoA;
					break;
				} else if (parede.getB().distance(posicaoMouse) <= RAIO_ALCANCE) {
					selecionarParede(parede);
					editandoParede = true;
					cantoSelecinado = Canto.CantoB;
					break;
				}
			}
		}
	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		clearSelection();

		criandoParede = false;
		posMouseAntigo = null;
		editandoParede = false;
		movendoMovel = false;
	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		// Manter
	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// Manter
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		Point posMouseAtual = e.getPoint();

		if (posMouseAntigo != null) {
			if (criandoParede) {
				paredeSelecionada.setB(posMouseAtual.x, posMouseAtual.y);
				renderizador.atualizar();
			} else if (editandoParede) {
				if (cantoSelecinado == Canto.CantoA) {
					paredeSelecionada.setA(posMouseAtual.x, posMouseAtual.y);
				} else {
					paredeSelecionada.setB(posMouseAtual.x, posMouseAtual.y);
				}
				renderizador.atualizar();
			} else if (movendoMovel) {
				float xMovelAntigo = movelSelecionado.getX();
				float yMovelAntigo = movelSelecionado.getY();

				float diferencaX = (float) posMouseAtual.x - posMouseAntigo.x;
				float diferencaY = (float) posMouseAtual.y - posMouseAntigo.y;

				movelSelecionado.setX(xMovelAntigo + diferencaX / Prancheta.PIXELS_POR_METRO);
				movelSelecionado.setY(yMovelAntigo + diferencaY / Prancheta.PIXELS_POR_METRO);
			} else {
				// Iniciar desenho de nova parede
				selecionarParede(new Parede(posMouseAntigo.x, posMouseAntigo.y, posMouseAtual.x, posMouseAtual.y));
				planta.addParede(paredeSelecionada);
				criandoParede = true;
			}
		}

		posMouseAntigo = posMouseAtual;

		renderizador.atualizar();
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		renderizador.atualizar();
	}

	@Override
	public void mouseWheelMoved(final MouseWheelEvent e) {
		// Manter
	}

}
