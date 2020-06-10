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
public class RitualOfWisdom extends Item{
    
     public RitualOfWisdom()
    {
        this.name = "Ritual of Wisdom";
        this.description = "Sacrifice 50% of your max Hp to gain 10 Magic Power";
        this.cost = 800;
        this.itemCode = 140;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetMagicAtk(Player.GetMagicAtk() + 10);
        Player.SetMaxHp((int)(Player.GetMaxHp() / 2));
        //Set player current hp to the same as max hp if it is greater than the new max hp
        if(Player.GetCurrentHp() > Player.GetMaxHp())
        {
            Player.SetCurrentHp(Player.GetMaxHp());
        }
    }
}
