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
import java.util.Map;

/**
 *
 * @author Liam
 */
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
    
    private boolean QueryProfileTable()
    {
        ResultSet rs = null;
        String sqlQuery = "select * from PROFILE";
        try
        {
            rs = qryStatement.executeQuery(sqlQuery);
            rs.beforeFirst();
            rs.next();
            
            Player.name = rs.getString("Name");
            Player.level = rs.getInt("Level");
            Player.gold = rs.getInt("Gold");
            
            rs.close();
            return true;
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
            return false;
        }         
    }
    
    private boolean QueryStatsTable()
    {
        ResultSet rs = null;
        String sqlQuery = "select * from STATS";
        try
        {
            rs = qryStatement.executeQuery(sqlQuery);
            rs.beforeFirst();
            rs.next();
            
            Player.currentHp = rs.getInt("CurrentHp");
            Player.maxHp = rs.getInt("MaxHp");
            Player.currentMp = rs.getInt("CurrentMp");
            Player.maxMp = rs.getInt("MaxMp");
            Player.atk = rs.getInt("ATK");
            Player.magicAtk = rs.getInt("MGATK");
            Player.def = rs.getInt("DEF");
            Player.luck = rs.getInt("LUCK");
            
            rs.close();
            return true;
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
            return false;
        }
    }
    
    private boolean QueryItemsTable()
    {
        ResultSet rs = null;
        String sqlQuery = "select * from ITEMS";
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
                        Player.inventory.put(i, quantity);
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
}
