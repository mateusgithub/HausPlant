package tk.hausplant;

import java.awt.Color;
import spacedrawboard.visualization.Drawboard;
import spacedrawboard.visualization.Visualization;
import tk.hausplant.dao.UserDAO;
import tk.hausplant.model.Planta;
import tk.hausplant.view.Renderizador2DPlanta;
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

        new Visualization("Hello World!", 700, 550, new Drawboard()).showWindow();

        Planta planta = new Planta();
        
        Prancheta prancheta = new Prancheta(planta, Color.white);
        prancheta.showWindow();

        /*
        UserDAO userController = new UserDAO();
        userController.getUsers().forEach(u -> {
            System.out.println("Id: " + u.getId() + " Name: " + u.getName());
        });
         */
    }

}