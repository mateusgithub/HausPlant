package tk.hausplant.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Connector class project database
 * 
 * @author mateusht
 */
public class ConnectMSSQLServer {

    public ConnectMSSQLServer() {
    }
    
    private static final String JDBCURL = "jdbc:sqlserver://ft-projects.database.windows.net:1433;databaseName=si400-hausplant-dev";
    private static final String USER = "ftlimeira";
    private static final String PASS = "limeira@2016";
    
    public ResultSet dbConnect(String query){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection conn = DriverManager.getConnection(JDBCURL, USER, PASS);
            System.out.println("Connected database");
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            return result;
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return null;
    }
}
