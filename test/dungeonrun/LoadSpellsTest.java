/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import dungeonrun.Spells.*;
import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.Assert.*;

/**
 *
 * @author Liam
 */
public class LoadSpellsTest {
    
    public LoadSpellsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //Create a new test list of spells for the player before each test
    @Before
    public void setUp() {
        Player.SetNewSpells();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of LoadSpells method, of class IntermissionState.
     * Testing to see if the correct spells are loaded into the player's 
     * usable spells list. These spells are added to the player's spell list
     * if they are of the required level to use them.
     */
    @Test
    public void testLoadSpellsLvl15() {
        
        System.out.println("Load Spells (Level 15)");
        Player.SetLevel(15);
        IntermissionState instance = new IntermissionState();
        
        instance.LoadSpells();
        //The spells that the player should have if they are level 15
        Spell[] spells = 
        {
            new Fireball(), new LightningSpear(), new Heal(), new FrostMissle(), new GreatHeal()
        };
        
        ArrayList<Spell> expectedSpells = new ArrayList<Spell>(Arrays.asList(spells));
        
        //If the spells from the expected spells list matches the player's spells list then
        //the expected result should be true
        boolean expectedResult = true;       
        boolean result = expectedSpells.containsAll(Player.GetSpells());
        
        assertEquals(expectedResult, result);
    }
    
    /**
     * Test of LoadSpells method, of class IntermissionState.
     * Testing to see if the correct spells are loaded into the player's 
     * usable spells list. These spells are added to the player's spell list
     * if they are of the required level to use them.
     */
    @Test
    public void testLoadSpellsLvl25() {
        
        System.out.println("Load Spells (Level 25)");
        Player.SetLevel(15);
        IntermissionState instance = new IntermissionState();
        
        instance.LoadSpells();
        //The spells that the player should have if they are level 25
        Spell[] spells = 
        {
            new Fireball(), new LightningSpear(), new Heal(), new FrostMissle(), new GreatHeal(),
            new EnhanceAttack(), new EnhanceMagic(), new ArcaneBlast(), new EnhanceDefense()
        };
        
        ArrayList<Spell> expectedSpells = new ArrayList<Spell>(Arrays.asList(spells));
        
        //If the spells from the expected spells list matches the player's spells list then
        //the expected result should be true
        boolean expectedResult = true;       
        boolean result = expectedSpells.containsAll(Player.GetSpells());
        
        assertEquals(expectedResult, result);
    }
}
