/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Items.*;
import java.util.Random;

/**
 *
 * @author Liam
 */

//This event adds a random treasure item to the player's inventory
public class TreasureEvent extends Event{

    private Item[] treasures;
    
    public TreasureEvent()
    {
        this.eventName = "Treasure";
        this.maxChance = 20;
        this.minChance = 0;
        treasures = new Item[]{new ScrollOfWrath(), new IdolOfLife(), new AstralDust(), new ArcaneConcoction()};
    }
    
    @Override
    public State RunEvent() {
        
        Item theTreasure;
        Random ran = new Random();
        theTreasure = treasures[ran.nextInt(treasures.length)];

        if(Player.inventory.containsKey(theTreasure))
        {
            //Player.inventory.items.get(theTreasure).intValue() = Player.inventory.items.get(theTreasure).intValue() + 1;
            Player.inventory.replace(theTreasure, Player.inventory.get(theTreasure).intValue() + 1);
        }
        else
        {
            Player.inventory.put(theTreasure, 1);
        }
        
        System.out.println("TREASURE! You found " + theTreasure.name);
        System.out.println("Type anything to continue......");
        scan.nextLine();
        return null;
    }
    
}
