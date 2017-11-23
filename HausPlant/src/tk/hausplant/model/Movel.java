/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.model;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;
import spacedrawboard.visualization.Drawboard;
import tk.hausplant.view.Prancheta;

/**
 * Objeto tridimensional que pode ser colocado na planta
 */
public class Movel extends Objeto3D implements Desenhavel {

    public static final Color COR_PADRAO = Color.gray;

    private final Model modelo;

    private final Color cor;

    public Movel(Mesh forma, Color cor) {
        Material material = new Material(cor);

        modelo = new Model(forma, material);
        this.cor = cor;
    }

    /**
     * Obter dimensão do movel desenhado sobre a prancheta em pixels
     *
     * @return
     */
    private Dimension getDimension2D() {
        return new Dimension(90, 90);
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

    public Color getCor() {
        return cor;
    }

    @Override
    public void desenhar2DEm(Graphics graficos2d) {
        float s = Prancheta.PIXELS_POR_METRO;

        Dimension tamanho = getDimension2D();

        graficos2d.setColor(COR_PADRAO);

        graficos2d.drawRect(
                (int) (x * s),
                (int) (y * s),
                tamanho.width,
                tamanho.height
        );
    }

    @Override
    public void desenhar3DEm(Drawboard drawboard) {
        drawboard.addModel(modelo);
    }

}
