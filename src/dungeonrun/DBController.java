/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Items.Item;
import dungeonrun.Items.ItemList;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

/**
 *
 * @author Liam
 */

//Enables manipulation of the database connected to this application
public class DBController {
    
    private DBManager dbm;
    private Connection conn;
    private Statement statement;
    private Statement qryStatement;
    private DatabaseMetaData dbmd;
    
    public DBController()
    {
        dbm = new DBManager();
        conn = dbm.getConnection();
        try
        {
            statement = conn.createStatement();
            qryStatement = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            dbmd = conn.getMetaData();
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Create all necessary tables
    public void CreateTables()
    {
        CreateProfileTable();
        CreateStatsTable();
        CreateItemsTable();
    }
    
    //Inserts data into the related Player tables with information from the Player object
    public void SetPlayerData()
    {
        try
        {
            //Adding player data to PROFILE table
            String sqlInsert = "insert into PROFILE values('"+Player.GetName()+"', "+Player.GetLevel()+", "+Player.GetGold()+")";
            statement.addBatch(sqlInsert);

            //Adding player data to STATS table
            sqlInsert = "insert into STATS values("
                    +Player.GetCurrentHp()+", "+Player.GetMaxHp()+", "+Player.GetCurrentMp()+", "+Player.GetMaxMp()+", "
                    +Player.GetAtk()+", "+Player.GetMagicAtk()+", "+Player.GetDef()+", "+Player.GetLuck()+")";
            statement.addBatch(sqlInsert);           
            
            //Adding player items to ITEMS table
            for(Map.Entry<Item, Integer> entry : Player.GetInventory().entrySet())
            {
                statement.addBatch("insert into ITEMS values('" +entry.getKey().name+ "', " +entry.getValue()+ ")");
            }
            statement.executeBatch();
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Get and load the player's data from the database tables into the Player class
    public boolean GetPlayerData()
    {
        boolean loadSuccess = false;
        //Return true if all the queries are successful
        if(QueryProfileTable() && QueryStatsTable() && QueryItemsTable())
        {
            loadSuccess = true;
        }    
        return loadSuccess;
    }
    
    //Drop all the tables
    public void DropPlayerTables()
    {
        try
        {
            statement.addBatch("DROP TABLE PROFILE");
            statement.addBatch("DROP TABLE STATS");
            statement.addBatch("DROP TABLE ITEMS");
            statement.executeBatch();
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Update the GRAVE table with a new player character
    public void UpdateGraveTable()
    {
        String tableName = "GRAVE";
        try
        {
            //If there is not GRAVE table, create it  
            ResultSet rs = dbmd.getTables(null, null, tableName, null);
            if(!rs.next())
            {
                String sqlCreate = "create table " + tableName
                + "(Name varchar(128),"
                + "Level int,"
                + "Date date)";
                statement.executeUpdate(sqlCreate);
            }
            
            //Add the player's GetName(), level and the current date
            String sqlInsert = "insert into "+tableName+" values"
                    + "('"+Player.GetName()+"', " +Player.GetLevel()+ ", '" +LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE)+ "')";
            statement.executeUpdate(sqlInsert);
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Returns a ResultSet of the rows in the GRAVE table if the table exists
    public ResultSet QueryGraveTable()
    {
        ResultSet rs = null;
        String tableName = "GRAVE";
        //If there is a GRAVE table, query for all rows
        try
        {
            rs = dbmd.getTables(null, null, tableName, null);
            if(rs.next())
            {
                rs = qryStatement.executeQuery("select * from GRAVE"); 
            }    
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
        
        return rs;
    }
    
    //Create a table called PROFILE that stores Player GetName(), level and gold.
    private void CreateProfileTable()
    {            
        String tableName = "PROFILE";
        try
        {
            //If it DOES EXIST drop it, in any case create a new table       
            if(TableExists(tableName))
            {
                statement.executeUpdate("DROP TABLE PROFILE");
            }
            
            String sqlCreate = "create table " + tableName
                + "(Name varchar(128),"
                + "Level int,"
                + "Gold int)";
            
            statement.executeUpdate(sqlCreate);
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Create a table called STATS that stores the player's stats
    private void CreateStatsTable()
    {            
        String tableName = "STATS";
        try
        {
            //If it DOES EXIST drop it, in any case create a new table       
            if(TableExists(tableName))
            {
                statement.executeUpdate("DROP TABLE STATS");
            }
            
            String sqlCreate = "create table " + tableName
                + "(CurrentHp int, MaxHp int,"
                + "CurrentMp int, MaxMp int,"
                + "ATK int, MGATK int,"
                + "DEF int, LUCK int)";
            
            statement.executeUpdate(sqlCreate);
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Create a table called ITEMS that stores the player's items
    private void CreateItemsTable()
    {            
        String tableName = "ITEMS";
        try
        {
            //If it DOES EXIST drop it, in any case create a new table       
            if(TableExists(tableName))
            {
                statement.executeUpdate("DROP TABLE ITEMS");
            }
            
            String sqlCreate = "create table " + tableName
                + "(ItemName varchar(128), Quantity int)";
            
            statement.executeUpdate(sqlCreate);
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    //Query the PROFILE table for all the data and load it into Player if the table exists
    private boolean QueryProfileTable()
    {
        String tableName = "PROFILE";
        if(TableExists(tableName))
        {
            ResultSet rs = null;
            String sqlQuery = "select * from " + tableName;
            try
            {
                rs = qryStatement.executeQuery(sqlQuery);
                rs.beforeFirst();
                rs.next();

                Player.SetName(rs.getString("Name"));
                Player.SetLevel(rs.getInt("Level"));
                Player.SetGold(rs.getInt("Gold"));

                rs.close();
                return true;
            }
            catch(SQLException ex)
            {
                System.err.println(ex);
                return false;
            }    
        }
        
        return false;
    }
    
    //Query the STATS table for all the data and load it into Player if the table exists
    private boolean QueryStatsTable()
    {
        String tableName = "STATS";
        if(TableExists(tableName))
        {
            ResultSet rs = null;
            String sqlQuery = "select * from " + tableName;
            try
            {
                rs = qryStatement.executeQuery(sqlQuery);
                rs.beforeFirst();
                rs.next();

                Player.SetCurrentHp(rs.getInt("CurrentHp"));
                Player.SetMaxHp(rs.getInt("MaxHp"));
                Player.SetCurrentMp(rs.getInt("CurrentMp"));
                Player.SetMaxMp(rs.getInt("MaxMp"));
                Player.SetAtk(rs.getInt("ATK"));
                Player.SetMagicAtk(rs.getInt("MGATK"));
                Player.SetDef(rs.getInt("DEF"));
                Player.SetLuck(rs.getInt("LUCK"));

                rs.close();
                return true;
            }
            catch(SQLException ex)
            {
                System.err.println(ex);
                return false;
            }
        }
        
        return false;
    }
    
    //Query the ITEMS table for all the data and load it into Player if the table exists
    private boolean QueryItemsTable()
    {
        String tableName = "ITEMS";
        if(TableExists(tableName))
        {
            ResultSet rs = null;
            String sqlQuery = "select * from " + tableName;
            try
            {
                rs = qryStatement.executeQuery(sqlQuery);
                rs.beforeFirst();
                while(rs.next())
                {
                    String itemName = rs.getString("ItemName");
                    int quantity = rs.getInt("Quantity");
                    for(Item i : ItemList.allItems)
                    {
                        if(itemName.compareTo(i.name) == 0)
                        {
                            Player.GetInventory().put(i, quantity);
                        }
                    }
                }

                rs.close();
                return true;
            }
            catch(SQLException ex)
            {
                System.err.println(ex);
                return false;
            }
        }
        
        return false;
    }
    
    //Return true if the table exists
    private boolean TableExists(String tblName)
    {
        boolean exists = false;
        try
        {
            ResultSet rs = dbmd.getTables(null, null, tblName, null);
            if(rs.next())
            {
                exists = true;
            }    
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
        
        return exists;
    }
    
}
