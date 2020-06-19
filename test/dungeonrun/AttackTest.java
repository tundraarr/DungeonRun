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
import static org.junit.Assert.*;

/**
 *
 * @author Liam
 */
public class AttackTest {
    
    public AttackTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        Player.SetLevel(1);       
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Player.SetCurrentHp(100);
    }
    
    @After
    public void tearDown() {
    }


    /**
     * Test of Attack method, of class BattleState.
     * Test to see if the player can successfully deal the correct damage to an Enemy.
     * Damage formula is as shown: pDamage = 3 + (int)Math.ceil(Math.pow(Player.GetAtk(), 1.35) * 0.65);
     */
    @Test
    public void testAttackAtk1() {      
        
        System.out.println("Attack with Atk of 1");
        Player.SetAtk(1);
        
        BattleState instance = new BattleState();
        instance.SpawnEnemy();
        
        int expectedDmg = 3 + (int)Math.ceil(Math.pow(1, 1.35) * 0.65);
        int resultDmg = instance.Attack();
        
        assertEquals(expectedDmg, resultDmg);      
    }
    
    /**
     * Test of Attack method, of class BattleState.
     * Test to see if the player can successfully deal the correct damage to an Enemy.
     * Damage formula is as shown: pDamage = 3 + (int)Math.ceil(Math.pow(Player.GetAtk(), 1.35) * 0.65);
     */
    @Test
    public void testAttackAtk5() {      
        
        System.out.println("Attack with Atk of 5");
        Player.SetAtk(5);
        
        BattleState instance = new BattleState();
        instance.SpawnEnemy();
        
        int expectedDmg = 3 + (int)Math.ceil(Math.pow(5, 1.35) * 0.65);
        int resultDmg = instance.Attack();
        
        assertEquals(expectedDmg, resultDmg);      
    }
}
