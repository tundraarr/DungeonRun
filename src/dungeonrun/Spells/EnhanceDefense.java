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
public class EnhanceDefense extends Spell{
    
    public EnhanceDefense()
    {
        this.name = "Enhance Defense";
        this.description = "Permanently increase you defense by 1";
        this.levelReq = 20;
        this.manaCost = 1050;
        this.code = 50;
    }
    
    @Override
    public void CastSpell(Enemy enemy)
    {
        Player.SetDef(Player.GetDef() + 1);
        System.out.println("Increased defense by 1");
    }
}
