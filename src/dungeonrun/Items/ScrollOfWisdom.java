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
public class ScrollOfWisdom extends Item{
    
    public ScrollOfWisdom()
    {
        this.name = "Scroll of Wisdom";
        this.description = "Increase your max mp by 15";
        this.cost = 500;
        this.itemCode = 170;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetMaxMp(Player.GetMaxMp() + 15);
    }
}
