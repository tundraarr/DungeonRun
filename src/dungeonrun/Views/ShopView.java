/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.Items.*;
import dungeonrun.ShopInventory;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Liam
 */
public class ShopView extends JPanel implements Observer{
    
    private JLabel shopTitle = new JLabel("SHOP");
    private JList shopInventory = new JList();
    private JScrollPane shopContainer = new JScrollPane(shopInventory);
    private JPanel shopActions = new JPanel();
    private JButton backButton = new JButton("Back");
    private JButton buyButton = new JButton("Buy");
    
    public ShopView()
    {
        shopActions.setLayout(new FlowLayout());
        
        shopContainer.setPreferredSize(new Dimension(250, 200));
        
        add(shopTitle);
        add(shopContainer);
        
        shopActions.add(backButton);
        shopActions.add(buyButton);
        add(shopActions);
    }

    private void SetupShopDisplay(Item[] items)
    {
        ArrayList<String> shopItems = new ArrayList<String>();
        for(Item i : items)
        {
            shopItems.add(i.GetName() + " Cost: " + i.GetCost() + " | " + i.GetDescription());
        }
        shopInventory.setListData(shopItems.toArray());
    }  
    
    public void SetController(ActionListener cntrl, ListSelectionListener list, ComponentListener cL)
    {
        shopInventory.addListSelectionListener(list);
        backButton.addActionListener(cntrl);
        buyButton.addActionListener(cntrl);
        this.addComponentListener(cL);
    }  
    
    @Override
    public void update(Observable o, Object obj) 
    {
        SetupShopDisplay(ShopInventory.GetItems());
    }
}
