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
public class RavenousSlime extends Enemy{
    
    public RavenousSlime()
    {
        super.setName("Ravenous Slime");
        super.setLevel( 5);
        super.setHp(25);
        super.setDmg(8);
        super.setGold(175);
        super.setMinChance(41);
        super.setMaxChance(55);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)(Math.pow(Player.GetLevel() - super.getLevel(), 1.6) * 1.4));
            super.setDmg((int)Math.pow(Player.GetLevel() - super.getLevel(), 0.75) * 1); 
        }
    }
}
