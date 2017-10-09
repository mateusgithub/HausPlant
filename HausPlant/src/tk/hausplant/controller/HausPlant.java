package tk.hausplant.controller;

import java.awt.Color;
import spacedrawboard.visualization.Drawboard;
import spacedrawboard.visualization.Visualization;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Prancheta;

/**
 * Main HausPlant class
 */
public class HausPlant {

    /**
     * HausPlant entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        Planta planta = new Planta();

        Prancheta prancheta = new Prancheta(planta, Color.white);
        prancheta.showWindow();
    }

}
