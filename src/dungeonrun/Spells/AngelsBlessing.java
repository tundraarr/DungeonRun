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
public class AngelsBlessing extends Spell{
    
    private int healAmount;
    
    public AngelsBlessing()
    {
        this.name = "Angel's Blessing";
        this.healAmount = 300;
        this.description = "Heal for + " + this.healAmount;
        this.levelReq = 30;
        this.manaCost = 75;
        this.code = 65;
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
        
        return ("["+Player.GetName()+"]" + " heals: " + this.healAmount + " hp   |  ");
    }
}
