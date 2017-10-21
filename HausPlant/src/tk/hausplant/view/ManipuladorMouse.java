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

    private final static int RAIO_ALCANCE = 10;

    private final Planta planta;

    private final Renderizador2DPlanta viewer;

    private Point lastPoint = null;

    private boolean creatingWall = false;

    private boolean editingWall = false;

    private Canto selectedWallCorner = Canto.CantoA;

    private Parede activeWall = null;

    public ManipuladorMouse(Planta planta, Renderizador2DPlanta viewer) {
        this.planta = planta;
        this.viewer = viewer;
        clearSelection();
    }

    private void clearSelection() {
        if (activeWall != null) {
            activeWall.setSelected(false);
        }
        viewer.update();
    }

    private void selectWall(Parede wall) {
        activeWall = wall;
        wall.setSelected(true);
        viewer.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        clearSelection();

        if (creatingWall || editingWall) {
            creatingWall = false;
            editingWall = false;
        } else {
            Point pos = e.getPoint();

            // Check cursor position if it is close to a wall corner
            for (Parede wall : planta.getWalls()) {
                if (wall.getA().distance(pos) <= RAIO_ALCANCE) {
                    selectWall(wall);
                    editingWall = true;
                    selectedWallCorner = Canto.CantoA;
                    break;
                } else if (wall.getB().distance(pos) <= RAIO_ALCANCE) {
                    selectWall(wall);
                    editingWall = true;
                    selectedWallCorner = Canto.CantoB;
                    break;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clearSelection();

        creatingWall = false;
        lastPoint = null;
        editingWall = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point currentPoint = e.getPoint();

        if (lastPoint != null) {
            if (creatingWall) {
                activeWall.setB(currentPoint.x, currentPoint.y);
                viewer.update();
            } else if (editingWall) {
                if (selectedWallCorner == Canto.CantoA) {
                    activeWall.setA(currentPoint.x, currentPoint.y);
                } else {
                    activeWall.setB(currentPoint.x, currentPoint.y);
                }
                viewer.update();
            } else {
                selectWall(new Parede(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y));
                planta.addWall(activeWall);
                creatingWall = true;
            }
        }

        lastPoint = currentPoint;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
         viewer.update();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }

}
