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
public class Dragonsoul extends Item{
    
    public Dragonsoul()
    {
        this.name = "Dragon soul";
        this.description = "(Only works if current hp is 15 or less) Gain 10 def and recover all hp";
        this.cost = 900;
        this.itemCode = 180;
    }
    
    @Override
    public void UseItem()
    {
        if(Player.GetCurrentHp() <= 15)
        {
            Player.SetCurrentHp(Player.GetMaxHp());
            Player.SetDef(Player.GetDef() + 10);
        }
    }
}
