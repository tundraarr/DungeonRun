/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Items.Item;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

/**
 *
 * @author Liam
 */
public class DBController {
    
    private DBManager dbm;
    private Connection conn;
    private Statement statement;
    private DatabaseMetaData dbmd;
    
    public DBController()
    {
        dbm = new DBManager();
        conn = dbm.getConnection();
        try
        {
            statement = conn.createStatement();
            dbmd = conn.getMetaData();
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
    }
    
    public void CreateTables()
    {
        CreateProfileTable();
        CreateStatsTable();
        CreateItemsTable();
    }
    
    public void SetPlayerData()
    {
        try
        {
            //Adding player data to PROFILE table
            String sqlInsert = "insert into PROFILE values('"+Player.name+"', "+Player.level+", "+Player.gold+")";
            statement.addBatch(sqlInsert);

            //Adding player data to STATS table
            sqlInsert = "insert into STATS values("
                    +Player.currentHp+", "+Player.maxHp+", "+Player.currentMp+", "+Player.maxMp+", "
                    +Player.atk+", "+Player.magicAtk+", "+Player.def+", "+Player.luck+")";
            statement.addBatch(sqlInsert);           
            
            //Adding player items to ITEMS table
            for(Map.Entry<Item, Integer> entry : Player.inventory.entrySet())
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
    
    public void GetPlayerData()
    {
        
    }
    
    public void DropAllTables()
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
//    private void UpdatePlayerItemData()
//    {
//        try
//        {                                     
//            for(Map.Entry<Item, Integer> entry : Player.inventory.entrySet())
//            {
//                statement.executeUpdate("insert into ITEMS values('" +entry.getKey().name+ "', " +entry.getValue()+ ")");
//            }
//        }
//        catch(SQLException ex)
//        {
//            System.err.println(ex);
//        }
//    }
    
//    public void updatePlayerData()
//    {
//        try
//        {
//            CreateProfileTable();
            //Just use insert again?
            
            //Update PROFILE table
//            String sqlUpdate = "update " + "PROFILE" + " set Level=" + Player.level
//                    + "where Name='" +Player.name+ "'";
//            statement.addBatch(sqlUpdate);
//            sqlUpdate = "update " + "PROFILE" + " set Gold=" + Player.gold
//                    + "where Name='" +Player.name+ "'";
//            statement.addBatch(sqlUpdate);
            
            //Update STATS table
//            sqlUpdate = "update " + "STATS" + " set currentHp=" + Player.gold
//                    + "where Name='" +Player.name+ "'";
//            statement.addBatch(sqlUpdate);
            
            
//            statement.executeBatch();
//        }
//        catch(SQLException ex)
//        {
//            System.err.println(ex);
//        }    
//    }
    
    //Create a table called PROFILE that stores Player name, level and gold.
    private void CreateProfileTable()
    {            
        String tableName = "PROFILE";
        try
        {
            //If it DOES EXIST drop it, in any case create a new table       
            ResultSet rs = dbmd.getTables(null, null, tableName, null);
            if(rs.next())
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
    
    //Create a table that stores the player's stats
    private void CreateStatsTable()
    {            
        String tableName = "STATS";
        try
        {
            //If it DOES EXIST drop it, in any case create a new table       
            ResultSet rs = dbmd.getTables(null, null, tableName, null);
            if(rs.next())
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
    
    //Create a table that stores the player's items
    private void CreateItemsTable()
    {            
        String tableName = "ITEMS";
        try
        {
            //If it DOES EXIST drop it, in any case create a new table       
            ResultSet rs = dbmd.getTables(null, null, tableName, null);
            if(rs.next())
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
}
