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
        super.setName("Skeleton");
        super.setLevel( 3);
        super.setHp(25);
        super.setDmg(8);
        super.setGold(150);
        super.setMinChance(25);
        super.setMaxChance(40);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)(Math.pow(Player.GetLevel() - super.getLevel(), 1.5) * 1.2));
            super.setDmg((int)Math.pow(Player.GetLevel() - super.getLevel(), 0.85) * 2); 
        }
    }
}
