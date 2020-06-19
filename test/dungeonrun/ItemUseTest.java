/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Items.*;
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
public class ItemUseTest {
    
    public ItemUseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //Set a new test inventory for the player before each test
    @Before
    public void setUp() {
        Player.SetNewInventory();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of UseItem method, of class IntermissionState.
     * Testing if the item is removed from the player's inventory after it has been used
     * when there is only a single amount of that item.
     */
    @Test
    public void testUseItemSingle() {
        
        //Populate the test inventory with a single item (Large Hp Potion)
        System.out.println("Use Item (Single Remaining");
        Player.GetInventory().put(new LargeHpPotion(), 1);
        IntermissionState instance = new IntermissionState();
        
        //Use the Large Hp Potion from the Player's inventory
        instance.SetSelectedItem("Large Hp Potion");
        instance.UseItem();
        
        //It is expected that the item is removed from the Player's inventory after it has been used
        //because it has a single instance of it left.
        //As such the containsKey() should return false, as the item should no longer be found
        Item expectedItem = new LargeHpPotion();
        boolean expectedResult = false;
        boolean result = Player.GetInventory().containsKey(expectedItem);
        
        assertEquals(expectedResult, result);
    }

    /**
     * Test of UseItem method, of class IntermissionState.
     * Testing if the item's amount is deducted by 1 after a single instance
     * of the item is used when it has an amount greater than 1.
     */
    @Test
    public void testUseItemMulti() {
        
        //Populate the test inventory with an item (Large Mp Potion) but with an amount of 3
        System.out.println("Use Item (Multiple Remaining)");
        Player.GetInventory().put(new LargeMpPotion(), 3);
        IntermissionState instance = new IntermissionState();
        
        //Use a Large Mp Potion from the Player's inventory
        instance.SetSelectedItem("Large Mp Potion");
        instance.UseItem();
        
        //It is expected that a single instance/amount of the item is removed from the Player's 
        //inventory after it has been used.
        //This should result in the amount of the Large Mp Potions in the player's inventory to be 2
        //down from it's original value of 3.
        Item expectedItem = new LargeMpPotion();
        int expectedResult = 2;
        int result = Player.GetInventory().get(expectedItem);
        
        assertEquals(expectedResult, result);
    }  
}
