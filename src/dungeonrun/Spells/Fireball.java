/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools   |   Templates
 * and open the template in the editor.
 */
package dungeonrun.Spells;
import dungeonrun.*;
import dungeonrun.Enemies.Enemy;
import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class Fireball extends Spell{
    
    private int power;
    
    public Fireball()
    {
        this.name = "Fireball";
        this.description = "Shoot a fireball that does decent damage   |  ";
        this.levelReq = 1;
        this.manaCost = 10;
        this.code = 10;
        this.power = 7;
    }

    @Override
    public String CastSpell(Enemy enemy) 
    {
        int damage = this.power + (int)((Math.pow(Player.GetMagicAtk(), 0.5) * 1.2));
        enemy.hp -= damage;
        return ("["+Player.GetName()+"]" + " does: " + damage + " damage   |  ");
    }

}
