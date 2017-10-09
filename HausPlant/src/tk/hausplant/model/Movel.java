package tk.hausplant.model;

import java.awt.Color;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;

/**
 * Objeto tridimensional que pode ser colocado na planta
 */
public class Movel extends Model implements Objeto3D {

    public static final Color COR_PADRAO = Color.gray;

    public Movel(Mesh forma, Material material) {
        super(forma, material);
    }

}
