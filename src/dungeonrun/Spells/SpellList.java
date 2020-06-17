/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Spells;

import dungeonrun.Player;

/**
 *
 * @author Liam
 */
public class SpellList {
    
    private Spell[] allSpells = 
    {
        new Fireball(), new LightningSpear(), new Heal(), new FrostMissle(), new GreatHeal(),
        new EnhanceAttack(), new EnhanceMagic(), new ArcaneBlast(), new EnhanceDefense(),
        new MeteorStrike(), new AngelsBlessing(), new ManaDischarge(), new SoulSplitter()
    };
    
    public Spell[] GetAllSpells()
    {
        return allSpells;
    }   
}
