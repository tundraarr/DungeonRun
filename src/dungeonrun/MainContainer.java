/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

import java.awt.CardLayout;
import javax.swing.*;

/**
 *
 * @author Liam
 */

//TODO: Encapsulate this??
public class MainContainer{
    
    public static JPanel container = new JPanel(new CardLayout());
    
    public static void ChangeView(String viewName)
    {
        CardLayout cl = (CardLayout)(MainContainer.container.getLayout());
        cl.show(MainContainer.container, viewName);   
    }
    
}
