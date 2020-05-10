/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.util.Scanner;
import java.util.Random;

/**
 *
 * @author Liam
 */

//When this event occurs the player takes non-lethal damage (will never reduce their hp below 1)
//Does not change the current state
public class TrapEvent extends Event{
    
    public TrapEvent()
    {
        this.eventName = "Trap";
        this.maxChance = 30;
        this.minChance = 21;
    }
    
    @Override
    public State RunEvent() {
        
        Random ran = new Random();
        
        //Damage is a randomized value between 1 and the 20% of the player's max hp
        int trapDmg = ran.nextInt((int)(Player.maxHp * 0.20)) + 1; 
        
        //Check to see if damage is lethal
        //If so, change damage to leave player on 1 hp
        if(trapDmg >= Player.currentHp)
        {
            trapDmg = Player.currentHp - 1;
        }   
                
        Player.currentHp -= trapDmg;
        
        System.out.println("Ran into a trap!");
        System.out.println(Player.name + " took " + trapDmg + " damage.");
        System.out.println("Type anything to continue.....");
        scan.nextLine();
        
        return null;
    }
    
}
