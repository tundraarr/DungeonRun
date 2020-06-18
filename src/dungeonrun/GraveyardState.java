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
//The model for the Graveyard View
//Enables player to return to the main menu
public class GraveyardState extends State{

    //Go back to the main menu view
    public void BackToMenu()
    {
        setChanged();
        notifyObservers("Back");
    } 
}
