/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import dungeonrun.Controllers.*;
import dungeonrun.Views.*;
import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.*;

/**
 *
 * @author Liam
 */
public class DungeonRun {
    
    public static JFrame frame;
    public static JPanel container;
    
    public static void main(String[] args) {
        //Intialize the default state of the game to be the Main Menu State
        State currentState = new MainMenuState();
        boolean isPlaying = true;
        
        ViewManager vm = new ViewManager();
        vm.IntializeViews();
        
        
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(650, 450));
        frame.add(MainContainer.container);
        frame.pack();
        frame.setVisible(true);      
        
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
