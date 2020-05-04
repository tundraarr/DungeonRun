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
public class LargeMpPotion extends Item{
    
    int healAmount = 50;
    
    public LargeMpPotion()
    {
        this.name = "Large Mp Potion";
        this.description = "Recover a large amount of MP";
        this.cost = 250;
        this.itemCode = 130;
    }
    
    @Override
    public void UseItem()
    {
        if(Player.currentMp + healAmount > Player.maxMp)
        {
            Player.currentMp = Player.maxMp;
        }
        else
        {
            Player.currentMp += healAmount;
        }
    }
}
