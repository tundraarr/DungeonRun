/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Enemies.*;
import dungeonrun.Items.*;
import dungeonrun.Spells.*;
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
    private Enemy[] enemies = new Enemy[]{new Goblin(), new Skeleton(), new LostSoul(), new RavenousSlime(), new UndeadKnight(), new VampireLord(), new TheDarkness()};
    private int enemyChoice = ran.nextInt(100);
    
    private Enemy enemy;
    private Turn turn = Turn.PLAYER;
    
    @Override
    public State RunState() {
        
        SpawnEnemy();
        
        while(loopState)
        {
            if(turn == Turn.PLAYER)
            {
                PlayerTurn();
            }
            else if(turn == Turn.ENEMY)
            {
                EnemyTurn();
            }
            else if(turn == Turn.VICTORY)
            {
                loopState = false;
                CollectRewards();
                Player.LevelUp();
            }
            else if(turn == Turn.DEFEAT)
            {
                loopState = false;
                System.out.println("Your journey ends.....but you may begin anew");
                return State.ChangeState(States.MAINMENU);
            }
        }
        
        return State.ChangeState(States.INTERMISSION);
    }
    
    //Provides player with an text interface to perform actions in the battle state
    //They are provided information on the enemy they are facing and the enemy's hp
    //They also can view their own hp and mp
    //Provides several options for combat such as attacking, using magic and using items
    private void PlayerTurn()
    {
        boolean validAns = false;
        //Display text interface to user
        while(!validAns)
        {
            System.out.println("======FIGHT=======");
            System.out.println("HP: " + Player.currentHp + "/" + Player.maxHp + " MP: " + Player.currentMp + "/" + Player.maxMp);
            System.out.println(enemy.name + " HP: " + enemy.hp);
            System.out.println("1) Attack");
            System.out.println("2) Magic");
            System.out.println("3) Items");
            System.out.println("==================");
            //Take in the player's choice and handle it accordingly
            try
            {
                userInput = scan.nextLine();
                switch(Integer.valueOf(userInput))
                {
                    case 1:
                        Attack();
                        break;
                    case 2:
                        ShowSpells();
                        break;
                    case 3:
                        OpenInventory();
                        break;
                    //If the input does not  meet any of the cases, throw an InvalidInputException and tell them that their input is invalid
                    default:
                        throw new InvalidInputException();
                }
                validAns = true;
            }
            catch(Exception e)
            {
                System.out.println("Invalid Input! Input mut be 1 - 3");
            }
            
            Wait(1000);
        }
    }
    
    //Spawn the enemy based on the enemy's probability of appearing
    //If the number generated is between the enemy's min & max chance, instantiate that enemy
    private void SpawnEnemy()
    {
        for(Enemy e : enemies)
        {
            if(enemyChoice >= e.minChance && enemyChoice <= e.maxChance)
            {
                enemy = e;
            }
        }
    }
    
    //Player attacks the enemy and deals damage to them based on their attack stat
    //Player attacks can also crit (deal double damage), with chance of occurring based on their luck stat
    private void Attack()
    {
        int pDamage = 3 + (int)Math.ceil(Math.pow(Player.atk, 1.35) * 0.65);
        if(ran.nextInt(100) <= (Player.luck * 2))
        {
            pDamage *= 2;
            System.out.println("CRITICAL HIT!");
        }
        enemy.hp -= pDamage;
        System.out.println(Player.name + " attacks!");
        Wait(1000);
        System.out.println(Player.name + " did: " + pDamage+ " damage");
        CheckEnemyHp();
    }
    
    //Access the player's inventory and show them what items they have
    //Items can be used by having the player input the name of the item
    private void OpenInventory()
    {
        boolean validAns = false;
        while(!validAns)
        {         
            //Display all available items in the player's inventory
            System.out.println("==============INVENTORY================");
            System.out.println("Input name of item to use or '0' to go back.");
            System.out.println("0) Back");
            for(Map.Entry<Item, Integer> entry : Player.inventory.entrySet())
            {
                System.out.println(entry.getKey().name + " x" + entry.getValue() + " | " + entry.getKey().description);
            }
            System.out.println("=======================================");
            
            //Get the user's input for which item they want to use
            userInput = scan.nextLine();
            Item theItem = Player.CheckItemInInventory(userInput);
            if(theItem != null)
            {
                validAns = true;
                theItem.UseItem();
                System.out.println("You used: " + theItem.name);
                //Deduct 1 from the item count
                //If the item has a count of 0, remove it from the the inventory
                Player.inventory.replace(theItem, Player.inventory.get(theItem) - 1);
                if(Player.inventory.get(theItem) == 0)
                {
                    Player.inventory.remove(theItem);
                }
            }
            //Return to the combat menu
            else if(Integer.valueOf(userInput) == 0)
            {
                validAns = true;
            }
            //Tell the user that they have entered an invalid name
            else
            {
                System.out.println("Item not found! Make sure you input the name of the item exactly as it is displayed.");
            }
        }
    }
    
    //Show the player all available spells they can use
    //Player inputs the corresponding number from the displayed list to use the spell 
    private void ShowSpells()
    {
        boolean validAns = false;

        while(!validAns)
        {
            //Display all of the spells available to the user and the option to go back
            System.out.println("0) Back");
            for(int i = 0; i < Player.spells.size(); i++)
            {
                System.out.println((i + 1) + ") " + Player.spells.get(i).name + "| MP: " + Player.spells.get(i).manaCost + " | " + Player.spells.get(i).description);
            }
            
            try
            {
                userInput = scan.nextLine();
                //If the user input a value that does not correspond to an existing spell displayed throw an invalid input error.
                if(Integer.valueOf(userInput) > Player.spells.size() || Integer.valueOf(userInput) < 0)
                {
                    throw new InvalidInputException();
                }
                //If the user inputs 0 they are sent back to the combat menu
                else if(Integer.valueOf(userInput) == 0)
                {
                    validAns = true;
                    turn = Turn.PLAYER;
                }
                //If the user inputs a valid value they will cast the spell and end their turn
                else
                {
                    //If the player has sufficient mp they can cast the spell, otherwise they will be told they don't have enough mp
                    if(Player.currentMp >= Player.spells.get(Integer.valueOf(userInput) - 1).manaCost)
                    {
                        validAns = true;
                        Player.spells.get(Integer.valueOf(userInput) - 1).CastSpell(enemy);
                        Player.currentMp -= Player.spells.get(Integer.valueOf(userInput) - 1).manaCost;
                        CheckEnemyHp();
                    }
                    else
                    {
                        System.out.println("Not enough MP to cast spell");
                    }
                }
            }
            catch(Exception e)
            {
                System.out.println("Invalid Input! Try inputting a value between 1 - " + Player.spells.size());
            }
        }
    }

    
    private void EnemyTurn()
    {
        //Enemy attacks the player based on their attack value (minus the player's defense) and display this information to the users
        //The damage taken is randomized between a low and high bound
        int enemyDmg = (int)Math.floor(enemy.dmg * (1.0f - (Player.def * 0.02)));
        int high = (int)Math.ceil(enemyDmg * 1.2);
        int low = (int)Math.ceil(enemyDmg * 0.80);
        enemyDmg = ran.nextInt(high-low) + low;
        
        Player.currentHp -= enemyDmg;
        System.out.println(enemy.name + " attacks!");
        Wait(1000);
        System.out.println(enemy.name + " does " + enemyDmg + " damage");
        
        //If the player's hp is reduced to 0 or less they are defeated otherwise, return to player's turn
        //Their player name and level is saved in the graveyard (their save is then deleted) and they are sent back to the main menu
        if(Player.currentHp <= 0)
        {
            System.out.println(Player.name + " has been defeated...");
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
        Player.gold += enemy.gold;
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
