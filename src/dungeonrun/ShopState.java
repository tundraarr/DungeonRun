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
    
    //Exit the shop view
    public void CloseShop()
    {
        CardLayout cl = (CardLayout)(imViewRef.GetInterPanel().getLayout());
        cl.show(imViewRef.GetInterPanel(), "ActionsPanel");
    }
    
    //Set what the currently selected item from the shop(JList) is
    public void SetSelectedItem(int item)
    {
        selectedItem = item;
    }
    
    //Purchase the selected item and add it to their inventory if the player has sufficient gold
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
            }
            //Update the intermission view (the inventory panel in particular) to reflect the purchase
            imViewRef.update(null, null);
        }     
    }
}
