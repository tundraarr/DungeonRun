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
public class LifeDrain extends Spell{
    
    private int drainPower;
    
    public LifeDrain()
    {
        this.name = "Life Drain";
        this.description = "Deal damage and restore hp equal to damage dealt";
        this.levelReq = 23;
        this.manaCost = 25;
        this.code = 55;
        this.drainPower = 15;
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        int damage = this.drainPower + (int)((Math.pow(Player.GetMagicAtk(), 0.75)) * 0.75);
        enemy.hp -= damage;
        if((Player.GetCurrentHp() + damage) > Player.GetMaxHp())
        {
            Player.SetCurrentHp(Player.GetMaxHp());
        }
        else
        {
            Player.SetCurrentHp(Player.GetCurrentHp() + damage);
        }
        return ("["+Player.GetName()+"]" + " does: " + damage + " damage and heals for " + damage + "   |   ");
    }
}
