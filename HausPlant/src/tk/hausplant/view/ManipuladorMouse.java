/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.hausplant.view;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import javax.swing.event.MouseInputListener;
import tk.hausplant.model.Planta;
import tk.hausplant.model.Wall;

/**
 *
 * @author sergio
 */
public class ManipuladorMouse implements MouseListener, MouseWheelListener, MouseInputListener {

    private final static int CORNER_A = 0;
    private final static int CORNER_B = 1;

    private final static int EDIT_WALL_RADIUS = 10;

    private final Planta planta;

    private final Renderizador2DPlanta viewer;

    private Point lastPoint = null;

    private boolean creatingWall = false;

    private boolean editingWall = false;

    private int selectedWallCorner = CORNER_A;

    private Wall activeWall = null;

    public ManipuladorMouse(Planta planta, Renderizador2DPlanta viewer) {
        this.planta = planta;
        this.viewer = viewer;
    }

    private void clearSelection() {
        if (activeWall != null) {
            activeWall.setSelected(false);
        }
        viewer.update();
    }

    private void selectWall(Wall wall) {
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
            for (Wall wall : planta.getWalls()) {
                if (wall.getA().distance(pos) <= EDIT_WALL_RADIUS) {
                    selectWall(wall);
                    editingWall = true;
                    selectedWallCorner = CORNER_A;
                    break;
                } else if (wall.getB().distance(pos) <= EDIT_WALL_RADIUS) {
                    selectWall(wall);
                    editingWall = true;
                    selectedWallCorner = CORNER_B;
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
                if (selectedWallCorner == CORNER_A) {
                    activeWall.setA(currentPoint.x, currentPoint.y);
                } else {
                    activeWall.setB(currentPoint.x, currentPoint.y);
                }
                viewer.update();
            } else {
                selectWall(new Wall(lastPoint.x, lastPoint.y, currentPoint.x, currentPoint.y));
                planta.addWall(activeWall);
                creatingWall = true;
            }
        }

        lastPoint = currentPoint;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }

}
