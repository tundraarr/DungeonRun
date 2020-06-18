/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Controllers;

import dungeonrun.NewGameState;
import dungeonrun.Views.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Liam
 */

//Controller for when the player is starting a new game
public class NewGameController implements ActionListener, ComponentListener{
    
    private NewGameState model;
    private NewGameView view;

    public NewGameController(NewGameState theModel, NewGameView theView)
    {
        this.model = theModel;
        this.view = theView;
        view.SetController(this, this);
    }
    
    //Attach actions to the button in new game, the name confirmation button
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String eStr = e.getActionCommand();
        if(eStr.compareTo("Confirm") == 0)
        {
            model.CreateNewCharacter(view.GetUserInput());
            
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    //Update itself when the new game view is shown
    @Override
    public void componentShown(ComponentEvent e) 
    {
        model.UpdateSelf();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
}
