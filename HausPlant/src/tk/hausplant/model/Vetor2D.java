package tk.hausplant.model;

/**
 * Classe responsável por realizar operações aritiméticas sobre um vetor
 */
public class Vetor2D {

    public double x, y;

    public Vetor2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vetor2D() {
        x = 0;
        y = 0;
    }

    public Vetor2D getOrthogonal() {
        return new Vetor2D(y, -x);
    }

    public Vetor2D plus(Vetor2D other) {
        return new Vetor2D(x + other.x, y + other.y);
    }

    public Vetor2D minus(Vetor2D other) {
        return new Vetor2D(x - other.x, y - other.y);
    }

    public Vetor2D times(double scalar) {
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
