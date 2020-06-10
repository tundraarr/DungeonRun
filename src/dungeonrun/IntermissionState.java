/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.Item;
import dungeonrun.Spells.*;
//import dungeonrun.Spells.SpellList;
import java.util.Map;
import java.util.Random;
/**
 *
 * @author Liam
 */
public class IntermissionState extends State{

    private Random rand = new Random();
    private SpellList spellList = new SpellList();
    
    @Override
    public State RunState() {
        
        return ChooseAction();     
    }
    
    //The primary interface for the user to interact with when playing
    //Allows them to proceed to events, buy from shop, look at stats and GetInventory()
    public State ChooseAction()
    {
//        State nextState = null;
//        LoadSpells();
//        while(loopState)
//        {
//            try
//            {
//                System.out.println("===============================================");
//                System.out.println("Name: " + Player.name);
//                System.out.println("Player Level: " + Player.level);
//                System.out.println("Gold: " + Player.gold);
//                System.out.println("HP: " + Player.currentHp + "/" + Player.maxHp + " MP: " + Player.currentMp + "/" + Player.maxMp);
//                System.out.println("1) View Inventory");
//                System.out.println("2) View Stats");
//                System.out.println("3) Proceed");
//                System.out.println("4) Shop");
//                System.out.println("5) Save and Quit");
//                System.out.println("===============================================");
//                
//                userInput = scan.nextLine();
//
//                nextState = HandleChoice();
//                if(nextState != null)
//                {
//                    loopState = false;
//                }
//            }
//            catch(Exception e)
//            {
//                System.out.println("Invalid Input! Please choose a valid input (1 - 5)\n");
//            }
//        }
        return null;
    }
    
    private State HandleChoice() throws InvalidInputException
    {
        //Change the user's input from a string to an int
        int choice = Integer.valueOf(userInput);
        State newState = null;
        
        //Compare the user's input to return an appropriate state or perform appropriate action
        switch(choice)
        {
            //Go to the player's GetInventory() - go to InventoryState
            case 1:
                AccessInventory();
                break;
            //Shows all of the player's stats
            case 2:
                ShowStats();
                break;
            //Proceed onto an event (treasure, trap, battle)
            case 3:
                newState = PlayEvent();
                break;
            //Takes player to the shop - go to ShopState
            case 4:
                newState = ChangeState(States.SHOP);
                loopState = false;
                break;
            //Save the player's data and quit the game
            case 5:
                try
                {
                    SaveSystem.SaveGame();
                }
                catch(Exception e)
                {
                    System.err.println("Save game error");
                }
                newState = ChangeState(States.END);
                loopState = false;
                break;
            //If the input does not  meet any of the cases, throw an InvalidInputException
            default:
                throw new InvalidInputException();
        }
        return newState;
    }
    
    //Show the player their stats
    private void ShowStats()
    {
//        System.out.println("=========STATS=========");
//        System.out.println("Name: " + Player.name);
//        System.out.println("Level: " + Player.level);
//        System.out.println("HP: " + Player.currentHp + " / " + Player.maxHp);
//        System.out.println("MP: " + Player.currentMp + " / " + Player.maxMp);
//        System.out.println("Attack: " + Player.atk);
//        System.out.println("Magic Attack: " + Player.magicAtk);
//        System.out.println("Defense: " + Player.def);
//        System.out.println("Luck: " + Player.luck);
//        System.out.println("Type anything to continue......");
//        scan.nextLine();
    }
    
    //Show the player's GetInventory() to them and allow them to use items from it by inputting the item's name
    private void AccessInventory()
    {
        boolean validAns = false;
        while(!validAns)
        {
            //Display all available items in the player's GetInventory()
            System.out.println("==============INVENTORY================");
            System.out.println("Input name of item to use or '0' to go back.");
            System.out.println("0) Back");
            for(Map.Entry<Item, Integer> entry : Player.GetInventory().entrySet())
            {
                System.out.println(entry.getKey().name + " x" + entry.getValue() + " | " + entry.getKey().description);
            }
            System.out.println("=======================================");
            
            //Get the user's input for which item they want to use
            try
            {
                userInput = scan.nextLine();
                Item theItem = Player.CheckItemInInventory(userInput);
                if(theItem != null)
                {
                    validAns = true;
                    theItem.UseItem();
                    System.out.println("You used: " + theItem.name);
                    
                    //Deduct 1 from the item count
                    //If the item has a count of 0, remove it from the the GetInventory()
                    Player.GetInventory().replace(theItem, Player.GetInventory().get(theItem) - 1);
                    if(Player.GetInventory().get(theItem) == 0)
                    {
                        Player.GetInventory().remove(theItem);
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
                    throw new InvalidInputException();
                }
            }
            catch(Exception e)
            {
                System.out.println("Item not found! Type in the item name exactly as displayed or type '0' to go back.");
            }           
        }
    }
    
    //Generate and run the event that the player will encounter when they choose to proceed
    private State PlayEvent()
    {
        State nextState = null;
        
        //Array of the possible events that can occur
        //Each event has as certain probability of happening
        Event[] events = new Event[]{new TreasureEvent(), new TrapEvent(), new BattleEvent()};
        Event theEvent = null;
        
        int eventProb = rand.nextInt(100);

        //Iterate through the possible events and check if the generated eventProb number falls in probability of the event
        for(Event e : events)
        {
            if(eventProb >= e.minChance && eventProb <= e.maxChance)
            {
                theEvent = e;
            }
        }

        if(theEvent != null)
        {
            nextState = theEvent.RunEvent();
        }
        
        if(nextState != null)
        {
            loopState = false;
        }
        
        return  nextState;
    }
    
    //Add a new spell to the player's list of usable GetSpells() based on their level
    private void LoadSpells()
    {
        for(Spell s : spellList.allSpells)
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
}
