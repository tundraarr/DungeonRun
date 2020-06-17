/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Enemies.*;
import dungeonrun.Items.*;
import dungeonrun.Spells.*;
import java.awt.CardLayout;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author Liam
 */

//Game state for combat, when the player is battling an enemy
public class BattleState extends State{
      
    private Random ran = new Random();
    
    //An array of all possible enemies that could appear
    //Program generates a random number between 1 - 100 to select which enemy the player will fight
    //private Enemy[] enemies = new Enemy[]{new Goblin(), new Skeleton(), new LostSoul(), new RavenousSlime(), new UndeadKnight(), new Necromancer(), new TheDarkness()};
    private int enemyChoice;   
    private Enemy enemy;
    private String combatText = "";
    
    
    private int selectedSpell;
    private String selectedItem;
    
    @Override
    public State RunState() {
        
        
        
       
        
        return State.ChangeState(States.INTERMISSION);
    }   
      
    //Spawn the enemy based on the enemy's probability of appearing
    //If the number generated is between the enemy's min & max chance, instantiate that enemy
    public void SpawnEnemy()
    {
        Enemy[] enemies = new Enemy[]{new Goblin(), new Skeleton(), new LostSoul(), new RavenousSlime(), new UndeadKnight(), new Necromancer(), new TheDarkness()};

        enemyChoice = ran.nextInt(100);
        for(Enemy e : enemies)
        {
            if(enemyChoice >= e.minChance && enemyChoice <= e.maxChance)
            {
                enemy = e;
            }
        }
    }   
    
    //Update the BattleView
    public void UpdateSelf()
    {
        setChanged();
        notifyObservers(new CombatInfo(enemy, combatText));
    }    

    private void ReturnToIntermission()
    {
        UpdateSelf();
        MainContainer.ChangeView("IntermissionView");
    }
    
    private void ReturnToMainMenu()
    {
        UpdateSelf();
        MainContainer.ChangeView("MainMenuView");
    }
    
    //Player attacks the enemy and deals damage to them based on their attack stat
    //Player attacks can also crit (deal double damage), with chance of occurring based on their luck stat
    public void Attack()
    {      
        int pDamage = 3 + (int)Math.ceil(Math.pow(Player.GetAtk(), 1.35) * 0.65);
        if(ran.nextInt(100) <= (Player.GetLuck() * 2))
        {
            pDamage *= 2;
            combatText += ("CRITICAL HIT! ");
        }
        enemy.hp -= pDamage;
        
        combatText += ("["+Player.GetName()+"]" + " did: " + pDamage+ " damage   |   ");
        UpdateSelf();
        //If enemy is defeated
        if(CheckEnemyHp())
        {
            ReturnToIntermission();
            combatText = "";
        }
        else
        {
            EnemyTurn();
        }
    }
    
    public void CastSpell()
    {
        //If the player has sufficient mp they can cast the spell, otherwise they will be told they don't have enough mp
        try
        {
            if(Player.GetCurrentMp() >= Player.GetSpells().get(selectedSpell).manaCost)
            {
                Player.GetSpells().get(selectedSpell).CastSpell(enemy);
                Player.SetCurrentMp(Player.GetCurrentMp() - Player.GetSpells().get(Integer.valueOf(userInput) - 1).manaCost);
                UpdateSelf();
                //If enemy is defeated
                if(CheckEnemyHp())
                {
                    ReturnToIntermission();
                    combatText = "";
                }
                else
                {
                    EnemyTurn();
                }
            }
            else
            {
                System.out.println("Not enough MP to cast spell");
                combatText = "Not enough mp to cast!";
                UpdateSelf();
            }
        }
        catch(ArrayIndexOutOfBoundsException e)
        {
            //This exception will occur when player backs out from the spell selection panel
            //Does not affect application, can be ignored
        }
        combatText = "";
    }
    
    private void EnemyTurn()
    {
        //Enemy attacks the player based on their attack value (minus the player's defense) and display this information to the users
        //The damage taken is randomized between a low and high bound
        int enemyDmg = (int)Math.floor(enemy.dmg * (1.0f - (Player.GetDef() * 0.02)));
        int high = (int)Math.ceil(enemyDmg * 1.2);
        int low = (int)Math.ceil(enemyDmg * 0.80);
        enemyDmg = ran.nextInt(high-low) + low;
        
        Player.SetCurrentHp(Player.GetCurrentHp() - enemyDmg);
        combatText += ("["+enemy.name+"]" + " does: " + enemyDmg + " damage");
        UpdateSelf();
        
        //If the player's hp is reduced to 0 or less they are defeated otherwise, return to player's turn
        //Their player name and level is saved in the graveyard (their save is then deleted) and they are sent back to the main menu
        if(Player.GetCurrentHp() <= 0)
        {
            combatText = ("Defeat");
            try
            {
                GraveSystem.AddToGraveyard();
            }
            catch(Exception e)
            {
                System.err.println("Error - " + e);
            }
            SaveSystem.DeleteSave();
            ReturnToMainMenu();     
        }   
        combatText = "";
    }
    
    public void SetSelectedSpell(int spell)
    {
        this.selectedSpell = spell;
    }
    
    public void SetSelectedItem(String item)
    {
        this.selectedItem = item;
    }
    
    public void ViewSpells()
    {
        setChanged();
        notifyObservers("SpellsPanel");
    }
    
    public void ViewItems()
    {
        setChanged();
        notifyObservers("InventoryPanel");
    }
    
    public void GoBack()
    {
        setChanged();
        notifyObservers("ButtonsPanel");
    }
    
    public void ShowBlankPanel()
    {
        setChanged();
        notifyObservers("BlankPanel");
    }
  
    public void UseItem()
    {
        Item theItem = Player.CheckItemInInventory(selectedItem);
        if(theItem != null)
        {
            theItem.UseItem();
            
            //Make the info text from intermission view display text
            System.out.println("You used: " + theItem.name);

            Player.GetInventory().replace(theItem, Player.GetInventory().get(theItem) - 1);
            if(Player.GetInventory().get(theItem) == 0)
            {
                Player.GetInventory().remove(theItem);
            }
            
            setChanged();
            notifyObservers(null);
            selectedItem = "";
        }  
    }
    
    
    //Used to check the enemy's hp after it has taken damage from the player
    //If the enemy is defeated then the player is victorious and the state changes
    //Otherwise, conntinue onto the enemy's turn
    private boolean CheckEnemyHp()
    {
        boolean enemyDefeated = false;
        if(enemy.hp <= 0)
        {
            System.out.println("Enemy has been defeated!");
            enemyDefeated = true;
            combatText = "Victory";
            CollectRewards();
        }
        return enemyDefeated;
    }
    
    //Add the enemy's rewards to the player
    private void CollectRewards()
    {
        Player.SetGold(Player.GetGold() + enemy.gold);
    }
    
    //Creates a delay between events (particularly text) in the battle
    private void Wait(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch(Exception e){}
    }
}
