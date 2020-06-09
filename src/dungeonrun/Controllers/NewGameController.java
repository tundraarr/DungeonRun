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

/**
 *
 * @author Liam
 */
public class NewGameController implements ActionListener{
    
    private NewGameState model;
    private NewGameView view;

    public NewGameController(NewGameState theModel, NewGameView theView)
    {
        this.model = theModel;
        this.view = theView;
        view.SetController(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String eStr = e.getActionCommand();
        if(eStr.compareTo("Confirm") == 0)
        {
            model.CreateNewCharacter(view.GetUserInput());
        }
    }
}
