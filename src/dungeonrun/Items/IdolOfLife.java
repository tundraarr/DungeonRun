/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Items;

import dungeonrun.Enemies.*;
import dungeonrun.Items.Item;
import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class IdolOfLife extends Item{
    
    public IdolOfLife()
    {
        this.name = "Idol of Life";
        this.description = "Recover all your health";
        this.itemCode = 20;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetCurrentHp(Player.GetMaxHp());
    }
}
