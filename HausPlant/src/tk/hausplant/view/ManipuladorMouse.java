/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;
import tk.hausplant.model.Planta;
import tk.hausplant.model.Parede;

/**
 * Receberá eventos ocorridos sobre a Prancheta e fará modificações na planta
 * conforme solicitado pelo usuário
 */
public class ManipuladorMouse implements MouseListener, MouseWheelListener, MouseInputListener {

    private static enum Canto {
        CantoA,
        CantoB;
    }

    private final static int RAIO_ALCANCE = 12;

    private final Planta planta;

    private final Renderizador2DPlanta renderizador;

    private Point ultimaPosicaoMouse = null;

    private boolean criandoParede = false;

    private boolean editandoParede = false;

    private Canto cantoSelecinado = Canto.CantoA;

    private Parede paredeSelecionada = null;

    public ManipuladorMouse(Planta planta, Renderizador2DPlanta viewer) {
        this.planta = planta;
        this.renderizador = viewer;
    }

    private void clearSelection() {
        if (paredeSelecionada != null) {
            paredeSelecionada.setSelecionado(false);
        }
        renderizador.atualizar();
    }

    private void selecionarParede(Parede parede) {
        paredeSelecionada = parede;
        parede.setSelecionado(true);
        renderizador.atualizar();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clearSelection();

        if (criandoParede || editandoParede) {
            criandoParede = false;
            editandoParede = false;
        } else {
            Point posicaoMouse = e.getPoint();

            // Verificar se a posição do cursor está próxima à algum canto de parede
            for (Parede wall : planta.getParedes()) {
                if (wall.getA().distance(posicaoMouse) <= RAIO_ALCANCE) {
                    selecionarParede(wall);
                    editandoParede = true;
                    cantoSelecinado = Canto.CantoA;
                    break;
                } else if (wall.getB().distance(posicaoMouse) <= RAIO_ALCANCE) {
                    selecionarParede(wall);
                    editandoParede = true;
                    cantoSelecinado = Canto.CantoB;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clearSelection();

        criandoParede = false;
        ultimaPosicaoMouse = null;
        editandoParede = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point pontoAtual = e.getPoint();

        if (ultimaPosicaoMouse != null) {
            if (criandoParede) {
                paredeSelecionada.setB(pontoAtual.x, pontoAtual.y);
                renderizador.atualizar();
            } else if (editandoParede) {
                if (cantoSelecinado == Canto.CantoA) {
                    paredeSelecionada.setA(pontoAtual.x, pontoAtual.y);
                } else {
                    paredeSelecionada.setB(pontoAtual.x, pontoAtual.y);
                }
                renderizador.atualizar();
            } else {
                selecionarParede(new Parede(ultimaPosicaoMouse.x, ultimaPosicaoMouse.y, pontoAtual.x, pontoAtual.y));
                planta.addParede(paredeSelecionada);
                criandoParede = true;
            }
        }

        ultimaPosicaoMouse = pontoAtual;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        renderizador.atualizar();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }

}
