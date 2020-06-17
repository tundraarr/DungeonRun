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
public class Necromancer extends Enemy{
    
    public Necromancer()
    {
        this.name = "Necromancer";
        this.level = 8;
        this.hp = 55;
        this.dmg = 23;
        this.gold = 350;
        this.minChance = 86;
        this.maxChance = 90;
        
        if(Player.GetLevel() > this.level)
        {
            this.hp += Math.pow(Player.GetLevel() - this.level, 1.5) * 1.3;
            this.dmg += Math.pow(Player.GetLevel() - this.level, 0.75) * 2.5; 
        }
    }
}
