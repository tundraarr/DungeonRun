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
public class Skeleton extends Enemy{
    
    public Skeleton()
    {
        this.name = "Skeleton";
        this.level = 3;
        this.hp = 25;
        this.dmg = 8;
        this.gold = 150;
        this.minChance = 25;
        this.maxChance = 40;
        
        if(Player.level > this.level)
        {
            this.hp += Math.pow(Player.level - this.level, 1.5) * 1.2;
            this.dmg += Math.pow(Player.level - this.level, 0.85) * 2; 
        }
    }
}
