package tk.hausplant.controller;

import tk.hausplant.dao.ConnectMSSQLServer;

abstract class AbstractController {
    
    final ConnectMSSQLServer con;
   
    public AbstractController() {
        con = new ConnectMSSQLServer();
    }
    
}
