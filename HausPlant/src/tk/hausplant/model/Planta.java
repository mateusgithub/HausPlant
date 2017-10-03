package tk.hausplant.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Planta da casa que poderá conter paredes, móveis e telhados
 */
public class Planta {

    private final List<Parede> walls = new ArrayList<>();

    public List<Parede> getWalls() {
        return walls;
    }

    public void addWall(Parede wall) {
        this.walls.add(wall);
    }

}
