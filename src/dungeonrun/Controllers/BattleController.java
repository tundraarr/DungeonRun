/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Controllers;

import dungeonrun.BattleState;
import dungeonrun.Views.BattleView;
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

//Controller for when the player is in battle
public class BattleController implements ActionListener, ListSelectionListener, ComponentListener{

    private BattleState model;
    private BattleView view;
    
    public BattleController(BattleState model, BattleView view)
    {
        this.model = model;
        this.view = view;
        view.SetController(this, this, this);
    }
    
    //For buttons presses
    @Override
    public void actionPerformed(ActionEvent e) 
    {
        String eStr = e.getActionCommand();
        if(eStr.compareTo("Attack") == 0)
        {
            model.Attack();
        }
        else if(eStr.compareTo("Spells") == 0)
        {
            model.ViewSpells();
        }
        else if(eStr.compareTo("Items") == 0)
        {
            model.ViewItems();
        }
        else if(eStr.compareTo("Back") == 0)
        {
            model.GoBack();
        }
        else if(eStr.compareTo("Cast") == 0)
        {
            model.CastSpell();
        }
        else if(eStr.compareTo("Use") == 0)
        {
            model.UseItem();
        }
    }

    //For when a JList is interacted with (spells list and inventory list)
    @Override
    public void valueChanged(ListSelectionEvent e) 
    {
        try
        {
            if(e.getValueIsAdjusting() == false)
            {
                //Check which JList is being interacted with
                JList aList = (JList)e.getSource();
                //Set the selected Item if the JList is ItemsList
                if(aList == view.GetItemsJList())
                {
                    String s = aList.getSelectedValue().toString();
                    String[] itemName = s.split(" \\|");
                    model.SetSelectedItem(itemName[0]);
                }
                //Set the selected Spell if the JList is SpellsList
                else if(aList == view.GetSpellsJList())
                {
                    int index = aList.getSelectedIndex();
                    model.SetSelectedSpell(index);
                }               
            }
        }
        catch(NullPointerException nex)
        {
            //Causes null pointer exception when an item is used up and removed from the list
            //Does not negatively impact the application so it can be ignored
        }   
    }

    //When a component is changed
    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    //When the battle view is shown, spawn a new enemy and update itself
    @Override
    public void componentShown(ComponentEvent e) 
    {
        model.SpawnEnemy();
        model.UpdateSelf();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
    
}
