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
        super.setName("Necromancer");
        super.setLevel( 8);
        super.setHp(55);
        super.setDmg(23);
        super.setGold(350);
        super.setMinChance(86);
        super.setMaxChance(90);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)(Math.pow(Player.GetLevel() - super.getLevel(), 1.5) * 1.3));
            super.setDmg((int)(Math.pow(Player.GetLevel() - super.getLevel(), 0.75) * 2.5)); 
        }
    }
}
