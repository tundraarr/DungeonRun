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
public class LostSoul extends Enemy{
    
    public LostSoul()
    {
        this.name = "Lost Soul";
        this.level = 8;
        this.hp = 40;
        this.dmg = 15;
        this.gold = 200;
        this.minChance = 56;
        this.maxChance = 70;
        
        if(Player.level > this.level)
        {
            this.hp += Math.pow(Player.level - this.level, 1.1) * 2; 
            this.dmg += Math.pow(Player.level - this.level, 0.95) * 2; 
        }
    }
}
