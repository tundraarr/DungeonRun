/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;
import dungeonrun.MainContainer;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;
/**
 *
 * @author Liam
 */

//The view of the main menu 
public class MainMenuView extends JPanel implements Observer{

    //Core panels of the main menu 
     private JPanel topPanel = new JPanel();
     private JPanel leftPanel = new JPanel();
     private JPanel centerPanel = new JPanel();
     private JPanel rightPanel = new JPanel();
     private JPanel botPanel = new JPanel();
     
     //The key components of the main menu
     private JLabel title = new JLabel("DUNGEON RUN");
     private JLabel subText = new JLabel("A game of luck and... mostly luck");
     private JButton newGameBtn = new JButton("New Game");
     private JButton loadGameBtn = new JButton("Load Game");
     private JButton graveyardBtn = new JButton("Graveyard");
     private JButton exitBtn = new JButton("Exit");
     private JLabel noData = new JLabel();
     
     public MainMenuView()
     {
         //Setup the view panel
         this.setLayout(new BorderLayout());
         this.setBackground(Color.GREEN);               
         
         //Setup the main panels within the view along with their components
         SetupTopPanel();
         SetupLeftPanel();
         SetupCenterPanel();
         SetupRightPanel();
         SetupBotPanel();
         
         add(topPanel, BorderLayout.NORTH);
         add(leftPanel, BorderLayout.WEST);
         add(centerPanel, BorderLayout.CENTER);
         add(rightPanel, BorderLayout.EAST);
         add(botPanel, BorderLayout.SOUTH);       
     }
    
     //For creation of an image icon
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
    
    //Setting up the title components
    private void SetupTopPanel()
    {
         topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
         topPanel.setPreferredSize(new Dimension(650, 100));
         topPanel.setBackground(Color.GRAY);
        
         title.setAlignmentX(CENTER_ALIGNMENT);
         title.setFont(new Font(title.getName(), Font.PLAIN, 32));
         title.setForeground(Color.ORANGE);
         title.setPreferredSize(new Dimension(150, 45));
         title.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
         
         subText.setAlignmentX(CENTER_ALIGNMENT);
         subText.setFont(new Font(subText.getName(), Font.PLAIN, 24));
         subText.setForeground(Color.WHITE);
         
         topPanel.add(title);
         topPanel.add(subText);
    }
    
    //Left panel for aesthetic design
    private void SetupLeftPanel()
    {
        leftPanel.setPreferredSize(new Dimension(100, 300));
        leftPanel.setBackground(Color.DARK_GRAY);
    }
    
    //Center panel where the buttons for the player to press are
    //Setup of their location and sizes
    private void SetupCenterPanel()
    {
         centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.PAGE_AXIS));
         centerPanel.setPreferredSize(new Dimension(450, 300));
         centerPanel.setBackground(Color.LIGHT_GRAY);
         centerPanel.setBorder(BorderFactory.createLineBorder(Color.PINK));
        
         JPanel topPadding = new JPanel();
         topPadding.setBackground(Color.LIGHT_GRAY);
         topPadding.setBorder(BorderFactory.createEmptyBorder(0 ,0 , 20, 0));
         
         JPanel botPadding = new JPanel();
         botPadding.setBackground(Color.LIGHT_GRAY);
         
         newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
         newGameBtn.setMargin(new Insets(10, 20, 10, 20));
    
         loadGameBtn.setAlignmentX(CENTER_ALIGNMENT);
         loadGameBtn.setMargin(new Insets(10, 18, 10, 18));
         
         graveyardBtn.setAlignmentX(CENTER_ALIGNMENT);
         graveyardBtn.setMargin(new Insets(10, 20, 10, 20));
         
         exitBtn.setAlignmentX(CENTER_ALIGNMENT);
         exitBtn.setMargin(new Insets(10, 38, 10, 38));
         
         noData.setAlignmentX(CENTER_ALIGNMENT);
         noData.setPreferredSize(new Dimension(50, 30));
         
         centerPanel.add(topPadding);
         centerPanel.add(newGameBtn);
         centerPanel.add(loadGameBtn);
         centerPanel.add(graveyardBtn);
         centerPanel.add(exitBtn);
         centerPanel.add(noData);
         centerPanel.add(botPadding);
    }
    
    //Left panel for aesthetic design
    private void SetupRightPanel()
    {
        rightPanel.setPreferredSize(new Dimension(100, 300));
        rightPanel.setBackground(Color.DARK_GRAY);
    }
    
    private void SetupBotPanel()
    {
        botPanel.setPreferredSize(new Dimension(650, 50));
        botPanel.setBackground(Color.DARK_GRAY);
    }
    
    //Setting the controller to all listenables in this view
    public void SetController(ActionListener cntrl, ComponentListener cL) 
    {
        newGameBtn.addActionListener(cntrl);
        loadGameBtn.addActionListener(cntrl);
        graveyardBtn.addActionListener(cntrl);
        exitBtn.addActionListener(cntrl);
        this.addComponentListener(cL);
    }
 
    //Display text that no data was found
    public void SetNoDataTxt()
    {
        noData.setText("No Data Found!");
    }
    
    //Clear the no data found text
    private void ClearNoDataTxt()
    {
        noData.setText("");
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        if(obj != null)
        {
            //Whether the player can load a save and continue where they left off
            boolean dataExists = (boolean)obj;
            if(!dataExists)
            {
                //Display if no data was found when trying to load a save
                SetNoDataTxt();
            }
            else
            {
                //There was save data, go to the intermisison view
                MainContainer.ChangeView("IntermissionView");
            }
        }
        else
        {
            //Clear the no data found text when this view is revisted
            ClearNoDataTxt();
        }
    }
    
}
