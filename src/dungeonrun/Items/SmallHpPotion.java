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
public class SmallHpPotion extends Item{
    
    int healAmount = 50;
    
    public SmallHpPotion()
    {
        this.name = "Small Hp Potion";
        this.description = "Recover a small amount of HP";
        this.cost = 100;
        this.itemCode = 100;
    }
    
    @Override
    public void UseItem()
    {
        if(Player.GetCurrentHp() + healAmount > Player.GetMaxHp())
        {
            Player.SetCurrentHp(Player.GetMaxHp());
        }
        else
        {
            Player.SetCurrentHp(Player.GetCurrentHp() + healAmount);
        }
    }
}
