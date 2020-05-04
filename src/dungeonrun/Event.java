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

//An event that can occur when the player chooses to 'proceed'
//Contains a min and max probability of ocurring
//Changes the state of the game when the event is triggered
public abstract class Event {
    public String eventName;
    public int minChance;
    public int maxChance;
    public Scanner scan = new Scanner(System.in);
    
    public abstract State RunEvent();
}
