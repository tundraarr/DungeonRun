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
        
        //Intialize all views necessary for this application
        ViewManager vm = new ViewManager();
        vm.IntializeViews();      
      
        //Intialize the frame to contain all the views/panels
        frame = new JFrame("Frame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(650, 450));
        frame.add(MainContainer.container);
        frame.pack();
        frame.setVisible(true);      
    }
    
}
