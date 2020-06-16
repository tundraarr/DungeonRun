/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.util.Random;
/**
 *
 * @author Liam
 */

//Event for engaging into battle with an enemy
//Changes to Battle State
public class BattleEvent extends Event{
    
    public BattleEvent()
    {
        this.eventName = "Battle";
        this.maxChance = 100;
        this.minChance = 31;
    }
    
    @Override
    public String RunEvent() {
        
        return "Battle";
    }
    
}
