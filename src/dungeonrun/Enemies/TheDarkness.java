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
public class TheDarkness extends Enemy{
    
    public TheDarkness()
    {
        this.name = "The Darkness";
        this.level = 15;
        this.hp = 150;
        this.dmg = 30;
        this.gold = 550;
        this.minChance = 91;
        this.maxChance = 100;
        
        if(Player.level > this.level)
        {
            this.hp += Math.pow(Player.level - this.level, 2) * 1.3;
            this.dmg += Math.pow(Player.level - this.level, 1.1) * 1.3; 
        }
    }
}
