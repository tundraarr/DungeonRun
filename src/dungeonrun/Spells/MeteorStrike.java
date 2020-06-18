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
public class MeteorStrike extends Spell{
    
    private int power;
    
    public MeteorStrike()
    {
        this.name = "Meteor Strike";
        this.description = "Call down meteors to destroy your enemy";
        this.levelReq = 26;
        this.manaCost = 50;
        this.code = 60;
        this.power = 30;
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 2)) * 0.85);
        enemy.hp -= damage;
        return ("["+Player.GetName()+"]" + " does: " + damage + " damage   |  ");
    }
}
