/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import java.awt.CardLayout;
import java.awt.Component;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Liam
 */

//Intializing the main container inside the frame which is a card layout
//Stores all views (corresponding to every game state) that the user can interact with
public class MainContainer{
    
    public static JPanel container = new JPanel(new CardLayout());
    
    //Change the view within the main container to a designated view
    public static void ChangeView(String viewName)
    {
        CardLayout cl = (CardLayout)(MainContainer.container.getLayout());
        cl.show(MainContainer.container, viewName);   
    }   
}
