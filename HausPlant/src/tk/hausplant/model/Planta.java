/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.hausplant.model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sergio
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
