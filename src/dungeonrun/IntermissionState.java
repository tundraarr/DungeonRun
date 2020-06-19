/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.Item;
import dungeonrun.Spells.*;
import java.util.Map;
import java.util.Random;
/**
 *
 * @author Liam
 */
public class IntermissionState extends State{

    private Random rand = new Random();
    private String selectedItem;
    private SpellList spellList = new SpellList();
    
    //Update the player's spells list (spells they can use) based on their level
    public  void LoadSpells()
    {
        for(Spell s : spellList.GetAllSpells())
        {
            if(Player.GetLevel() >= s.getLevelReq())
            {
                if(!Player.GetSpells().contains(s))
                {
                    Player.GetSpells().add(s);
                }
            }
        }
    }
    
    //Use the item they selected from their inventory(JList) from the view
    public void UseItem()
    {
        Item theItem = Player.CheckItemInInventory(selectedItem);
        if(theItem != null)
        {
            theItem.UseItem();          

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
    
    //Set what the currently selected item from their inventory(JList) is
    public void SetSelectedItem(String itemName)
    {
        selectedItem = itemName;
    }
    
    //Open the view of the shop
    public void OpenShop()
    {
        setChanged();
        notifyObservers("Shop");
    }
    
    //Show a popup box of details regarding special events (treasure and trap event)
    public void ShowPopUp()
    {
        setChanged();
        notifyObservers(PlayEvent());
    }
    
    //Save the player's data and quit the application
    public void SaveAndExit()
    {
        SaveSystem.SaveGame();
        System.exit(0);
    }
    
    //Generate and run the event that the player will encounter when they choose to proceed
    //Return the text from these events to be displayed in a popup (excluding battle event)
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
}
