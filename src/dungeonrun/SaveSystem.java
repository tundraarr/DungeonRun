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

    private static String fileName = "drun-save-data.bin";
    
    //Saves the player's data as a binary file
    public static void SaveGame() throws IOException
    {
        PrintWriter pw = new PrintWriter(new FileOutputStream(fileName));
        pw.println(Player.name);
        pw.println(Player.level);
        pw.println(Player.gold);
        pw.println(Player.currentHp);
        pw.println(Player.maxHp);
        pw.println(Player.currentMp);
        pw.println(Player.maxMp);
        pw.println(Player.atk);
        pw.println(Player.magicAtk);
        pw.println(Player.def);
        pw.println(Player.luck);
        for(Map.Entry<Item, Integer> entry : Player.inventory.entrySet())
        {
            pw.println(entry.getKey().name);
            pw.println(entry.getValue());
        }
        pw.close();
    }
    
    //Load data from file into player's data if there is a file available
    //Returns true if there is a file available, otherwise false
    public static boolean LoadGame() throws IOException
    {
        //If there is a save available, read the file and assign values to Player's data
        File saveFile = new File(fileName);
        if(saveFile.exists())
        {
            BufferedReader br = new BufferedReader(new FileReader(fileName));

            Player.name = br.readLine();
            Player.level = Integer.valueOf(br.readLine());
            Player.gold = Integer.valueOf(br.readLine());
            Player.currentHp = Integer.valueOf(br.readLine());
            Player.maxHp = Integer.valueOf(br.readLine());
            Player.currentMp = Integer.valueOf(br.readLine());
            Player.maxMp = Integer.valueOf(br.readLine());
            Player.atk = Integer.valueOf(br.readLine());
            Player.magicAtk = Integer.valueOf(br.readLine());
            Player.def = Integer.valueOf(br.readLine());
            Player.luck = Integer.valueOf(br.readLine());
            
            //Add items to the player's inventory based on the saved string values from file
            String item;
            Integer amount;
            //Check to see if there is still data in the file to be read (if the player had any inventory items)
            while((item = br.readLine()) != null)
            {
                amount = Integer.valueOf(br.readLine());
                //Iterate through a list of all existing items and find an item that matches the name saved
                //If it is found, add it to the player's inventory along with the amount.
                for(Item i : ItemList.allItems)
                {
                    if(i.name.compareTo(item) == 0)
                    {
                        Player.inventory.put(i, amount);
                    }
                }
            }                     
            br.close();
        }
        else
        {
            return false;
        }
        
        return true;
    }
    
    //Used for when the player is defeated in the dungeon
    public static void DeleteSave()
    {
        File file = new File(fileName);
        file.delete();
    }
}
