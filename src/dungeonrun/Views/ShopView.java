/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.Items.*;
import dungeonrun.ShopInventory;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
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

//The shop view for the player to purchase items
public class ShopView extends JPanel implements Observer{
    
    private JLabel shopTitle = new JLabel("SHOP");
    private JList shopInventory = new JList();
    private JScrollPane shopContainer = new JScrollPane(shopInventory);
    private JPanel shopActions = new JPanel();
    private JButton backButton = new JButton("Back");
    private JButton buyButton = new JButton("Buy");
    
    public ShopView()
    {
        //Setup of the shop panel's layout and color
        this.setBackground(Color.GRAY);
        shopActions.setLayout(new FlowLayout());
        
        //Setup of container consisting of the shop's items (a JList)
        shopContainer.setPreferredSize(new Dimension(250, 200));
        shopTitle.setFont(new Font(shopTitle.getName(), Font.BOLD, 20));
        shopTitle.setForeground(Color.WHITE);
        
        add(shopTitle);
        add(shopContainer);
        
        shopActions.add(backButton);
        shopActions.add(buyButton);
        add(shopActions);
    }

    //Setting the items to be displayed in the shop (JList)
    private void SetupShopDisplay(Item[] items)
    {
        ArrayList<String> shopItems = new ArrayList<String>();
        for(Item i : items)
        {
            shopItems.add(i.GetName() + " Cost: " + i.GetCost() + " | " + i.GetDescription());
        }
        shopInventory.setListData(shopItems.toArray());
    }  
    
    //Setting controller for all listenables in this iew
    public void SetController(ActionListener cntrl, ListSelectionListener list, ComponentListener cL)
    {
        shopInventory.addListSelectionListener(list);
        backButton.addActionListener(cntrl);
        buyButton.addActionListener(cntrl);
        this.addComponentListener(cL);
    }  
    
    @Override
    //Simply set the display for all items of the shop
    public void update(Observable o, Object obj) 
    {
        SetupShopDisplay(ShopInventory.GetItems());
    }
}
