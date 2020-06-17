/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Spells.SpellList;
import java.util.Random;
/**
 *
 * @author Liam
 */

//Event for engaging into battle with an enemy
public class BattleEvent extends Event{
    
    public BattleEvent()
    {
        this.eventName = "Battle";
        this.maxChance = 100;
        this.minChance = 31;
    }
    
    @Override
    public String RunEvent() {
        
        //Load in the player's spells they are able to use before entering battle
        return "Battle";
    }
    
}
