/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;
import java.util.Observable;
import java.util.Scanner;
/**
 *
 * @author Liam
 */

//An abstract class for the different states the game can be in
public abstract class State extends Observable{
      
    //Method that notifies the observer of a view to run their update method
    //More specifically, the designated default Update action
    public void UpdateSelf()
    {
        setChanged();
        notifyObservers(null);
    }   
}
