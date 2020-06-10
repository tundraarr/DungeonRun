/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Items;

import dungeonrun.Enemies.Enemy;
import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class AstralDust extends Item{
    
    private int mpIncrease = 25;
    
    public AstralDust()
    {
        this.name = "Astral Dust";
        this.description = "Increase your current and max Mp by " + mpIncrease;
        this.itemCode = 30;
    }
    
    public void UseItem(Enemy enemy)
    {
        Player.SetMaxMp(Player.GetMaxMp() + mpIncrease);
        if(Player.GetCurrentMp() + mpIncrease > Player.GetMaxMp())
        {
            Player.SetCurrentMp(Player.GetMaxMp());
        }
        else
        {
            Player.SetCurrentMp(Player.GetCurrentMp() + mpIncrease);
        }
    }
}
