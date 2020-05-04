/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
    public void CastSpell(Enemy enemy)
    {
        int damage = this.drainPower + (int)((Math.pow(Player.magicAtk, 0.75)) * 0.75);
        System.out.println(Player.name + " used " + this.name + " and does " + damage + " damage and heals for " + damage);
        enemy.hp -= damage;
        if((Player.currentHp + damage) > Player.maxHp)
        {
            Player.currentHp = Player.maxHp;
        }
        else
        {
            Player.currentHp += damage;
        }
    }
}
