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
public class ScrollOfWrath extends Item{
    
    public ScrollOfWrath()
    {
        this.name = "Scroll of Wrath";
        this.description = "Increase your attack power by 5";
        this.itemCode = 10;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetAtk(Player.GetAtk() + 5);
    }
}
