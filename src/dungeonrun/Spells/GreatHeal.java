/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools   |   Templates
 * and open the template in the editor.
 */
package dungeonrun.Spells;

import dungeonrun.Enemies.Enemy;
import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class GreatHeal extends Spell{
    
    private int healAmount;
    
    public GreatHeal()
    {
        super.setName("Great Heal");
        this.healAmount = 100;
        super.setDescription("Heal for " + this.healAmount);
        super.setLevelReq(13);
        super.setManaCost(35);
        super.setCode(25);
    }
    
    @Override
    public String CastSpell(Enemy enemy) 
    {
        if((Player.GetCurrentHp() + this.healAmount) > Player.GetMaxHp())
        {
            Player.SetCurrentHp(Player.GetMaxHp());
        }
        else
        {
            Player.SetCurrentHp(Player.GetCurrentHp() + this.healAmount);
        }
        
        return ("["+Player.GetName()+"]"  + " heals: " + this.healAmount + " hp   |  ");
    }
}
