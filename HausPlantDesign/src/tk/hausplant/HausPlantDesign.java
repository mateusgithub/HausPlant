package tk.hausplant;

import tk.hausplant.controller.UserController;

/**
 * Main Test
 * 
 * @author mateusht
 */
public class HausPlantDesign {

    /**
     * Test to list users from database
     * 
     * @param args 
     */
    public static void main(String[] args) {
        UserController userController = new UserController();
        userController.getUsers().forEach(u -> {
            System.out.println("Id: " + u.getId() + " Name: " + u.getName());
                    });
    }
}
