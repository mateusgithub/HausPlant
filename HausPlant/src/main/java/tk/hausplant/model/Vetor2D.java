/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.model;

/**
 * Classe responsável por realizar operações aritiméticas sobre um vetor
 */
public class Vetor2D {

	private double x;
	private double y;

	public Vetor2D(final double x, final double y) {
		this.x = x;
		this.y = y;
	}

	public Vetor2D() {
		x = 0;
		y = 0;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setX(final double x) {
		this.x = x;
	}

	public void setY(final double y) {
		this.y = y;
	}

	public Vetor2D getOrthogonal() {
		return new Vetor2D(y, -x);
	}

	public Vetor2D plus(final Vetor2D other) {
		return new Vetor2D(x + other.x, y + other.y);
	}

	public Vetor2D minus(final Vetor2D other) {
		return new Vetor2D(x - other.x, y - other.y);
	}

	public Vetor2D times(final double scalar) {
		return new Vetor2D(x * scalar, y * scalar);
	}

	public void normalize() {
		double len = Math.sqrt(x * x + y * y);

		if (len != 0) {
			x /= len;
			y /= len;
		}
	}

}
