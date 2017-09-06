package tk.hausplant.controller;

import tk.hausplant.dao.ConnectMSSQLServer;

public abstract class AbstractDAO {
    
    public final ConnectMSSQLServer con;
   
    public AbstractDAO() {
        con = new ConnectMSSQLServer();
    }
    
}
