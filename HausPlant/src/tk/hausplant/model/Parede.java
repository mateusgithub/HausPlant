/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tk.hausplant.model;

import java.awt.Point;

/**
 *
 * @author sergio
 */
public class Parede extends Objeto3D {

    private Point a, b;

    private boolean selected = false;

    public Parede(int x1, int y1, int x2, int y2) {
        this.a = new Point(x1, y1);
        this.b = new Point(x2, y2);
    }

    public void setA(int x, int y) {
        this.a = new Point(x, y);
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

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public boolean isSelected() {
        return selected;
    }

}
