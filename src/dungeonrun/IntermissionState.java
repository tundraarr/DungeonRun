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
    private String selectedItem;
    
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
                //AccessInventory();
                break;
            //Shows all of the player's stats
            case 2:
                break;
            //Proceed onto an event (treasure, trap, battle)
            case 3:
                //newState = PlayEvent();
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
    
    //Show the player's GetInventory() to them and allow them to use items from it by inputting the item's name
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
    
    public void SetSelectedItem(String itemName)
    {
        selectedItem = itemName;
    }
    
    public void OpenShop()
    {
        setChanged();
        notifyObservers("Shop");
    }
    
    public void ShowPopUp()
    {
        setChanged();
        notifyObservers(PlayEvent());
    }
    
    public void SaveAndExit()
    {
        SaveSystem.SaveGame();
        System.exit(0);
    }
    
    //Generate and run the event that the player will encounter when they choose to proceed
    private String PlayEvent()
    {
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

        String text = theEvent.RunEvent();
     
        return text;
    }    
    
    public void UpdateSelf()
    {
        setChanged();
        notifyObservers(null);
    }
}
