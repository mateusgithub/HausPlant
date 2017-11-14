/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.io.Serializable;

public class Parede extends Objeto3D implements Serializable, Desenhavel {

    /**
     * Cor da parede pardrão
     */
    public transient static final Color COR_PADRAO = new Color(100, 100, 100);

    /**
     * Altura da parede padrão em metros (3D)
     */
    public transient static final double ALTURA_PADRAO = 3;

    /**
     * Largura da parede padrão em metros (3D)
     */
    public transient static final double LARGURA_PADRAO = 0.2;

    /**
     * Largura da parede quando desenhada em pixels (2D)
     */
    public transient static final double LARGURA_PADRAO_PX = 5;

    /**
     * Extremidades desta parede representada por dois pontos
     */
    private final Point a, b;

    private transient boolean selecionado = false;

    public Parede(int x1, int y1, int x2, int y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }

    public void setA(int x, int y) {
        this.a.x = x;
        this.a.y = y;
    }

    public void setB(int x, int y) {
        this.b.x = x;
        this.b.y = y;
    }

    public Point getA() {
        return a;
    }

    public Point getB() {
        return b;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }

    public boolean isSelected() {
        return selecionado;
    }

    @Override
    public void desenhar2DEm(Graphics graficos2d) {
        int x1 = this.getA().x, y1 = this.getA().y,
                x2 = this.getB().x, y2 = this.getB().y;

        Vetor2D dir = new Vetor2D(x2 - x1, y2 - y1);
        Vetor2D c1;

        Vetor2D c2, c3, c4;

        c1 = dir.getOrthogonal();
        c1.normalize();
        c1 = c1.times(LARGURA_PADRAO_PX / 2);

        c2 = c1.plus(dir);

        c3 = c1.times(-2).plus(dir);

        c4 = c3.minus(dir);

        Polygon polygon = new Polygon();
        polygon.addPoint((int) c1.x + x1, (int) c1.y + y1);
        polygon.addPoint((int) c2.x + x1, (int) c2.y + y1);
        polygon.addPoint((int) c3.x + x1, (int) c3.y + y1);
        polygon.addPoint((int) c4.x + x1, (int) c4.y + y1);

        if (this.isSelected()) {
            graficos2d.setColor(Color.blue);
        } else {
            graficos2d.setColor(Color.black);
        }
        graficos2d.fillPolygon(polygon);
        //g.setColor(Color.black);
        //g.drawPolygon(polygon);
    }

}
