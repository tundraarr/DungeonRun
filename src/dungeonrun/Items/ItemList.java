/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Items;

/**
 *
 * @author Liam
 */
public class ItemList {
    //A list of all the items available in the game
    //Used for matching save data (string data) to the name of an item class which is then added to player inventory
    public static Item[] allItems = new Item[]
    {
      new LargeHpPotion(), new LargeMpPotion(), new SmallHpPotion(), new SmallMpPotion(),
      new IdolOfLife(), new ScrollOfWrath(), new AstralDust(), new ArcaneConcoction(), new DevilsHeart(),
      new LuckyClover(), new RitualOfWisdom(), new ContractOfStrength(), new PotionOfVigor(), new ScrollOfWisdom(),
      new Dragonsoul()
    };
}
