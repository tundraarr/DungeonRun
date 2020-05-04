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
public class LargeHpPotion extends Item{
    
    int healAmount = 100;
    
    public LargeHpPotion()
    {
        this.name = "Large Hp Potion";
        this.description = "Recover a large amount of HP";
        this.cost = 200;
        this.itemCode = 120;
    }
    
    @Override
    public void UseItem()
    {
        if(Player.currentHp + healAmount > Player.maxHp)
        {
            Player.currentHp = Player.maxHp;
        }
        else
        {
            Player.currentHp += healAmount;
        }
    }
}
