/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Enemies;

import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class RavenousSlime extends Enemy{
    
    public RavenousSlime()
    {
        this.name = "Ravenous Slime";
        this.level = 5;
        this.hp = 25;
        this.dmg = 8;
        this.gold = 175;
        this.minChance = 41;
        this.maxChance = 55;
        
        if(Player.level > this.level)
        {
            this.hp += Math.pow(Player.level - this.level, 1.6) * 1.4;
            this.dmg += Math.pow(Player.level - this.level, 0.75) * 1; 
        }
    }
}
