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
public class EnhanceMagic extends Spell{
    
    public EnhanceMagic()
    {
        this.name = "Enhance Magic";
        this.description = "Permanently increase your magic attack by 1";
        this.levelReq = 19;
        this.manaCost = 85;
        this.code = 45;
    }
    
    @Override
    public void CastSpell(Enemy enemy)
    {
        Player.magicAtk += 1;
        System.out.println("Increased magic attack by 1");
    }
}
