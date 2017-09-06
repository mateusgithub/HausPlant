package tk.hausplant;

import spacedrawboard.visualization.Drawboard;
import spacedrawboard.visualization.Visualization;
import tk.hausplant.dao.UserDAO;

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

        UserDAO userController = new UserDAO();
        userController.getUsers().forEach(u -> {
            System.out.println("Id: " + u.getId() + " Name: " + u.getName());
        });
    }

}
