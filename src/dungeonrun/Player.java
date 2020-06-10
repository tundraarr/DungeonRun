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
    
    private static String name;
    private static int level;
    private static int gold;

    private static int currentHp;
    private static int maxHp;
    private static int currentMp;
    private static int maxMp;
    
    private static int atk;
    private static int magicAtk;
    private static int def;
    private static int luck;
    
    private static HashMap<Item, Integer> inventory = new HashMap<Item, Integer>();
    private static ArrayList<Spell> spells = new ArrayList<Spell>();
    
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
    
    //Getters 
    public static String GetName()
    {
        return Player.name;
    }
    
    public static int GetLevel()
    {
        return Player.level;
    }
    
    public static int GetGold()
    {
        return Player.gold;
    }
    
    public static int GetCurrentHp()
    {
        return Player.currentHp;
    }
    
    public static int GetMaxHp()
    {
        return Player.maxHp;
    }
    
    public static int GetCurrentMp()
    {
        return Player.currentMp;
    }
    
    public static int GetMaxMp()
    {
        return Player.maxMp;
    }
    
    public static int GetAtk()
    {
        return Player.atk;
    }
    
    public static int GetMagicAtk()
    {
        return Player.magicAtk;
    }
    
    public static int GetDef()
    {
        return Player.def;
    }
    
    public static int GetLuck()
    {
        return Player.luck;
    }
    
    public static HashMap<Item, Integer> GetInventory()
    {
        return Player.inventory;
    }
    
    public static ArrayList<Spell> GetSpells()
    {
        return Player.GetSpells();
    }
    
    //Setters
    public static void SetName(String name)
    {
        Player.name = name;
    }
    
    public static void SetLevel(int level)
    {
        Player.level = level;
    }
    
    public static void SetGold(int gold)
    {
        Player.gold = gold;
    }
    
    public static void SetCurrentHp(int cHp)
    {
        Player.currentHp = cHp;
    }
    
    public static void SetMaxHp(int mHp)
    {
        Player.maxHp = mHp;
    }
    
    public static void SetCurrentMp(int cMp)
    {
        Player.currentMp = cMp;
    }
    
    public static void SetMaxMp(int mMp)
    {
        Player.maxMp = mMp;
    }
    
    public static void SetAtk(int atk)
    {
        Player.atk = atk;
    }
    
    public static void SetMagicAtk(int mAtk)
    {
        Player.magicAtk = mAtk;
    }
    
    public static void SetDef(int def)
    {
        Player.def = def;
    }
    
    public static void SetLuck(int luck)
    {
        Player.luck = luck;
    }  
    
    public static void SetNewSpells()
    {
        spells = new ArrayList<Spell>();
    }
    
    public static void SetNewInventory()
    {
        Player.inventory = new HashMap<Item, Integer>();
    }
}
