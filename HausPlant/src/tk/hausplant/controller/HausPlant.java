package tk.hausplant.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import tk.hausplant.model.Parede;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Prancheta;

/**
 * Class main start do projeto HausPlant
 */
public class HausPlant {

    /**
     * HausPlant entry point
     *
     * @param args
     */
    public static void main(String[] args) {
        Parede a = new Parede(309, 111, 400, 135);
         
         
        List<Parede> paredes = new ArrayList();
        paredes.add(a);
        
        Planta planta = new Planta("Teste", paredes);

        Prancheta prancheta = new Prancheta(planta, Color.white);
        prancheta.showWindow();
    }

}
