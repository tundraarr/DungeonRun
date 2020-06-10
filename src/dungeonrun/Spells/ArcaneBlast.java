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
public class ArcaneBlast extends Spell{
    
    private int power;
    
    public ArcaneBlast()
    {
        this.name = "Arcane Blast";
        this.description = "Deal a very high amount of damage based on magic power";
        this.levelReq = 18;
        this.manaCost = 25;
        this.code = 40;
        this.power = 5;
    }
    
    @Override
    public void CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 2)) * 0.75);
        System.out.println(Player.GetName() + " used " + this.name + " and does " + damage + " damage.");
        enemy.hp -= damage;
    }
}
