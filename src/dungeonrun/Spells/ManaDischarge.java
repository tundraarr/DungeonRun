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
public class ManaDischarge extends Spell{
    
    public ManaDischarge()
    {
        this.name = "Mana Discharge";
        this.description = "Spend all of your current mp to deal significant damage based on mana spent";
        this.levelReq = 33;
        this.manaCost = Player.GetCurrentMp();
        this.code = 70;
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        int damage = (int)(Math.ceil(this.manaCost * 4.5));
        enemy.hp -= damage;
        return ("You spend all your current mana to do " + damage + " damage   |  ");
    }
}
