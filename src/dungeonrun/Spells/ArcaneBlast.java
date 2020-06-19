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
public class ArcaneBlast extends Spell{
    
    private int power;
    
    public ArcaneBlast()
    {
        super.setName("Arcane Blast");
        super.setDescription("Deal a very high amount of damage based on magic power");
        super.setLevelReq(18);
        super.setManaCost(25);
        super.setCode(40);
        this.power = 5;
    }
    
    @Override
    public String CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 2)) * 0.75);
        enemy.setHp(enemy.getHp() - damage);
        return ("["+Player.GetName()+"]" + " does: " + damage + " damage   |  ");
    }
}
