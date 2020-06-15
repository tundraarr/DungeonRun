/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.*;
import dungeonrun.Views.IntermissionView;
import java.awt.CardLayout;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Liam
 */

//Game state that provides an interface for the player to purchase items
public class ShopState extends State{
    
    private int selectedItem;  
    private IntermissionView imViewRef;
    
    public ShopState(IntermissionView imView)
    {
        //Get reference to intermission view to update inventory when items are purchased.
        //Also to change the shop panel back to the actions panel
        this.imViewRef = imView;
    }
    
    @Override
    public State RunState() {

        return BrowseWares();
    }
    
    //Provide a text interface for the user to interact with the shop
    private State BrowseWares()
    {
//        while(loopState)
//        {
//            //Display all the shop items to the player
//            System.out.println("=========================SHOP============================");
//            System.out.println("GOLD: " + Player.GetGold());
//            System.out.println("0) Back");
//            for(int i = 0; i < shopItems.length; i++)
//            {
//                System.out.println(i+1 + ") " + shopItems[i].name + " Cost: " + shopItems[i].cost + " | " + shopItems[i].description);
//            }
//            System.out.println("=========================================================");
//            
//            try
//            {
//                userInput = scan.nextLine();
//                //Successfully buy an item if the user's input is valid
//                if(Integer.valueOf(userInput) <= shopItems.length && Integer.valueOf(userInput) > 0)
//                {
//                    BuyItem();
//                }
//                //Go back to intermission state
//                else if(Integer.valueOf(userInput) == 0)
//                {             
//                    loopState = false;
//                }
//                //Invalid input handler
//                else
//                {
//                    throw new InvalidInputException();
//                }
//            }
//            catch(InvalidInputException | NumberFormatException e)
//            {
//                System.out.println("Invalid input! Input a value between " + 0 + " - " + shopItems.length);
//            }
//
//           
//        }
        
        return State.ChangeState(States.INTERMISSION);
    }
    
    public void CloseShop()
    {
        CardLayout cl = (CardLayout)(imViewRef.GetInterPanel().getLayout());
        cl.show(imViewRef.GetInterPanel(), "ActionsPanel");
    }
    
    public void SetSelectedItem(int item)
    {
        selectedItem = item;
        System.out.println(item);
    }
    
    public void BuyItem()
    {
        if(selectedItem >= 0)
        {
            Item theItem = ShopInventory.GetItems()[selectedItem];

            //Check if the player has sufficient GetGold() to purchase the item
            if(Player.GetGold() >= theItem.cost)
            {
                //Check if the player already has this item in their inventory
                //If they do, increase the count for the item
                //Otherwise, add a new instance of the item 
                if(Player.GetInventory().containsKey(theItem))
                {
                    Player.GetInventory().replace(theItem, Player.GetInventory().get(theItem) + 1);
                }
                else
                {
                    Player.GetInventory().put(theItem, 1);
                }

                Player.SetGold(Player.GetGold() - theItem.cost);
                System.out.println("You have purchased " + theItem.name);
            }
            else
            {
                System.out.println("You do not have enough GetGold()!");
            }
            //Update the intermission view (the inventory panel in particular) to reflect the purchase
            imViewRef.update(null, null);
        }     
    }
}
