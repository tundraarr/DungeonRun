/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Controllers;

import dungeonrun.GraveyardState;
import dungeonrun.Views.GraveyardView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**
 *
 * @author Liam
 */

//Controller for the graveyard 
public class GraveyardController implements ActionListener, ComponentListener{
    
    private GraveyardState model;
    private GraveyardView view;
    
    public GraveyardController(GraveyardState model, GraveyardView view)
    {
        this.model = model;
        this.view = view;
        view.SetController(this, this);
    }

    //Assign action to return to the main menu when back button is pressed
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String eStr = e.getActionCommand();
        if(eStr.compareTo("Back") == 0)
        {
            model.BackToMenu();;
        }
    }

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    //When the graveyard is shown, update itself with the list of all deceased characters
    @Override
    public void componentShown(ComponentEvent e) 
    {
        model.UpdateSelf();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
}
