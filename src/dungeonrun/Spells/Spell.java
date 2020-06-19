/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools   |   Templates
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
    private String name;
    private String description;
    private int levelReq;
    private int manaCost;
    private int code;
    
    //Method to cast a spell 
    public abstract String CastSpell(Enemy enemy);    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevelReq() {
        return levelReq;
    }

    public void setLevelReq(int levelReq) {
        this.levelReq = levelReq;
    }

    public int getManaCost() {
        return manaCost;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == null) {
        return false;
        }
        if (!(o instanceof Spell)) {
        return false;
        }
        Spell other = (Spell) o;
        return (this.getName().compareTo(other.getName()) == 0);
    }
    
    @Override
    public int hashCode() {
        int hashCode = 1;
        hashCode = 180 * hashCode + this.getCode();
        return hashCode;
    }
}
