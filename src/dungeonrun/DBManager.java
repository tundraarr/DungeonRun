/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Liam
 */

//Manages connection to the database
public class DBManager {
    
    //Arguments for connecting to the embedded database
    static final String USER_NAME = "pdc";
    static final String PASSWORD = "pdc";
    static final String URL = "jdbc:derby:DungeonRunDB;create=true";
    
    public Connection conn;
    
    public DBManager()
    {
        establishConnection();
    }
    
    public void establishConnection()
    {
        if(this.conn == null)
        {
            try
            {
                conn = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            }
            catch(SQLException ex)
            {
                System.err.println(ex + "SQL ERROR");
            }
        }
    }
    
    public Connection getConnection()
    {
        return this.conn;
    }
    
    public void closeConnection()
    {
        try
        {
            if(!conn.isClosed())
            {
                conn.close();
            }
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
}
