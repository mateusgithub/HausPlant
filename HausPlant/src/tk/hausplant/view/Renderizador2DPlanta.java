/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.hausplant.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JPanel;
import tk.hausplant.model.Planta;
import tk.hausplant.model.Wall;
import tk.hausplant.model.Vector2D;

/**
 *
 * @author sergio
 */
public class Renderizador2DPlanta extends JPanel {

    private final Color backgroundColor;

    private final Planta planta;

    public Renderizador2DPlanta(Planta planta, Color backgroundColor) {
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
            public void componentResized(ComponentEvent e) {
                update();
            }
        });
    }

    private void clear() {
        Graphics g = getGraphics();

        g.setColor(backgroundColor);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void drawWall(Wall wall, double thickness) {
        Graphics g = getGraphics();

        int x1 = wall.getA().x, y1 = wall.getA().y,
                x2 = wall.getB().x, y2 = wall.getB().y;

        Vector2D dir = new Vector2D(x2 - x1, y2 - y1);

        Vector2D c1, c2, c3, c4;

        c1 = dir.getOrthogonal();
        c1.normalize();
        c1 = c1.times(thickness / 2);

        c2 = c1.plus(dir);

        c3 = c1.times(-2).plus(dir);

        c4 = c3.minus(dir);

        Polygon polygon = new Polygon();
        polygon.addPoint((int) c1.x + x1, (int) c1.y + y1);
        polygon.addPoint((int) c2.x + x1, (int) c2.y + y1);
        polygon.addPoint((int) c3.x + x1, (int) c3.y + y1);
        polygon.addPoint((int) c4.x + x1, (int) c4.y + y1);

        if (wall.isSelected()) {
            g.setColor(Color.blue);
        } else {
            g.setColor(Color.black);
        }
        g.fillPolygon(polygon);
        //g.setColor(Color.black);
        //g.drawPolygon(polygon);
    }

    public void update() {
        clear();

        for (Wall wall : planta.getWalls()) {
            drawWall(wall, 10);
        }
    }

}
