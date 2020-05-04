/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Spells.*;
import dungeonrun.Items.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 *
 * @author Liam
 */

//Contains all attributes associated to the player
//Includes: Stats, Inventory, Level, Name
//Also provides static method that allows player to level up their stats and a static method to check whether an item is in their inventory
public class Player {
    
    public static String name;
    public static int level;
    public static int gold;

    public static int currentHp;
    public static int maxHp;
    public static int currentMp;
    public static int maxMp;
    
    public static int atk;
    public static int magicAtk;
    public static int def;
    public static int luck;
    
    public static HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    public static ArrayList<Spell> spells = new ArrayList<Spell>();
    
    //Enable the player to select a stat to increase by 1 when they defeat a monster
    public static void LevelUp()
    {
        boolean validAns = false;
        Scanner scan = new Scanner(System.in);
        System.out.println("Leveling Up!");
        level++;
        
        while(!validAns)
        {
            try
            {
                System.out.println("==========================================");
                System.out.println("What would you like to level up?");
                System.out.println("1) HP");
                System.out.println("2) MP");
                System.out.println("3) ATK");
                System.out.println("4) MGK");
                System.out.println("5) DEF");
                System.out.println("6) LUCK");
                System.out.println("==========================================");
                
                switch(Integer.valueOf(scan.nextLine()))
                {
                    case 1:
                        Player.maxHp += 20;
                        Player.currentHp += 20;
                        break;
                    case 2:
                        Player.maxMp += 15;
                        Player.currentMp += 15;
                        break;
                    case 3:
                        Player.atk ++;
                        break;
                    case 4:
                        Player.magicAtk++;
                        break;
                    case 5:
                        Player.def++;
                        break;
                    case 6:
                        Player.luck++;
                        break;
                    default:
                        throw new InvalidInputException();
                }
                
                validAns = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid Input! Must be 1 - 6");
            }
        }
    }
    
    //Check to see whether the user's input of an item name matches an item in their inventory
    //Return the item if there is match, otherwise return null
    public static Item CheckItemInInventory(String userInput)
    {
        Item item = null;
        for(Map.Entry<Item, Integer> entry : Player.inventory.entrySet())
        {
            if(entry.getKey().name.compareTo(userInput) == 0)
            {
                item = entry.getKey();
            }
        }
        
        return item;
    }
}
