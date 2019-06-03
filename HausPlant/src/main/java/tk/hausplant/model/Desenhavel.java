/**
 * HausPlant
 *
 * 2017
 * Equipe desenvolvedora do HausPlant
 */
package tk.hausplant.model;

import java.awt.Graphics;
import spacedrawboard.visualization.Drawboard;

public interface Desenhavel {

    public void desenhar2DEm(Graphics graficos2d);

    public void desenhar3DEm(Drawboard drawboard);

}
