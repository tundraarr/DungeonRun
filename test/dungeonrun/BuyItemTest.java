/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Items.Item;
import dungeonrun.Views.IntermissionView;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Liam
 */
public class BuyItemTest {
    
    IntermissionView imV;
    
    public BuyItemTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
        
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        imV = new IntermissionView();
        Player.SetGold(1000);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of BuyItem method, of class ShopState.
     * Testing to see if the desired item is successfully purchased
     * and added to the player's inventory.
     */
    @Test
    public void testBuyItem() {

        System.out.println("BuyItem");
        int selectedItem = 1;
        boolean itemInPlayerInventory = true;
        
        ShopState instance = new ShopState(imV);
        instance.SetSelectedItem(selectedItem);
        instance.BuyItem();
        
        Item expectedBoughtItem = ShopInventory.GetItems()[selectedItem];
        boolean result = Player.GetInventory().containsKey(expectedBoughtItem);

        assertEquals(itemInPlayerInventory, result);
    }
    
    @Test
    public void testBuyItem2() {

        System.out.println("BuyItem2");
        int selectedItem = 2;
        boolean itemInPlayerInventory = true;
        
        ShopState instance = new ShopState(imV);
        instance.SetSelectedItem(selectedItem);
        instance.BuyItem();
        
        Item expectedBoughtItem = ShopInventory.GetItems()[selectedItem];
        boolean result = Player.GetInventory().containsKey(expectedBoughtItem);

        assertEquals(itemInPlayerInventory, result);
    }
    
}
