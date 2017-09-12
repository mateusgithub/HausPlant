package tk.hausplant.dao;

public abstract class AbstractDAO {

    public final ConnectMSSQLServer con;

    public AbstractDAO() {
        con = new ConnectMSSQLServer();
    }

}
