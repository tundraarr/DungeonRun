/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.util.Scanner;

/**
 *
 * @author Liam
 */

//When this event occurs the player takes a random amount of damage between a range
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
        
        System.out.println("Ran into a trap");
        System.out.println("Type anything to continue.....");
        scan.nextLine();
        
        return null;
    }
    
}
