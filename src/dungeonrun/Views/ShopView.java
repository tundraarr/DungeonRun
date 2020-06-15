/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.Items.*;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Liam
 */
public class ShopView extends JPanel implements Observer{
    
    JLabel shopTitle = new JLabel("SHOP");
    JList shopInventory = new JList();
    JScrollPane shopContainer = new JScrollPane(shopInventory);
    JPanel shopActions = new JPanel();
    JButton backButton = new JButton("Back");
    JButton buyButton = new JButton("Buy");
    
    public ShopView()
    {
        
        shopActions.setLayout(new FlowLayout());
        
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
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
