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
        super.setName("Lost Soul");
        super.setLevel(8);
        super.setHp(40);
        super.setDmg(15);
        super.setGold(250);
        super.setMinChance(56);
        super.setMaxChance(70);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)Math.pow(Player.GetLevel() - super.getLevel(), 1.1) * 2); 
            super.setDmg((int)Math.pow(Player.GetLevel() - super.getLevel(), 0.95) * 2); 
        }
    }
}
