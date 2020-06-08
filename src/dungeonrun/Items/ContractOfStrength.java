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
public class ContractOfStrength extends Item{
    
    public ContractOfStrength()
    {
        this.name = "Contract of Strength";
        this.description = "Sacrifice 50% of your max hp to gain 10 Atk Power";
        this.cost = 1000;
        this.itemCode = 150;
    }
    
    @Override
    public void UseItem()
    {
        Player.atk += 10;
        Player.maxHp = (int)(Player.maxHp / 2);
        //Set player current hp to the same as max hp if it is greater than the new max hp
        if(Player.currentHp > Player.maxHp)
        {
            Player.currentHp = Player.maxHp;
        }
    }
}