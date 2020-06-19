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
public class EnhanceDefense extends Spell{
    
    public EnhanceDefense()
    {
        super.setName("Enhance Defense");
        super.setDescription("Permanently increase you defense by 1");
        super.setLevelReq(20);
        super.setManaCost(150);
        super.setCode(50);
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        Player.SetDef(Player.GetDef() + 1);
        return ("["+Player.GetName()+"]" + " Increased defense by 1   |  ");
    }
}
