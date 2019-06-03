/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.Serializable;
import spacedrawboard.resource.Material;
import spacedrawboard.resource.Mesh;
import spacedrawboard.resource.Model;
import spacedrawboard.resource.Vector3D;
import spacedrawboard.visualization.Drawboard;
import tk.hausplant.view.Prancheta;

/**
 * Objeto tridimensional que pode ser colocado na planta
 */
public class Movel extends Objeto3D implements Desenhavel, Serializable {

    public static final Color COR_PADRAO = Color.gray;

    private final Model modelo;

    private final Color cor;

    public Movel(Mesh forma, Color cor) {
        Material material = new Material(cor);
        
        modelo = new Model(forma, material);
        
        this.cor = cor;
        
        setX(10);
        setY(10);
    }

    /**
     * Obter dimensão do movel desenhado sobre a prancheta em pixels
     *
     * @return
     */
    public Rectangle getRetangulo() {
        float s = Prancheta.PIXELS_POR_METRO;
        return new Rectangle(
                (int) (x * s), (int) (y * s),
                30, 30
        );
    }

    @Override
    public void setX(float x) {
        // Resetar posição
        modelo.translate(new Vector3D(-this.x, 0, 0));
        modelo.translate(new Vector3D(x, 0, 0));

        this.x = x;
    }

    @Override
    public void setY(float y) {
        // Resetar posição
        modelo.translate(new Vector3D(0, this.y, 0));
        modelo.translate(new Vector3D(0, -y, 0));

        this.y = y;
    }

    @Override
    public void setZ(float z) {
        // Ignorar
    }

    public Color getCor() {
        return cor;
    }

    @Override
    public void desenhar2DEm(Graphics graficos2d) {
        graficos2d.setColor(cor);

        Rectangle retangulo = getRetangulo();
        graficos2d.fillRoundRect(
                retangulo.x, retangulo.y,
                retangulo.width, retangulo.height,
                5, 5
        );
    }

    @Override
    public void desenhar3DEm(Drawboard drawboard) {
        drawboard.addModel(modelo);
    }

}
