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
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;
import spacedrawboard.resource.Triangle;
import spacedrawboard.resource.Vector3D;
import spacedrawboard.visualization.Drawboard;
import tk.hausplant.view.Prancheta;

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

    @Override
    public void desenhar3DEm(Drawboard drawboard) {
        // Pixel por metro
        double pxMetro = Prancheta.PIXELS_POR_METRO;

        Material materialParede = new Material(Parede.COR_PADRAO);

        // Cantos da parede
        Vector3D A = new Vector3D(getA().x, -getA().y, 0);
        Vector3D B = new Vector3D(getB().x, -getB().y, 0);

        // Ajustar escalas dos cantos (converter de pixel para metro)
        A = A.timesScalar(1 / pxMetro);
        B = B.timesScalar(1 / pxMetro);

        // Cantos superiores da parede
        Vector3D Ah = A.clone();
        Vector3D Bh = B.clone();
        Ah.z = Bh.z = Parede.ALTURA_PADRAO;

        // Vetores para dar largura à parede
        Vetor2D paralelo = new Vetor2D(A.x, A.y).minus(new Vetor2D(B.x, B.y));
        Vector3D paral = new Vector3D(paralelo.x, paralelo.y, 0);
        paral.normalize();
        Vetor2D perpendicular = paralelo.getOrthogonal();
        perpendicular.normalize();
        Vector3D perp = new Vector3D(perpendicular.x, perpendicular.y, 0);
        perp = perp.timesScalar(Parede.LARGURA_PADRAO);
        Vector3D perpNeg = perp.clone().timesScalar(-1);

        // Formar os triângulos da parede
        Mesh formaParede = new Mesh();

        // Lados
        formaParede.addTriangle(new Triangle(A.plus(perp), Ah.plus(perp), Bh.plus(perp)));
        formaParede.addTriangle(new Triangle(Bh.plus(perp), B.plus(perp), A.plus(perp)));

        formaParede.addTriangle(new Triangle(Bh.plus(perpNeg), Ah.plus(perpNeg), A.plus(perpNeg)));
        formaParede.addTriangle(new Triangle(A.plus(perpNeg), B.plus(perpNeg), Bh.plus(perpNeg)));

        // Frentes
        formaParede.addTriangle(new Triangle(A.plus(perpNeg), Ah.plus(perpNeg), Ah.plus(perp)));
        formaParede.addTriangle(new Triangle(Ah.plus(perp), A.plus(perp), A.plus(perpNeg)));

        formaParede.addTriangle(new Triangle(B.plus(perp), Bh.plus(perp), Bh.plus(perpNeg)));
        formaParede.addTriangle(new Triangle(Bh.plus(perpNeg), B.plus(perpNeg), B.plus(perp)));

        // Calha
        Material materialCalha = new Material(new Color(50, 50, 50));
        Mesh formaCalha = new Mesh();

        Vector3D alturaCalha = new Vector3D(0, 0, 0.1);
        double c = 1;

        // Cantos inferiores da calha
        Vector3D c1 = Ah.plus(perpNeg);
        Vector3D c2 = Ah.plus(perp);
        Vector3D c3 = Bh.plus(perp);
        Vector3D c4 = Bh.plus(perpNeg);

        // Face inferior da calha
        formaCalha.addTriangle(new Triangle(c1, c2, c3));
        formaCalha.addTriangle(new Triangle(c3, c4, c1));

        Model modeloParede = new Model(formaParede, materialParede);
        Model modeloCalha = new Model(formaCalha, materialCalha);
        drawboard.addModel(modeloParede);
        drawboard.addModel(modeloCalha);
    }

}
