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
/**
 *
 * @author Liam
 */

//Contains static methods to view the player's deceased characters from the graveyard
//Also contains method that enables the addition of a character's data when they are defeated
public class GraveSystem {
    
    private static DBController dbc = new DBController();
    //private static String graveFile = "graveyard.txt";
    
    //Add the player's name, level and gold amount to the graveyard file
    public static void AddToGraveyard() throws IOException
    {    
//        PrintWriter pw = new PrintWriter(new FileOutputStream(graveFile, true));
//        pw.append(Player.name + " Level: " + Player.level + " Gold: " + Player.gold + " | " + LocalDate.now() + "\n");
//        pw.close();
        dbc.UpdateGraveTable();
    }
    
    //Read the graveyard file and display all the player's deceased characters
    public static void ViewGraveyard() throws IOException
    {
        System.out.println("====================GRAVEYARD=======================");
        try
        {
            ResultSet rs = dbc.QueryGraveTable();
            rs.beforeFirst();
            while(rs.next())
            {
                System.out.println("Name: " +rs.getString("Name")+ " | Level: " +rs.getInt("Level")+ " | Date: " +rs.getDate("Date"));
            }
        }
        catch(SQLException ex)
        {
            System.err.println(ex);
        }

//        File file = new File(graveFile);
//        if(file.exists())
//        {
//            BufferedReader br = new BufferedReader(new FileReader(graveFile));
//            String data;
//            while((data = br.readLine()) != null)
//            {
//                System.out.println(data);
//            }
//        }
//        else
//        {
//            System.out.println("No graveyard exists right now");
//        }
        System.out.println("====================================================");
    }
    
}
