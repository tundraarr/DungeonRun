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
public class LuckyClover extends Item{
    
    public LuckyClover()
    {
        this.name = "Lucky Clover";
        this.description = "Increase your LUCK by 5";
        this.cost = 500;
        this.itemCode = 190;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetLuck(Player.GetLuck() + 5);
    }
}
