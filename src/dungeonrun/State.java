/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.util.Scanner;
/**
 *
 * @author Liam
 */

//An abstract class for the different states the game can be in
//All states can take inputs from the user, and have a method to run the stuff that happens in that state
//Also contains a static method that changes the current state by returning a new state
public abstract class State {
    
    public States theState;
    public boolean loopState = true;
    public Scanner scan = new Scanner(System.in);
    public String userInput;
    
    public abstract State RunState();
    
    //Take in a states enum and use it to discern which state to change to
    public static State ChangeState(States state)
    {
        State nextState = null;
        
        switch(state)
        {
            case MAINMENU:
                nextState = new MainMenuState();
                break;
            case NEWGAME:
                nextState = new NewGameState();
                break;
            case INTERMISSION:
                nextState = new IntermissionState();
                break;
            case INVENTORY:
                break;
            case SHOP:
                nextState = new ShopState();
                break;
            case BATTLE:
                nextState = new BattleState();
                break;
            case END:
                nextState = new EndState();
                break;
            default:
                System.err.println("State to change to could not be found!");
        }
        
        return nextState;
    }
}
