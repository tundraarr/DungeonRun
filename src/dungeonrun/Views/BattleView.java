/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.Items.Item;
import dungeonrun.Player;
import dungeonrun.Spells.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Liam
 */
public class BattleView extends JPanel implements Observer{

    //Main panels for Battle View
    private JPanel visualPanel = new JPanel();
    private JPanel actionPanel = new JPanel();
    
    //Components to go inside visual panel
    //And image component
    private JLabel enemyName = new JLabel("Goblin");
    private ImageIcon enemyIcon;
    private JLabel enemyImage;
    private JLabel enemyHpText = new JLabel("50 / 50");
    private JLabel battleText = new JLabel("Something of text should go here");
    private JLabel playerHpText = new JLabel("HP: 100 / 100");
    private JLabel playerMpText = new JLabel("MP: 50 / 50");
    
    //Components to go inside actions panel
    private JPanel buttonsPanel = new JPanel();
    private JPanel spellsPanel = new JPanel();
    private JPanel inventoryPanel = new JPanel();
    private JPanel blankPanel = new JPanel(); //Used to stop player's from interacting during combat
    
    //Components to go inside buttons panel
    private JButton attackButton = new JButton("Attack");
    private JButton spellsButton = new JButton("Spells");
    private JButton itemsButton = new JButton("Items");
    
    //Components to go inside spells panel
    private JPanel spButtons = new JPanel();
    private JButton spBackButton = new JButton("Back");
    private JButton spCastButton = new JButton("Cast");
    private JList spellsList = new JList();
    private JScrollPane spellsContainer = new JScrollPane(spellsList);
    
    //Components to go inside inventory panel
    private JPanel invButtons = new JPanel();
    private JButton invBackButton = new JButton("Back");
    private JButton invUseButton = new JButton("Use");
    private JList itemsList = new JList();
    private JScrollPane itemsContainer = new JScrollPane(itemsList);
    
    public BattleView()
    {
        this.setLayout(new BorderLayout());
        SetupVisualPanel();
        SetupButtonsPanel();
        SetupSpellsPanel();
        SetupInventoryPanel();
        SetupActionPanel();
        add(visualPanel, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);
    }
    
    private ImageIcon createImageIcon(String path, String description) 
    {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) 
        {
            return new ImageIcon(imgURL, description);
        } 
        else 
        {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private void SetEnemyImage()
    {
        
    }
    
    private void SetupVisualPanel()
    {
        visualPanel.setLayout(new BoxLayout(visualPanel, BoxLayout.PAGE_AXIS));
        visualPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        enemyName.setBorder(BorderFactory.createEmptyBorder(75, 0, 0, 0));
        enemyName.setFont(new Font(enemyName.getName(), Font.PLAIN, 20));
        enemyName.setForeground(Color.gray);
        enemyName.setAlignmentX(CENTER_ALIGNMENT);
        
        enemyIcon = createImageIcon("../Images/Goblin.png", "an image");
        enemyImage = new JLabel(enemyIcon);
        enemyImage.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        enemyImage.setAlignmentX(CENTER_ALIGNMENT);
        
        enemyHpText.setFont(new Font(enemyHpText.getName(), Font.PLAIN, 18));
        enemyHpText.setForeground(Color.red);
        enemyHpText.setAlignmentX(CENTER_ALIGNMENT);
        
        battleText.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        battleText.setFont(new Font(battleText.getName(), Font.ROMAN_BASELINE, 20));
        battleText.setAlignmentX(CENTER_ALIGNMENT);
        
        playerHpText.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        playerHpText.setFont(new Font(playerHpText.getName(), Font.PLAIN, 18));
        playerHpText.setForeground(Color.green);
        playerHpText.setAlignmentX(CENTER_ALIGNMENT);
        
        playerMpText.setFont(new Font(playerHpText.getName(), Font.PLAIN, 18));
        playerMpText.setForeground(Color.blue);
        playerMpText.setAlignmentX(CENTER_ALIGNMENT);
        
        visualPanel.add(enemyName);
        visualPanel.add(enemyImage);
        visualPanel.add(enemyHpText);
        visualPanel.add(battleText);
        visualPanel.add(playerHpText);
        visualPanel.add(playerMpText);
    }
    
    private void SetupActionPanel()
    {
        actionPanel.setLayout(new CardLayout());
        actionPanel.setPreferredSize(new Dimension(650, 100));
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        actionPanel.add(buttonsPanel, "ButtonsPanel");
        actionPanel.add(spellsPanel, "SpellsPanel");
        actionPanel.add(inventoryPanel, "InventoryPanel");
        actionPanel.add(blankPanel, "BlankPanel");
    }
    
    private void SetupButtonsPanel()
    {
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 30));

