/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Items;

import dungeonrun.Enemies.Enemy;
import dungeonrun.*;

/**
 *
 * @author Liam
 */

//Defines an item
//Items have a name, description, cost and an item code
//They also have a UseItem method that should be overridden and enables the item to be used
public class Item {
    public String name;
    public String description;
    public int cost;
    public int itemCode;
    
    public void UseItem(){};
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
        return false;
        }
        if (!(o instanceof Item)) {
        return false;
        }
        Item other = (Item) o;
        return (this.name.compareTo(other.name) == 0);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 180 * hashCode + this.itemCode;
        return hashCode;
    }


}
