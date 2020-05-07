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
public class DevilsHeart extends Item{
    
    public DevilsHeart()
    {
        this.name = "Devil's Heart";
        this.description = "Increase your maximum hp by 40";
        this.itemCode = 50;
    }
    
    @Override
    public void UseItem()
    {
        Player.maxHp += 40;
    }
}
