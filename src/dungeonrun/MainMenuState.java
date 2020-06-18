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
      
    //Create a new game save for the player and delete their previous save
    //And go to the New Game view where they will create their new character
    public void NewGame()
    {
        //Delete data from database 
        SaveSystem.DeleteSave();
        //Go to new game state and view
        MainContainer.ChangeView("NewGameView");
    }
    
    //Load their previous save if it exists and go to the Dungen (Entrance)Intermission View 
    public void LoadGame()
    {
        boolean dataExists = SaveSystem.LoadGame();
         setChanged();
         notifyObservers(dataExists);
    }   
}
