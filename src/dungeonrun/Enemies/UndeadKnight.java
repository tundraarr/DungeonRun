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
public class UndeadKnight extends Enemy{
    
    public UndeadKnight()
    {
        super.setName("Undead Knight");
        super.setLevel( 6);
        super.setHp(27);
        super.setDmg(20);
        super.setGold(300);
        super.setMinChance(71);
        super.setMaxChance(85);
        
        if(Player.GetLevel() > super.getLevel())
        {
            super.setHp((int)(Math.pow(Player.GetLevel() - super.getLevel(), 0.75) * 1.6));
            super.setDmg((int)Math.pow(Player.GetLevel() - super.getLevel(), 0.75) * 2); 
        }
    }
}
