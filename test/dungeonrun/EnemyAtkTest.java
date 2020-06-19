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
public class EnemyAtkTest {
    
    public EnemyAtkTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    //Setup necessary Player stats at the start of each test to test damage recieved
    @Before
    public void setUp() {
        Player.SetLevel(1);
        Player.SetAtk(1);
        Player.SetCurrentHp(100);
    }
    
    @After
    public void tearDown() {
    }

    //Testing the enemy's attack on the player and if the player takes damage (and the correct amount)
    //The enemy's damage can vary between a high and low value (0.2 offset)
    @Test
    public void testAttack() {
       
        System.out.println("Enemy attacking player");
        BattleState instance = new BattleState();
        
        int expRemainingHpHi = (100 - (int)(instance.SpawnEnemy().dmg * 0.8));
        int expRemainingHpLo = (100 - (int)(instance.SpawnEnemy().dmg * 1.2));
        
        instance.Attack();
        int resultHp = Player.GetCurrentHp();
        //If the player's resulting hp is within the the bounds of the low and high then
        //the testing is successful
        assertTrue(resultHp >= expRemainingHpLo || resultHp <= expRemainingHpHi);
    }

    
}
