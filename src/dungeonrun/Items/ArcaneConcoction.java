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
public class ArcaneConcoction extends Item{
    
    public ArcaneConcoction()
    {
        this.name = "Arcane Concoction";
        this.description = "Recover all of your Mp";
        this.itemCode = 40;
    }
    
    @Override
    public void UseItem()
    {
        Player.SetCurrentMp(Player.GetMaxMp());
    }
}
