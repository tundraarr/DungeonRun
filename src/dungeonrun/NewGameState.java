/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import dungeonrun.Items.*;
import dungeonrun.Spells.*;
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
        boolean validAns = false;
        while(!validAns)
        {
            try
            {
                //Take the player's input as a string and allow them to confirm if it isn't empty
                System.out.println("What is your name? ");
                Player.name = scan.nextLine();
                if(Player.name.startsWith(" ") || Player.name.isEmpty())
                {
                    throw new NullInputException();
                }

                System.out.println("Are you sure you want to be " + Player.name + "? (y/n)");

                //Handle the player's confirmation input of 'y' or 'n'
                boolean YoN = false;
                while(!YoN)
                {
                    userInput = scan.nextLine();
                    if(userInput.compareTo("y") == 0)
                    {
                        validAns = true;
                        InitializeNewPlayerStats();
                        YoN = true;
                        try
                        {
                            SaveSystem.SaveGame();
                        }
                        catch(Exception e)
                        {
                            System.err.println("Error - " + e);
                        }
                    }
                    else if(userInput.compareTo("n") == 0)
                    {
                        YoN = true;
                    }
                    else
                    {
                        System.out.println("Please enter y/n");
                    }
                }
            }
            catch(NullInputException e)
            {
                System.out.println("Name cannot be null or start with space");
            }
            
        }
    }
    
    //Set the default values for a new character
    private void InitializeNewPlayerStats()
    {
        Player.level = 1;
        Player.gold = 0;
        Player.maxHp = 100;
        Player.maxMp = 50;
        Player.currentHp = Player.maxHp;
        Player.currentMp = Player.maxMp;
        Player.atk = 1;
        Player.magicAtk = 1;
        Player.def = 1;
        Player.luck = 1;
        Player.spells = new ArrayList<Spell>();
        Player.inventory = new HashMap<Item, Integer>();
    }
}
