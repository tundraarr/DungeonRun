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
public class FrostMissle extends Spell{
    
    private int power;
    
    public FrostMissle()
    {
        super.setName("Frost Missle");
        super.setDescription("Fire a frozen icicle at the enemy that does high damage");
        super.setLevelReq(10);
        super.setManaCost(20);
        super.setCode(25);
        this.power = 20;
    }
    
    @Override
    public String CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 0.85)) * 1.75);
        enemy.setHp(enemy.getHp() - damage);
        return ("["+Player.GetName()+"]" + " does: " + damage + " damage   |  ");
    }
}
