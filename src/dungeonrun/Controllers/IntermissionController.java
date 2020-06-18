/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Controllers;

import dungeonrun.IntermissionState;
import dungeonrun.SaveSystem;
import dungeonrun.Views.IntermissionView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Liam
 */

//The controller for the intermission (dungoen entrance) 
public class IntermissionController implements ActionListener, ListSelectionListener, ComponentListener{
    
    private IntermissionState model;
    private IntermissionView view;

    public IntermissionController(IntermissionState model, IntermissionView view)
    {
        this.model = model;
        this.view = view;
        view.SetController(this, this, this);
    }
    
    //Assign actions to the buttons in the intermission
    @Override
    public void actionPerformed(ActionEvent e) {      
        
        String eStr = e.getActionCommand();
        if(eStr.compareTo("Shop") == 0)
        {
            //Change interPanel to view of Shop
            model.OpenShop();
        }
        else if(eStr.compareTo("Proceed") == 0)
        {
            //Run event method
            model.ShowPopUp();
        }
        else if(eStr.compareTo("Save and Quit") == 0)
        {
            //Save and quit the game
            model.SaveAndExit();
        }
        else if(eStr.compareTo("Use") == 0)
        {
            model.UseItem();
        }
        
    }

    //For when the player selects an item from their inventory - sets what currently selected item is in model
    @Override
    public void valueChanged(ListSelectionEvent e) {
        
        try
        {
            if(e.getValueIsAdjusting() == false)
            {
                //Get the currently selected item from the JList
                JList inventory = (JList)e.getSource();
                //Split the string of JList item so that it consists only of the item's name
                String s = inventory.getSelectedValue().toString();
                String[] itemName = s.split(" \\|");

                model.SetSelectedItem(itemName[0]);
            }
        }
        catch(NullPointerException nex)
        {
            //Causes null pointer exception when an item is used up and removed from the list
            //Does not negatively impact the application so it can be ignored
        }      
    }

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    //When the intermission view is shown it updates itself 
    @Override
    public void componentShown(ComponentEvent e) 
    {
        model.LoadSpells();
        model.UpdateSelf();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
}
