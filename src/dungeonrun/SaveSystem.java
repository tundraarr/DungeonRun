/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.*;
import java.io.*;
import java.util.Map;
import java.time.LocalDate;
/**
 *
 * @author Liam
 */

//Provides static methods to save the player's data, load their data or delete their save
public class SaveSystem {

    private static DBController dbc = new DBController();
    
    //Saves the player's data to the embedded database.
    public static void SaveGame()
    {
        //Create new tables (or replace existing ones)
        dbc.CreateTables();
        //Insert data into the tables based on data from Player class
        dbc.SetPlayerData();
    }
    
    //Load data from embedded database into Player if it the data exists
    public static boolean LoadGame()
    {
        return dbc.GetPlayerData();
    }
    
    //Used for when the player is defeated in the dungeon
    //Drops all the tables in the database that store the player's save data
    public static void DeleteSave()
    {
        dbc.DropPlayerTables();
    }
}
