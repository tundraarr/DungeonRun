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
        super.setName("Mana Discharge");
        super.setDescription( "Spend all of your current mp to deal significant damage based on mana spent");
        super.setLevelReq(33);
        super.setManaCost(Player.GetCurrentMp());
        super.setCode(70);
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        int damage = (int)(Math.ceil(this.getManaCost() * 4.5));
        enemy.setHp(enemy.getHp() - damage);
        return ("You spend all your current mana to do " + damage + " damage   |  ");
    }
}
