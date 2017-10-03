package tk.hausplant.model;

/**
 * Classe responsável por realizar operações aritiméticas sobre um vetor
 */
public class Vector2D {

    public double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D() {
        x = 0;
        y = 0;
    }

    public Vector2D getOrthogonal() {
        return new Vector2D(y, -x);
    }

    public Vector2D plus(Vector2D other) {
        return new Vector2D(x + other.x, y + other.y);
    }

    public Vector2D minus(Vector2D other) {
        return new Vector2D(x - other.x, y - other.y);
    }

    public Vector2D times(double scalar) {
        return new Vector2D(x * scalar, y * scalar);
    }

    public void normalize() {
        double len = Math.sqrt(x * x + y * y);

        if (len != 0) {
            x /= len;
            y /= len;
        }
    }

}
