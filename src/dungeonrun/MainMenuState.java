/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import java.io.IOException;

/**
 *
 * @author Liam
 */
public class MainMenuState extends State{
    
    //Display the main menu interface to the player and allow them to choose what they wish to do
    //Player can start a new game, continue their previous run, view the graveyard and quit
    public State RunState()
    {
        State nextState = null;
        while(loopState)
        {
            try
            {
                System.out.println("======DUNGEON RUN======");
                System.out.println("=======================");
                System.out.println("1) New Game");
                System.out.println("2) Continue Game");
                System.out.println("3) View Graveyard");
                System.out.println("4) Quit");
                System.out.println("=======================");

                userInput = scan.nextLine();
                nextState = HandleChoice();
            }
            catch(InvalidInputException | NumberFormatException e)
            {
                System.out.println("Input Invalid. Enter a value 1 - 4");
            }
        }
        
        return nextState;
    }
    
    //Use the user's input to discern what they wish to do
    public State HandleChoice() throws InvalidInputException
    {
        //Change the user's input from a string to an int
        int choice = Integer.valueOf(userInput);
        State newState = null;
        
        //Compare the user's input to return an appropriate state or perform appropriate action
        switch(choice)
        {
            //Start a new game - go to new game state
            case 1:
                newState = ChangeState(States.NEWGAME);
                loopState = false;
                break;
            //Continue a game if there is save data available - go to intermission state
            case 2:
                try
                {
                    //Load data and see if method returns true (if there is data) or false
                    if(SaveSystem.LoadGame())
                    {
                        newState =  ChangeState(States.INTERMISSION);
                        loopState = false;
                    }
                    else
                    {
                        newState = null;
                        System.out.println("No Data Found");
                    }
                }
                catch(IOException e)
                {
                    System.err.println(e);
                    System.err.println("Load error");
                }
                break;
            //Print out a list of the player's deceased characters
            case 3:
                try
                {
                    GraveSystem.ViewGraveyard();
                    System.out.println("Type anything to continue...");
                    scan.nextLine();
                }
                catch(IOException e)
                {
                    System.err.println("Error in getting graveyard data - " + e);
                }               
                return null;
            //Close the application
            case 4:
                newState = ChangeState(States.END);
                loopState = false;
                break;
            //If the input does not  meet any of the cases, throw an InvalidInputException
            default:
                throw new InvalidInputException();
        }
        return newState;
    }
}
