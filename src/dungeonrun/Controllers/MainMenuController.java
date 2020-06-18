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
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Liam
 */

//Controller for the main menu
public class MainMenuController implements ActionListener, ComponentListener{
    
    private MainMenuState model;
    private MainMenuView view;

    public MainMenuController(MainMenuState theModel, MainMenuView theView)
    {
        this.model = theModel;
        this.view = theView;
        view.SetController(this, this);
    }
    
    //Assign actions to the buttons in the main menu
    @Override
    public void actionPerformed(ActionEvent e) 
    {                
        String eStr = e.getActionCommand();
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
            MainContainer.ChangeView("GraveyardView");
        }
        else if(eStr.compareTo("Exit") == 0)
        {
            System.exit(0);
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    //Update self when main menu is shown
    @Override
    public void componentShown(ComponentEvent e) 
    {
        model.UpdateSelf();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
    
}
