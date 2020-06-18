/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.*;
import dungeonrun.Spells.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author Liam
 */

//The new game interface that allows the player to create a new character by creating a new name
public class NewGameState extends State{
      
    //Set the default values for a new character
    private void InitializeNewPlayerStats()
    {
        Player.SetLevel(1);
        Player.SetGold(0);
        Player.SetMaxHp(100);
        Player.SetMaxMp(50);
        Player.SetCurrentHp(Player.GetMaxHp());
        Player.SetCurrentMp(Player.GetMaxMp());
        Player.SetAtk(1);
        Player.SetMagicAtk(1);
        Player.SetDef(1);
        Player.SetLuck(1);
        Player.SetNewSpells();
        Player.SetNewInventory();
    }
    
    //Create a new character by having the user input their name into a text field
    //If it is valid (not null or starts with space) the character will be created
    public void CreateNewCharacter(String name)
    {
        try
        {
            if(name.startsWith(" ") || name.isEmpty())
            {
                throw new NullInputException();
            }
            else
            {
                Player.SetName(name);
                InitializeNewPlayerStats();
                MainContainer.ChangeView("IntermissionView");
                SaveSystem.SaveGame();
            }
        }
        catch(NullInputException e)
        {
            setChanged();
            notifyObservers(false);
        }
    }
}
