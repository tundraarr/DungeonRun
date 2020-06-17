/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Enemies.Enemy;

/**
 *
 * @author Liam
 */
public class CombatInfo {
    
    private Enemy enemy;
    private String text;
    
    public CombatInfo(Enemy enemy, String text)
    {
        this.enemy = enemy;
        this.text = text;
        if(this.text.isEmpty())
        {
            this.text = "In combat with " + this.enemy.name;
        }
    }
    
    public Enemy GetEnemyInfo()
    {
        return this.enemy;
    }
    
    public String getTextInfo()
    {
        return this.text;
    }
}
