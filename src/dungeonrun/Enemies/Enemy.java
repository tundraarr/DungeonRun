/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Enemies;

/**
 *
 * @author Liam
 */

//Defines an enemy
//An enemy consists of a name, level, hp, dmg, gold, and min/max chance of appearing
public class Enemy {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getDmg() {
        return dmg;
    }

    public void setDmg(int dmg) {
        this.dmg = dmg;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getMinChance() {
        return minChance;
    }

    public void setMinChance(int minChance) {
        this.minChance = minChance;
    }

    public int getMaxChance() {
        return maxChance;
    }

    public void setMaxChance(int maxChance) {
        this.maxChance = maxChance;
    }
    
    private String name;
    private int level;
    private int hp;
    private int dmg;
    private int gold;
    private int minChance;
    private int maxChance;
}
