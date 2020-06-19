/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools   |   Templates
 * and open the template in the editor.
 */
package dungeonrun.Spells;

import dungeonrun.Enemies.Enemy;

/**
 *
 * @author Liam
 */
public class SoulSplitter extends Spell{
    
    public SoulSplitter()
    {
        super.setName("Soul Splitter");
        super.setDescription("Cut in half the enemy's current hp (rounded up)");
        super.setLevelReq(35);
        super.setManaCost(100);
        super.setCode(75);
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        enemy.setHp((int) Math.ceil(enemy.getHp() / 2));
        return ("You cut the enemy's hp in half   |  ");
    }
}
