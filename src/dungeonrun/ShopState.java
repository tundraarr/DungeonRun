/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.*;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Liam
 */

//Game state that provides an interface for the player to purchase items
public class ShopState extends State{

    //Array of all items available to purchase from the shop
    private Item[] shopItems = new Item[]
    {
        new SmallHpPotion(), new SmallMpPotion(), new LargeHpPotion(), new LargeMpPotion()
    };
    
    @Override
    public State RunState() {

        return BrowseWares();
    }
    
    //Provide a text interface for the user to interact with the shop
    private State BrowseWares()
    {
        while(loopState)
        {
            //Display all the shop items to the player
            System.out.println("=========================SHOP============================");
            System.out.println("GOLD: " + Player.gold);
            System.out.println("0) Back");
            for(int i = 0; i < shopItems.length; i++)
            {
                System.out.println(i+1 + ") " + shopItems[i].name + " Cost: " + shopItems[i].cost + " | " + shopItems[i].description);
            }
            System.out.println("=========================================================");
            
            try
            {
                userInput = scan.nextLine();
                //Successfully buy an item if the user's input is valid
                if(Integer.valueOf(userInput) <= shopItems.length && Integer.valueOf(userInput) > 0)
                {
                    BuyItem();
                }
                //Go back to intermission state
                else if(Integer.valueOf(userInput) == 0)
                {             
                    loopState = false;
                }
                //Invalid input handler
                else
                {
                    throw new InvalidInputException();
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid input! Input a value between " + 0 + " - " + shopItems.length);
            }

           
        }
        
        return State.ChangeState(States.INTERMISSION);
    }
    
    private void BuyItem()
    {
        Item theItem = shopItems[Integer.valueOf(userInput) - 1];
        
        //Check if the player has sufficient gold to purchase the item
        if(Player.gold >= theItem.cost)
        {
            //Check if the player already has this item in their inventory
            //If they do, increase the count for the item
            //Otherwise, add a new instance of the item 
            if(Player.inventory.containsKey(theItem))
            {
                Player.inventory.replace(theItem, Player.inventory.get(theItem) + 1);
            }
            else
            {
                Player.inventory.put(theItem, 1);
            }
            
            Player.gold -= theItem.cost;
            System.out.println("You have purchased " + theItem.name);
        }
        else
        {
            System.out.println("You do not have enough gold!");
        }
    }
}
