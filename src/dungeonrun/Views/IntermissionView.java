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
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

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
    private JLabel inventTitle = new JLabel("INVENTORY");
    private JList inventory = new JList();
    private JScrollPane inventoryContainer = new JScrollPane(inventory);
    private JButton useButton = new JButton("Use");
    
    //Panels to go inside interPanel
    private JPanel actionsPanel = new JPanel(); //Where the buttons to do stuff are
    private JPanel infoPanel = new JPanel();
    private JLabel infoText = new JLabel("The dungeon awaits");
    private JButton shopButton = new JButton("Shop");
    private JButton proceedButton = new JButton("Proceed");
    private JButton quitButton = new JButton("Save and Quit");
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
        topBanner.setPreferredSize(new Dimension(650, 35));
        topBanner.setForeground(Color.MAGENTA);
        topBanner.setFont(new Font(topBanner.getName(), Font.PLAIN, 20));
    }
    
    private void SetupStatsPanel()
    {
        UpdateStatsPanel();       
        
        statsPanel.setPreferredSize(new Dimension(190, 450));
        statsPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        statsPanel.add(name);
        statsPanel.add(level);
        statsPanel.add(gold);
        statsPanel.add(hp);
        statsPanel.add(mp);
        statsPanel.add(atk);
        statsPanel.add(magicAtk);
        statsPanel.add(def);
        statsPanel.add(luck);
        
        Font font = new Font("Courier",Font.BOLD, 16);
        for ( Component child : statsPanel.getComponents() )
        {
            JLabel label = (JLabel)child;
            label.setFont(font);
            label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 0));
        }
    }
    
    private void SetupInventoryPanel()
    {
        UpdateInventoryPanel();       
        
        inventTitle.setAlignmentX(CENTER_ALIGNMENT);
        inventTitle.setFont(new Font(inventTitle.getName(), Font.PLAIN, 18));
        inventTitle.setPreferredSize(new Dimension(190, 30));
        
        useButton.setAlignmentX(CENTER_ALIGNMENT);
        useButton.setPreferredSize(new Dimension(190,25));
        useButton.setBorder(BorderFactory.createEmptyBorder(25, 83, 25, 83));
        
        inventoryPanel.setPreferredSize(new Dimension(190, 450));
        inventoryPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        inventoryPanel.add(inventTitle);
        inventoryPanel.add(inventoryContainer);
        inventoryPanel.add(useButton);
    }
    
    private void SetupInterPanel()
    {
        interPanel.setBorder(BorderFactory.createLineBorder(Color.gray));
        interPanel.setPreferredSize(new Dimension(250, 450));
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.PAGE_AXIS));
        
        infoText.setAlignmentX(CENTER_ALIGNMENT);
        infoText.setFont(new Font(infoText.getName(), Font.PLAIN, 20));
        infoText.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        infoPanel.setPreferredSize(new Dimension(250, 250));
        
        shopButton.setAlignmentX(CENTER_ALIGNMENT);
        proceedButton.setAlignmentX(CENTER_ALIGNMENT);
        quitButton.setAlignmentX(CENTER_ALIGNMENT);
        
        shopButton.setMargin(new Insets(9, 115, 9, 115));
        proceedButton.setMargin(new Insets(9, 100, 9, 100));
        quitButton.setMargin(new Insets(9, 85, 9, 85));
        
        infoPanel.add(infoText);
        actionsPanel.add(infoPanel);
        actionsPanel.add(shopButton);
        actionsPanel.add(proceedButton);
        actionsPanel.add(quitButton);
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
    
    public void SetController(ActionListener aCntrl, ListSelectionListener lCntrl)
    {
        shopButton.addActionListener(aCntrl);
        proceedButton.addActionListener(aCntrl);
        quitButton.addActionListener(aCntrl);
        inventory.addListSelectionListener(lCntrl);
        useButton.addActionListener(aCntrl);
        
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        System.out.println("It is updating");
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
