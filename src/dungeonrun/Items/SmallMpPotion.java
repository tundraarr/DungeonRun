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
public class SmallMpPotion extends Item{
    
    int healAmount = 30;
    
    public SmallMpPotion()
    {
        this.name = "Small Mp Potion";
        this.description = "Recover a small amount of MP";
        this.cost = 150;
        this.itemCode = 110;
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
