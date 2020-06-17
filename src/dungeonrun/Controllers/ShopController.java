/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Controllers;

import dungeonrun.ShopState;
import dungeonrun.Views.ShopView;
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
public class ShopController implements ActionListener, ListSelectionListener, ComponentListener{

    private ShopState model;
    private ShopView view;
    
    public ShopController(ShopState model, ShopView view)
    {
        this.model = model;
        this.view = view;
        view.SetController(this, this, this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e)
    {
        String eStr = e.getActionCommand();
        if(eStr.compareTo("Back") == 0)
        {
            model.CloseShop();
        }
        else if(eStr.compareTo("Buy") == 0)
        {
            model.BuyItem();
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        try
        {
            if(e.getValueIsAdjusting() == false)
            {
                //Get the currently selected item from the JList
                JList inventory = (JList)e.getSource();

                model.SetSelectedItem(inventory.getSelectedIndex());
            }
        }
        catch(NullPointerException nex)
        {
            
            System.err.println(nex);
        }   
    }

    @Override
    public void componentResized(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) 
    {
        model.UpdateSelf();
    }

    @Override
    public void componentHidden(ComponentEvent e) {}
    
}
