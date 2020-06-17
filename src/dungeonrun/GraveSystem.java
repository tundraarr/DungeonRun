/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.time.LocalDate;
import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author Liam
 */

//Contains static methods to view the player's deceased characters from the graveyard
//Also contains method that enables the addition of a character's data when they are defeated
public class GraveSystem {
    
    private static DBController dbc = new DBController();
    
    //Add the player's name, level and gold amount to the graveyard database table
    public static void AddToGraveyard() throws IOException
    {    
        dbc.UpdateGraveTable();
    }
    
    //Read the graveyard table and display all the player's deceased characters
    public static ArrayList<String> GetGraveyard()
    {
        ArrayList<String> graveList = new ArrayList<String>();
        
        try
        {
            ResultSet rs = dbc.QueryGraveTable();
            rs.beforeFirst();
            while(rs.next())
            {
                graveList.add("Name: " +rs.getString("Name")+ " | Level: " +rs.getInt("Level")+ " | Date: " +rs.getDate("Date"));
            }           
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }
        
        return graveList;
    }
    
}
