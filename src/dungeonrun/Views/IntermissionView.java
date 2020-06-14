/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.Items.*;
import dungeonrun.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Liam
 */
public class IntermissionView extends JPanel implements Observer{

    private JLabel topBanner = new JLabel("DUNGEON RUN INTERMISSION", SwingConstants.CENTER);
    private JPanel statsPanel = new JPanel();
    private JPanel inventoryPanel = new JPanel();
    private JPanel interPanel = new JPanel(); //Card layout
       
    //Panels to go inside statsPanel
    private JLabel name = new JLabel();
    private JLabel level = new JLabel();
    private JLabel gold = new JLabel();
    private JLabel hp = new JLabel();
    private JLabel mp = new JLabel();
    private JLabel atk = new JLabel();
    private JLabel magicAtk = new JLabel();
    private JLabel def = new JLabel();
    private JLabel luck = new JLabel();
    
    //Panels to go inside inventoryPanel
    private JList inventory = new JList();
    private JScrollPane inventoryContainer = new JScrollPane(inventory);
    private JButton useButton = new JButton("Use");
    
    //Panels to go inside interPanel
    private JPanel actionsPanel = new JPanel(); //Where the buttons to do stuff are
    //Make a new class for shop but put it in here
    private JPanel shopPanel = new JPanel(); //Show shop in interPanel and interact with it - add extra observer for this to interm view
 
    
    public IntermissionView()
    {
        this.setLayout(new BorderLayout());
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.PAGE_AXIS));
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.PAGE_AXIS));
        
        SetupTopBanner();
        SetupStatsPanel();
        SetupInventoryPanel();
        SetupInterPanel();
        
        add(topBanner, BorderLayout.NORTH);
        add(statsPanel, BorderLayout.WEST);
        add(inventoryPanel, BorderLayout.EAST);
        add(interPanel, BorderLayout.CENTER);
    }
    
    private void SetupTopBanner()
    {
        topBanner.setPreferredSize(new Dimension(650, 25));
        topBanner.setForeground(Color.MAGENTA);
        topBanner.setFont(new Font(topBanner.getName(), Font.PLAIN, 16));
//        topBanner.setHorizontalTextPosition(CENTER);
    }
    
    private void SetupStatsPanel()
    {
        UpdateStatsPanel();
        statsPanel.add(name);
        statsPanel.add(level);
        statsPanel.add(gold);
        statsPanel.add(hp);
        statsPanel.add(mp);
        statsPanel.add(atk);
        statsPanel.add(magicAtk);
        statsPanel.add(def);
        statsPanel.add(luck);
    }
    
    private void SetupInventoryPanel()
    {
        UpdateInventoryPanel();
        inventoryPanel.add(inventoryContainer);
        inventoryPanel.add(useButton);
    }
    
    private void SetupInterPanel()
    {
        interPanel.add(actionsPanel);
    }
    
    private void UpdateStatsPanel()
    {
        name.setText("Name: " + Player.GetName());
        level.setText("Level: " + Player.GetLevel());
        gold.setText("Gold: " + Player.GetGold());
        hp.setText("HP: " + Player.GetCurrentHp() + " / " + Player.GetMaxHp());
        mp.setText("MP: " + Player.GetCurrentMp() + " / " + Player.GetMaxMp());
        atk.setText("ATK: " + Player.GetAtk());
        magicAtk.setText("MAGIC ATK: " + Player.GetMagicAtk());
        def.setText("DEF: " + Player.GetDef());
        luck.setText("LUCK: " + Player.GetLuck());
    }
    
    private void UpdateInventoryPanel()
    {
        ArrayList<String> invItems = new ArrayList<String>();
        for(Map.Entry<Item, Integer> entry : Player.GetInventory().entrySet())
        {
            invItems.add(entry.getKey().name + " | " + entry.getValue());
        }
        
        inventory.setListData(invItems.toArray());
    }   
    
    @Override
    public void update(Observable o, Object obj) 
    {
        if(obj != null)
        {
            
        }
        else
        {
           UpdateStatsPanel();
           UpdateInventoryPanel(); 
        }
    }
    
}
