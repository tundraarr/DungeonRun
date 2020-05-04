/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Spells;

import dungeonrun.Enemies.Enemy;
import dungeonrun.*;

/**
 *
 * @author Liam
 */
public abstract class Spell {
    public String name;
    public String description;
    public int levelReq;
    public int manaCost;
    public int code;
    
    //Method to cast a spell 
    public abstract void CastSpell(Enemy enemy);
    
    
        @Override
    public boolean equals(Object o) {
        if (o == null) {
        return false;
        }
        if (!(o instanceof Spell)) {
        return false;
        }
        Spell other = (Spell) o;
        return (this.name.compareTo(other.name) == 0);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 180 * hashCode + this.code;
        return hashCode;
    }
}
