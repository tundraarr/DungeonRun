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
public class EnhanceAttack extends Spell{
    
    public EnhanceAttack()
    {
        this.name = "Enhance Attack";
        this.description = "Permanently increase your attack by 1";
        this.levelReq = 16;
        this.manaCost = 75;
        this.code = 35;
    }
    
    @Override
    public void CastSpell(Enemy enemy)
    {
        Player.atk += 1;
        System.out.println("Increased attack by 1");
    }
    
}
