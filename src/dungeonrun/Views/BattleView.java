/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;

import dungeonrun.CombatInfo;
import dungeonrun.Items.Item;
import dungeonrun.Player;
import dungeonrun.Spells.*;
import dungeonrun.Enemies.*;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
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
    private JLabel enemyName = new JLabel();
    private ImageIcon enemyIcon;
    private JLabel enemyImage;
    private JLabel enemyHpText = new JLabel();
    private JLabel battleText = new JLabel("Something of text should go here");
    private JLabel playerHpText = new JLabel();
    private JLabel playerMpText = new JLabel();
    
    //Components to go inside actions panel
    private JPanel buttonsPanel = new JPanel();
    private JPanel spellsPanel = new JPanel();
    private JPanel inventoryPanel = new JPanel();
    
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
    
    private void SetEnemyImage(Enemy enemy)
    {
        enemyIcon = createImageIcon("../Images/" +enemy.name+ ".png", "Enemy Image");
        enemyImage.setIcon(enemyIcon);
        System.out.println(enemyIcon.getImage());
    }
    
    private void SetupVisualPanel()
    {
        //The top half panel for the Battle View
        visualPanel.setBackground(Color.GRAY);
        visualPanel.setLayout(new BoxLayout(visualPanel, BoxLayout.PAGE_AXIS));
        visualPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Name of Enemy
        enemyName.setBorder(BorderFactory.createEmptyBorder(45, 0, 0, 0));
        enemyName.setFont(new Font(enemyName.getName(), Font.PLAIN, 20));
        enemyName.setForeground(Color.gray);
        enemyName.setAlignmentX(CENTER_ALIGNMENT);
        
        //Image of Enemy
        enemyImage = new JLabel();
        enemyImage.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
        enemyImage.setAlignmentX(CENTER_ALIGNMENT);
        
        //Enemy's HP
        enemyHpText.setFont(new Font(enemyHpText.getName(), Font.PLAIN, 22));
        enemyHpText.setForeground(Color.RED);
        enemyHpText.setAlignmentX(CENTER_ALIGNMENT);
        
        //Text during the battle
        battleText.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        battleText.setFont(new Font(battleText.getName(), Font.BOLD, 16));
        battleText.setAlignmentX(CENTER_ALIGNMENT);
        
        //Player's HP
        playerHpText.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
        playerHpText.setFont(new Font(playerHpText.getName(), Font.PLAIN, 18));
        playerHpText.setForeground(Color.green);
        playerHpText.setAlignmentX(CENTER_ALIGNMENT);
        
        //Player's MP
        playerMpText.setFont(new Font(playerHpText.getName(), Font.PLAIN, 18));
        playerMpText.setForeground(Color.blue);
        playerMpText.setAlignmentX(CENTER_ALIGNMENT);
        
        //Add components to panel
        visualPanel.add(enemyName);
        visualPanel.add(enemyImage);
        visualPanel.add(enemyHpText);
        visualPanel.add(battleText);
        visualPanel.add(playerHpText);
        visualPanel.add(playerMpText);
    }
    
    //Setup components that are part of the Action Panel
    private void SetupActionPanel()
    {
        actionPanel.setLayout(new CardLayout());
        actionPanel.setPreferredSize(new Dimension(650, 100));
        actionPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        actionPanel.add(buttonsPanel, "ButtonsPanel");
        actionPanel.add(spellsPanel, "SpellsPanel");
        actionPanel.add(inventoryPanel, "InventoryPanel");
    }
    
    //Setup components that are part of the Buttons Panel
    private void SetupButtonsPanel()
    {
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 90, 30));
        buttonsPanel.setBackground(Color.ORANGE);

        attackButton.setMargin(new Insets(10, 20, 10, 20));
        spellsButton.setMargin(new Insets(10, 20, 10, 20));
        itemsButton.setMargin(new Insets(10, 20, 10, 20));
        
        buttonsPanel.add(attackButton);
        buttonsPanel.add(spellsButton);
        buttonsPanel.add(itemsButton);
    }
    
    //Setup components that are part of the Spells Panel
    private void SetupSpellsPanel()
    {
        spellsPanel.setLayout(new BoxLayout(spellsPanel, BoxLayout.X_AXIS));
        
        spButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));
        spButtons.setPreferredSize(new Dimension(20, 100));
        spButtons.setBackground(Color.CYAN);
        spButtons.add(spBackButton);
        spButtons.add(spCastButton);
        
        spellsContainer.setPreferredSize(new Dimension(400, 100));
        
        spellsPanel.add(spButtons);
        spellsPanel.add(spellsContainer);
    }
    
    //Setup components that are part of the Inventory Panel
    private void SetupInventoryPanel()
    {
        inventoryPanel.setLayout(new BoxLayout(inventoryPanel, BoxLayout.X_AXIS));
        
        invButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 15));
        invButtons.setPreferredSize(new Dimension(20, 100));
        invButtons.setBackground(Color.YELLOW);
        invButtons.add(invBackButton);
        invButtons.add(invUseButton);
        
        itemsContainer.setPreferredSize(new Dimension(400, 100));
        
        inventoryPanel.add(invButtons);
        inventoryPanel.add(itemsContainer);
    }  
    
    //Update visual information regarding the enemy
    private void UpdateEnemy(Enemy enemy)
    {
        SetEnemyImage(enemy);
        enemyName.setText(enemy.name);
        enemyHpText.setText("HP: " + enemy.hp);
    }
    
    //Update visual information regarding the player
    private void UpdatePlayer()
    {
        playerHpText.setText("HP: " + Player.GetCurrentHp() + " / " + Player.GetMaxHp());
        playerMpText.setText("MP: " + Player.GetCurrentMp() + " / " + Player.GetMaxMp());
    }
    
    //Update the battle text information
    private void UpdateBattleText(String text)
    {
        battleText.setText(text);
    }
    
    //Update the spells JList
    private void UpdateSpellsPanel()
    {
        ArrayList<String> spells = new ArrayList<String>();
        for(Spell sp : Player.GetSpells())
        {
            spells.add(sp.name + " | " + sp.manaCost + " | " + sp.description);
        }
        
        spellsList.setListData(spells.toArray());
    }
    
    //Upate the Inventory JList
    private void UpdateInventoryPanel()
    {
        ArrayList<String> invItems = new ArrayList<String>();
        for(Map.Entry<Item, Integer> entry : Player.GetInventory().entrySet())
        {
            invItems.add(entry.getKey().name + " | " + entry.getValue() + " | " +entry.getKey().GetDescription());
        }
        
        itemsList.setListData(invItems.toArray());
    }
    
    //Show a popup, used at the end of the battle
    private void ShowBattleEndPopup(String msg)
    {
        JOptionPane.showMessageDialog(this, msg);
    }
    
    //Change the panel to be displayed inside of the Action Panel
    private void ChangeActionPanel(String panel)
    {
        CardLayout cl = (CardLayout)(actionPanel.getLayout());
        cl.show(actionPanel, panel);  
    }
    
    //Get a reference for the Spells JList
    public JList GetSpellsJList()
    {
        return this.spellsList;
    }
    
    //Get a reference for the Items JList
    public JList GetItemsJList()
    {
        return this.itemsList;
    }
    
    //Set the controller for this Panel
    public void SetController(ActionListener actL, ListSelectionListener lsL, ComponentListener cL)
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
        this.addComponentListener(cL);
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        //If the object is not a CombatInfo obj, then it is a String
        //to change the action panel to a different panel
        if(!(obj instanceof CombatInfo))
        {
            String str = (String)obj;
            ChangeActionPanel(str);

        }
        //If the object is of CombatInfo then it means that the View needs to be updated
        else if(obj instanceof CombatInfo)
        {
            CombatInfo info = (CombatInfo)obj;
            //Display victory popup
            if(info.getTextInfo().compareTo("Victory") == 0)
            {
                ShowBattleEndPopup("Level Up! You have become stronger. \nYou have received " + info.GetEnemyInfo().gold + " gold");
            }
            //Display defeat popup
            else if(info.getTextInfo().compareTo("Defeat") == 0)
            {
                ShowBattleEndPopup("Defeat... But you may try again");
            }
            //General visual update to the view
            else
            {
                UpdateEnemy(info.GetEnemyInfo());
                UpdateBattleText(info.getTextInfo());
            }
        }
        UpdatePlayer();
        UpdateSpellsPanel();
        UpdateInventoryPanel();
    }
    
}
