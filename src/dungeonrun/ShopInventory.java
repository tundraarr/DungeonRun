/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Items.*;

/**
 *
 * @author Liam
 */
public class ShopInventory {
    
    //Array of all items available to purchase from the shop
    private static Item[] shopItems = new Item[]
    {
        new SmallHpPotion(), new SmallMpPotion(), new LargeHpPotion(), new LargeMpPotion(),
        new PotionOfVigor(), new ScrollOfWisdom(), new LuckyClover(), new RitualOfWisdom(), 
        new ContractOfStrength(), new Dragonsoul()
    };;     
    
    public static Item[] GetItems()
    {
        return shopItems;
    }
}
