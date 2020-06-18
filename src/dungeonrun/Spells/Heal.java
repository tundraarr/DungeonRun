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
public class Heal extends Spell{
    
    private int healAmount;
    
    public Heal()
    {
        this.name = "Heal";
        this.healAmount = 40;
        this.description = "Heal for " + this.healAmount;
        this.levelReq = 6;
        this.manaCost = 15;
        this.code = 20;
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
