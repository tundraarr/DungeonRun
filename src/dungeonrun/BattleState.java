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
/**
 *
 * @author Liam
 */

//Game state for combat, when the player is battling an enemy
public class BattleState extends State{
    
    //Combat state identifiers
    enum Turn
    {
        PLAYER,
        ENEMY,
        VICTORY,
        DEFEAT
    }
    
    private Random ran = new Random();
    
    //An array of all possible enemies that could appear
    //Program generates a random number between 1 - 100 to select which enemy the player will fight
    private static Enemy[] enemies = new Enemy[]{new Goblin(), new Skeleton(), new LostSoul(), new RavenousSlime(), new UndeadKnight(), new Necromancer(), new TheDarkness()};
    private static int enemyChoice;   
    private static Enemy enemy;
    
    private Turn turn = Turn.PLAYER;
    
    private String selectedSpell;
    private String selectedItem;
    
    @Override
    public State RunState() {
        
        
        
       
        
        return State.ChangeState(States.INTERMISSION);
    }   
    
    //Provides player with an text interface to perform actions in the battle state
    //They are provided information on the enemy they are facing and the enemy's hp
    //They also can view their own hp and mp
    //Provides several options for combat such as attacking, using magic and using items
    private void PlayerTurn()
    {
//        boolean validAns = false;
//        //Display text interface to user
//        while(!validAns)
//        {
//            System.out.println("======FIGHT=======");
//            System.out.println("HP: " + Player.currentHp + "/" + Player.maxHp + " MP: " + Player.currentMp + "/" + Player.maxMp);
//            System.out.println(enemy.name + " HP: " + enemy.hp);
//            System.out.println("1) Attack");
//            System.out.println("2) Magic");
//            System.out.println("3) Items");
//            System.out.println("==================");
//            //Take in the player's choice and handle it accordingly
//            try
//            {
//                userInput = scan.nextLine();
//                switch(Integer.valueOf(userInput))
//                {
//                    case 1:
//                        Attack();
//                        break;
//                    case 2:
//                        ShowSpells();
//                        break;
//                    case 3:
//                        OpenInventory();
//                        break;
//                    //If the input does not  meet any of the cases, throw an InvalidInputException and tell them that their input is invalid
//                    default:
//                        throw new InvalidInputException();
//                }
//                validAns = true;
//            }
//            catch(Exception e)
//            {
//                System.out.println("Invalid Input! Input mut be 1 - 3");
//            }
//            
//            Wait(1000);
//        }
    }
    
    //Spawn the enemy based on the enemy's probability of appearing
    //If the number generated is between the enemy's min & max chance, instantiate that enemy
    public static void SpawnEnemy()
    {
        Random ran = new Random();
        enemyChoice = ran.nextInt(100);
        for(Enemy e : enemies)
        {
            if(enemyChoice >= e.minChance && enemyChoice <= e.maxChance)
            {
                enemy = e;
            }
        }
        System.out.println(enemy.name);
    }
    
    //Specifically used by BattleView to obtain the enemy object so that it can update
    //the enemy components in the view. Not ideal but enemy can not be parsed to view
    //when the view is switched and no buttons are pressed.
    public static Enemy GetEnemy()
    {
        return enemy;
    }
    
    public void UpdateSelf()
    {
        setChanged();
        notifyObservers(enemy);
    }

    //Player attacks the enemy and deals damage to them based on their attack stat
    //Player attacks can also crit (deal double damage), with chance of occurring based on their luck stat
    public void Attack()
    {
        int pDamage = 3 + (int)Math.ceil(Math.pow(Player.GetAtk(), 1.35) * 0.65);
        if(ran.nextInt(100) <= (Player.GetLuck() * 2))
        {
            pDamage *= 2;
            System.out.println("CRITICAL HIT!");
        }
        enemy.hp -= pDamage;
        System.out.println(Player.GetName() + " attacks!");
        Wait(1000);
        System.out.println(Player.GetName() + " did: " + pDamage+ " damage");
        CheckEnemyHp();
    }
    
    public void LoadSpells()
    {
        for(Spell s : SpellList.GetAllSpells())
        {
            if(Player.GetLevel() >= s.levelReq)
            {
                if(!Player.GetSpells().contains(s))
                {
                    Player.GetSpells().add(s);
                }
            }
        }
    }
    
    public void SetSelectedSpell(String spell)
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
    
    //Show the player all available GetSpells() they can use
    //Player inputs the corresponding number from the displayed list to use the spell 
    public void CastSpell()
    {
        //If the player has sufficient mp they can cast the spell, otherwise they will be told they don't have enough mp
        if(Player.GetCurrentMp() >= Player.GetSpells().get(Integer.valueOf(userInput) - 1).manaCost)
        {
            Player.GetSpells().get(Integer.valueOf(userInput) - 1).CastSpell(enemy);
            Player.SetCurrentMp(Player.GetCurrentMp() - Player.GetSpells().get(Integer.valueOf(userInput) - 1).manaCost);
            CheckEnemyHp();
        }
        else
        {
            System.out.println("Not enough MP to cast spell");
        }
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
    
    
    private void EnemyTurn()
    {
        //Enemy attacks the player based on their attack value (minus the player's defense) and display this information to the users
        //The damage taken is randomized between a low and high bound
        int enemyDmg = (int)Math.floor(enemy.dmg * (1.0f - (Player.GetDef() * 0.02)));
        int high = (int)Math.ceil(enemyDmg * 1.2);
        int low = (int)Math.ceil(enemyDmg * 0.80);
        enemyDmg = ran.nextInt(high-low) + low;
        
        Player.SetCurrentHp(Player.GetCurrentHp() - enemyDmg);
        System.out.println(enemy.name + " attacks!");
        Wait(1000);
        System.out.println(enemy.name + " does " + enemyDmg + " damage");
        
        //If the player's hp is reduced to 0 or less they are defeated otherwise, return to player's turn
        //Their player name and level is saved in the graveyard (their save is then deleted) and they are sent back to the main menu
        if(Player.GetCurrentHp() <= 0)
        {
            System.out.println(Player.GetName() + " has been defeated...");
            System.out.println("Type anything to continue...");
            try
            {
                GraveSystem.AddToGraveyard();
            }
            catch(Exception e)
            {
                System.err.println("Error - " + e);
            }
            SaveSystem.DeleteSave();
            scan.nextLine();
            turn = Turn.DEFEAT;
        }
        else
        {
            //System.out.println(enemy.name + " does " + enemy.dmg + " damage");
            turn = Turn.PLAYER;
        }
        
        Wait(1000);
    }
    
    //Used to check the enemy's hp after it has taken damage from the player
    //If the enemy is defeated then the player is victorious and the state changes
    //Otherwise, conntinue onto the enemy's turn
    private void CheckEnemyHp()
    {
        if(enemy.hp <= 0)
        {
            System.out.println("Enemy has been defeated!");
            turn = Turn.VICTORY;
        }
        else
        {
            //System.out.println(Player.name + " did: " + Player.atk + " damage");
            turn = Turn.ENEMY;
        }
    }
    
    //Add the enemy's rewards to the player
    private void CollectRewards()
    {
        System.out.println("Collecting rewards from enemy");
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
