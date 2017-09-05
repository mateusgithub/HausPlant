package tk.hausplant;

import spacedrawboard.visualization.Drawboard;
import spacedrawboard.visualization.Visualization;

/**
 * Main HausPlant class
 */
public class HausPlantDesign {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        new Visualization("Hello World!", 700, 550, new Drawboard()).showWindow();
        
    }
    
}
