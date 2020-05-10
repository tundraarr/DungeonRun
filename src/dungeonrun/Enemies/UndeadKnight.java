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
public class UndeadKnight extends Enemy{
    
    public UndeadKnight()
    {
        this.name = "Undead Knight";
        this.level = 6;
        this.hp = 27;
        this.dmg = 20;
        this.gold = 300;
        this.minChance = 71;
        this.maxChance = 85;
        
        if(Player.level > this.level)
        {
            this.hp += Math.pow(Player.level - this.level, 0.75) * 1.6;
            this.dmg += Math.pow(Player.level - this.level, 0.75) * 2; 
        }
    }
}
