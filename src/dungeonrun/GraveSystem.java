/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.time.LocalDate;
import java.io.*;
/**
 *
 * @author Liam
 */

//Contains static methods to view the player's deceased characters from the graveyard
//Also contains method that enables the addition of a character's data when they are defeated
public class GraveSystem {
    
    private static String graveFile = "graveyard.txt";
    
    //Add the player's name, level and gold amount to the graveyard file
    public static void AddToGraveyard() throws IOException
    {    
        PrintWriter pw = new PrintWriter(new FileOutputStream(graveFile, true));
        pw.append(Player.name + " Level: " + Player.level + " Gold: " + Player.gold + " | " + LocalDate.now() + "\n");
        pw.close();
    }
    
    //Read the graveyard file and display all the player's deceased characters
    public static void ViewGraveyard() throws IOException
    {
        System.out.println("====================GRAVEYARD=======================");
        File file = new File(graveFile);
        if(file.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(graveFile));
            String data;
            while((data = br.readLine()) != null)
            {
                System.out.println(data);
            }
        }
        else
        {
            System.out.println("No graveyard exists right now");
        }
        System.out.println("====================================================");
    }
    
}
