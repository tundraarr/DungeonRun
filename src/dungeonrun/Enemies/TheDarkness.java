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
        super.setName("The Darkness");
        super.setLevel( 15);
        super.setHp(150);
        super.setDmg(30);
        super.setGold(550);
        super.setMinChance(91);
        super.setMaxChance(100);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)(Math.pow(Player.GetLevel() - super.getLevel(), 2) * 1.3));
            super.setDmg((int)(Math.pow(Player.GetLevel() - super.getLevel(), 1.1) * 1.3)); 
        }
    }
}
