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
public class EnhanceAttack extends Spell{
    
    public EnhanceAttack()
    {
        super.setName("Enhance Attack");
        super.setDescription("Permanently increase your attack by 1");
        super.setLevelReq(16);
        super.setManaCost(75);
        super.setCode(35);
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        Player.SetAtk(Player.GetAtk() + 1);
        return ("["+Player.GetName()+"]" + " Increased attack by 1   |  ");
    }
    
}
