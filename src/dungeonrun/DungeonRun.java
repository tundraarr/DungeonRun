/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

/**
 *
 * @author Liam
 */
public class DungeonRun {

    /**
     * @param args the command line arguments
     */
    //static boolean isPlaying = true;
    
    public static void main(String[] args) {
        //Intialize the default state of the game to be the Main Menu State
        State currentState = new MainMenuState();
        boolean isPlaying = true;
        
        //Change the state of the game while the game loop is runnning
        while(isPlaying)
        {
            //The current state is overwritten by the player's choices in each of the states
            currentState = currentState.RunState();
            if(currentState.theState == States.END)
            {
                isPlaying = false;
            }
        }
        
        System.out.println("Quitting Application");
    }
    
}