        attackButton.setMargin(new Insets(10, 20, 10, 20));
        spellsButton.setMargin(new Insets(10, 20, 10, 20));
        itemsButton.setMargin(new Insets(10, 20, 10, 20));
        
        buttonsPanel.add(attackButton);
        buttonsPanel.add(spellsButton);
        buttonsPanel.add(itemsButton);
    }
    
    private void SetupSpellsPanel()
    {
        spellsPanel.setLayout(new BoxLayout(spellsPanel, BoxLayout.X_AXIS));
        
        spButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        spButtons.setPreferredSize(new Dimension(20, 100));
        spButtons.add(spBackButton);
        spButtons.add(spCastButton);
        
        spellsContainer.setPreferredSize(new Dimension(400, 100));
        
        spellsPanel.add(spButtons);
        spellsPanel.add(spellsContainer);
    }
    
    private void SetupInventoryPanel()
    {
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.X_AXIS));
        
        invButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        invButtons.setPreferredSize(new Dimension(20, 100));
        invButtons.add(invBackButton);
        invButtons.add(invUseButton);
        
        itemsContainer.setPreferredSize(new Dimension(400, 100));
        
        inventoryPanel.add(invButtons);
        inventoryPanel.add(itemsContainer);
    }
    
    private void SetupBlankPanel()
    {
        blankPanel.setPreferredSize(new Dimension(650, 100));
    }
    
    private void UpdateEnemy()
    {
        
    }
    
    private void UpdatePlayer()
    {
        playerHpText.setText("HP: " + Player.GetCurrentHp() + " / " + Player.GetMaxHp());
        playerMpText.setText("MP: " + Player.GetCurrentMp() + " / " + Player.GetMaxMp());
    }
    
    private void UpdateSpellsPanel()
    {
        ArrayList<String> spells = new ArrayList<String>();
        for(Spell sp : Player.GetSpells())
        {
            spells.add(sp.name + " | " + sp.manaCost + " | " + sp.description);
        }
        
        spellsList.setListData(spells.toArray());
    }
    
    private void UpdateInventoryPanel()
    {
        ArrayList<String> invItems = new ArrayList<String>();
        for(Map.Entry<Item, Integer> entry : Player.GetInventory().entrySet())
        {
            invItems.add(entry.getKey().name + " | " + entry.getValue() + " | " +entry.getKey().GetDescription());
        }
        
        itemsList.setListData(invItems.toArray());
    }
    
    private void ChangeActionPanel(String panel)
    {
        CardLayout cl = (CardLayout)(actionPanel.getLayout());
        cl.show(actionPanel, panel);  
    }
    
    public JList GetSpellsJList()
    {
        return this.spellsList;
    }
    
    public JList GetItemsJList()
    {
        return this.itemsList;
    }
    
    public void SetController(ActionListener actL, ListSelectionListener lsL)
    {
        attackButton.addActionListener(actL);
        spellsButton.addActionListener(actL);
        itemsButton.addActionListener(actL);
        spBackButton.addActionListener(actL);
        invBackButton.addActionListener(actL);
        spCastButton.addActionListener(actL);
        invUseButton.addActionListener(actL);
        spellsList.addListSelectionListener(lsL);
        itemsList.addListSelectionListener(lsL);
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        if(obj != null)
        {
            String str = (String)obj;
            ChangeActionPanel(str);
        }
        else
        {
            UpdateEnemy();
            UpdatePlayer();
            UpdateSpellsPanel();
            UpdateInventoryPanel();
        }
    }
    
}
