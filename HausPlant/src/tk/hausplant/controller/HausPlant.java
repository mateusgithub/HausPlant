package tk.hausplant.controller;

import java.awt.Color;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Prancheta;

/**
 * Class main para iniciar o projeto HausPlant
 */
public class HausPlant {

    
    
    /**
     * HausPlant entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        Planta planta =  new Planta();
        Prancheta prancheta = new Prancheta(planta, Color.white);
        prancheta.showWindow();
    }

}
