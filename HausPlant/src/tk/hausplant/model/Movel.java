package tk.hausplant.model;

import java.awt.Color;
import java.awt.Graphics;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;

/**
 * Objeto tridimensional que pode ser colocado na planta
 */
public class Movel extends Objeto3D implements Desenhavel {

    public static final Color COR_PADRAO = Color.gray;
    private final Model modelo;

    public Movel(Mesh forma, Material material) {
        modelo = new Model(forma, material);
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }
    
    @Override
    public void setY(float y) {
        this.y = y;
    }
    
    @Override
    public void setZ(float z) {
        this.z = z;
    }

    @Override
    public void desenhar2DEm(Graphics graficos2d) {
        // TODO
    }
    
    

}
