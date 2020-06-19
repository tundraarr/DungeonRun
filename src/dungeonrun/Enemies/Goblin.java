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
        super.setName("Goblin");
        super.setLevel(2);
        super.setHp(10);
        super.setDmg(5);
        super.setGold(75);
        super.setMinChance(0);
        super.setMaxChance(24);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)(Math.pow(Player.GetLevel() - super.getLevel(), 1.15) * 2));  
            super.setDmg((int)Math.pow(Player.GetLevel() - super.getLevel(), 0.75) * 2); 
        }
    }
}
