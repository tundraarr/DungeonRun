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
public class Goblin extends Enemy{
    
    public Goblin()
    {
        this.name = "Goblin";
        this.level = 2;
        this.hp = 10;
        this.dmg = 5;
        this.gold = 75;
        this.minChance = 0;
        this.maxChance = 24;
        
        if(Player.GetLevel() > this.level)
        {
            this.hp += Math.pow(Player.GetLevel() - this.level, 1.15) * 2;  
            this.dmg += Math.pow(Player.GetLevel() - this.level, 0.75) * 2; 
        }
    }
}
