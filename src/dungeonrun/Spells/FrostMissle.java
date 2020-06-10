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
public class FrostMissle extends Spell{
    
    private int power;
    
    public FrostMissle()
    {
        this.name = "Frost Missle";
        this.description = "Fire a frozen icicle at the enemy that does high damage";
        this.levelReq = 10;
        this.manaCost = 20;
        this.code = 25;
        this.power = 20;
    }
    
    @Override
    public void CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 0.85)) * 1.75);
        System.out.println(Player.GetName() + " used " + this.name + " and does " + damage + " damage.");
        enemy.hp -= damage;
    }
}
