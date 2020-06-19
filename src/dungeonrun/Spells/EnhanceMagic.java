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
public class EnhanceMagic extends Spell{
    
    public EnhanceMagic()
    {
        super.setName("Enhance Magic");
        super.setDescription("Permanently increase your magic attack by 1");
        super.setLevelReq(19);
        super.setManaCost(85);
        super.setCode(45);
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        Player.SetMagicAtk(Player.GetMagicAtk() + 1);
        return ("["+Player.GetName()+"]" + " Increased magic attack by 1   |  ");
    }
}
