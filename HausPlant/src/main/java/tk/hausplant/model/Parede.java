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
	public static final transient Color COR_PADRAO = new Color(100, 100, 100);

	/**
	 * Altura da parede padrão em metros (3D)
	 */
	public static final transient double ALTURA_PADRAO = 3;

	/**
	 * Largura da parede padrão em metros (3D).
	 */
	public static final transient double LARGURA_PADRAO = 0.2;

	/**
	 * Largura da parede quando desenhada em pixels (2D).
	 */
	public static final transient double LARGURA_PADRAO_PX = 5;

	/**
	 * Extremidade A desta parede.
	 */
	private final Point a;

	/**
	 * Extremidade B desta parede.
	 */
	private final Point b;

	private transient boolean selecionado = false;

	public Parede(final int x1, final int y1, final int x2, final int y2) {
		this.a = new Point(x1, y1);
		this.b = new Point(x2, y2);
	}

	public void setA(final int x, final int y) {
		this.a.x = x;
		this.a.y = y;
	}

	public void setB(final int x, final int y) {
		this.b.x = x;
		this.b.y = y;
	}

	public Point getA() {
		return a;
	}

	public Point getB() {
		return b;
	}

	public void setSelecionado(final boolean selecionado) {
		this.selecionado = selecionado;
	}

	public boolean isSelected() {
		return selecionado;
	}

	@Override
	public void desenhar2DEm(final Graphics graficos2d) {
		int x1 = this.getA().x;
		int y1 = this.getA().y;
		int x2 = this.getB().x;
		int y2 = this.getB().y;

		Vetor2D dir = new Vetor2D(x2 - (double) x1, y2 - (double) y1);
		Vetor2D c1;

		Vetor2D c2;
		Vetor2D c3;
		Vetor2D c4;

		c1 = dir.getOrthogonal();
		c1.normalize();
		c1 = c1.times(LARGURA_PADRAO_PX / 2);

		c2 = c1.plus(dir);

		c3 = c1.times(-2).plus(dir);

		c4 = c3.minus(dir);

		Polygon polygon = new Polygon();
		polygon.addPoint((int) c1.getX() + x1, (int) c1.getY() + y1);
		polygon.addPoint((int) c2.getX() + x1, (int) c2.getY() + y1);
		polygon.addPoint((int) c3.getX() + x1, (int) c3.getY() + y1);
		polygon.addPoint((int) c4.getX() + x1, (int) c4.getY() + y1);

		if (this.isSelected()) {
			graficos2d.setColor(Color.blue);
		} else {
			graficos2d.setColor(Color.black);
		}
		graficos2d.fillPolygon(polygon);
	}

	@Override
	public void desenhar3DEm(final Drawboard drawboard) {
		// Pixel por metro
		double pxMetro = Prancheta.PIXELS_POR_METRO;

		Material materialParede = new Material(Parede.COR_PADRAO);

		// Cantos da parede
		Vector3D cantoA = new Vector3D(getA().x, -getA().y, 0);
		Vector3D cantoB = new Vector3D(getB().x, -getB().y, 0);

		// Ajustar escalas dos cantos (converter de pixel para metro)
		cantoA = cantoA.timesScalar(1 / pxMetro);
		cantoB = cantoB.timesScalar(1 / pxMetro);

		// Cantos superiores da parede
		Vector3D ah = cantoA.clone();
		Vector3D bh = cantoB.clone();
		ah.z = bh.z = Parede.ALTURA_PADRAO;

		// Vetores para dar largura à parede
		Vetor2D paralelo = new Vetor2D(cantoA.x, cantoA.y).minus(new Vetor2D(cantoB.x, cantoB.y));
		Vector3D paral = new Vector3D(paralelo.getX(), paralelo.getY(), 0);
		paral.normalize();
		Vetor2D perpendicular = paralelo.getOrthogonal();
		perpendicular.normalize();
		Vector3D perp = new Vector3D(perpendicular.getX(), perpendicular.getY(), 0);
		perp = perp.timesScalar(Parede.LARGURA_PADRAO / 2);
		Vector3D perpNeg = perp.clone().timesScalar(-1);

		// Formar os triângulos da parede
		Mesh formaParede = new Mesh();

		// Lados
		formaParede.addTriangle(new Triangle(cantoA.plus(perp), ah.plus(perp), bh.plus(perp)));
		formaParede.addTriangle(new Triangle(bh.plus(perp), cantoB.plus(perp), cantoA.plus(perp)));

		formaParede.addTriangle(new Triangle(bh.plus(perpNeg), ah.plus(perpNeg), cantoA.plus(perpNeg)));
		formaParede.addTriangle(new Triangle(cantoA.plus(perpNeg), cantoB.plus(perpNeg), bh.plus(perpNeg)));

		// Frentes
		formaParede.addTriangle(new Triangle(cantoA.plus(perpNeg), ah.plus(perpNeg), ah.plus(perp)));
		formaParede.addTriangle(new Triangle(ah.plus(perp), cantoA.plus(perp), cantoA.plus(perpNeg)));

		formaParede.addTriangle(new Triangle(cantoB.plus(perp), bh.plus(perp), bh.plus(perpNeg)));
		formaParede.addTriangle(new Triangle(bh.plus(perpNeg), cantoB.plus(perpNeg), cantoB.plus(perp)));

		// Calha
		Material materialCalha = new Material(new Color(50, 50, 50));
		Mesh formaCalha = new Mesh();

		// Cantos inferiores da calha
		Vector3D c1 = ah.plus(perpNeg);
		Vector3D c2 = ah.plus(perp);
		Vector3D c3 = bh.plus(perp);
		Vector3D c4 = bh.plus(perpNeg);

		// Face inferior da calha
		formaCalha.addTriangle(new Triangle(c1, c2, c3));
		formaCalha.addTriangle(new Triangle(c3, c4, c1));

		Model modeloParede = new Model(formaParede, materialParede);
		Model modeloCalha = new Model(formaCalha, materialCalha);
		drawboard.addModel(modeloParede);
		drawboard.addModel(modeloCalha);
	}

}
