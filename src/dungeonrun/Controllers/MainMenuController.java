/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Controllers;

import dungeonrun.*;
import dungeonrun.Views.MainMenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Liam
 */
public class MainMenuController implements ActionListener{
    
    private MainMenuState model;
    private MainMenuView view;

    public MainMenuController(MainMenuState theModel, MainMenuView theView)
    {
        this.model = theModel;
        this.view = theView;
        view.SetController(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {        
        try 
        {            
            String eStr = e.getActionCommand();
            System.out.println(eStr);
            if (eStr.compareTo("New Game") == 0) 
            {
                model.NewGame();
            } 
            else if (eStr.compareTo("Load Game") == 0) 
            {
                model.LoadGame();
            } 
            else if (eStr.compareTo("Graveyard") == 0) 
            {
                
            }
            else if(eStr.compareTo("Exit") == 0)
            {
                System.exit(0);
            }
        } 
        catch (NumberFormatException ex) 
        {
            System.out.println(ex);

        }
    }
    
}
