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
        this.name = "Soul Splitter";
        this.description = "Cut in half the enemy's current hp (rounded up)";
        this.levelReq = 35;
        this.manaCost = 100;
        this.code = 75;
    }
    
    @Override
    public String CastSpell(Enemy enemy)
    {
        enemy.hp = (int)Math.ceil(enemy.hp / 2);
        return ("You cut the enemy's hp in half   |  ");
    }
}
