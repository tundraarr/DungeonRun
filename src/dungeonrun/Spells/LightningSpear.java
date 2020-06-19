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
public class LightningSpear extends Spell{
    
    private int power;
    
    public LightningSpear()
    {
        super.setName("Lightning Spear");
        super.setDescription("Strike with lightning to deal damage that scales well with  magic attack");
        super.setLevelReq(4);
        super.setManaCost(12);
        super.setCode(15);
        this.power = 5;
    }
    
    @Override
    public String CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 1.65)) * 0.75);
        enemy.setHp(enemy.getHp() - damage);
        return ("["+Player.GetName()+"]" + " does: " + damage + " damage   |  ");
    }
}
