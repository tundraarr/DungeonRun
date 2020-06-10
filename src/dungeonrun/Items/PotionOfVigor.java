/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Items;

import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class PotionOfVigor extends Item{
    
    public PotionOfVigor()
    {
        this.name = "Potion of Vigor";
        this.description = "Increase your max hp by 15";
        this.cost = 500;
        this.itemCode = 160;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetMaxHp(Player.GetMaxHp() + 15);
    }
}
