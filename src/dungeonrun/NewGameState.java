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
    
    public State RunState()
    {
        while(loopState)
        {
            CreateCharacter();
            loopState = false;
        }
        
        return ChangeState();
    }
    
    public State ChangeState()
    {
        return new IntermissionState();
    }
    
    //Have the player input the name of their new character and proceed to the intermission state
    private void CreateCharacter()
    {
//        boolean validAns = false;
//        while(!validAns)
//        {
//            try
//            {
//                //Take the player's input as a string and allow them to confirm if it isn't empty
//                System.out.println("What is your name? ");
//                Player.name = scan.nextLine();
//                if(Player.name.startsWith(" ") || Player.name.isEmpty())
//                {
//                    throw new NullInputException();
//                }
//
//                System.out.println("Are you sure you want to be " + Player.name + "? (y/n)");
//
//                //Handle the player's confirmation input of 'y' or 'n'
//                boolean YoN = false;
//                while(!YoN)
//                {
//                    userInput = scan.nextLine();
//                    if(userInput.compareTo("y") == 0)
//                    {
//                        validAns = true;
//                        InitializeNewPlayerStats();
//                        YoN = true;
//                        SaveSystem.SaveGame();
//                    }
//                    else if(userInput.compareTo("n") == 0)
//                    {
//                        YoN = true;
//                    }
//                    else
//                    {
//                        System.out.println("Please enter y/n");
//                    }
//                }
//            }
//            catch(NullInputException e)
//            {
//                System.out.println("Name cannot be null or start with space");
//            }
//            
//        }
    }
    
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
            }
        }
        catch(NullInputException e)
        {
            setChanged();
            notifyObservers(false);
        }
    }
}
