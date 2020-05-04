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
public class LightningSpear extends Spell{
    
    private int power;
    
    public LightningSpear()
    {
        this.name = "Lightning Spear";
        this.description = "Strike with lightning to deal damage that scales well with  magic attack";
        this.levelReq = 4;
        this.manaCost = 12;
        this.code = 15;
        this.power = 5;
    }
    
    @Override
    public void CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.magicAtk, 1.65)) * 0.75);
        System.out.println(Player.name + " used " + this.name + " and does " + damage + " damage.");
        enemy.hp -= damage;
    }
}
