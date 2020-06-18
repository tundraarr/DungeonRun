/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;
import dungeonrun.GraveSystem;
import dungeonrun.MainContainer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Liam
 */

//The view for when the player accesses the Graveyard, shows a list of all deceased characters
public class GraveyardView extends JPanel implements Observer{

    //Components to go inside the graveyard view
    private JPanel titleContainer = new JPanel();
    private JLabel graveyardTitle = new JLabel("GRAVEYARD");
    private JList graveList = new JList();
    private JScrollPane graveContainer = new JScrollPane(graveList);
    private JPanel botContainer = new JPanel();
    private JButton backButton = new JButton("Back");
    
    public GraveyardView()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        //Title components
        //Title container panel
        titleContainer.setPreferredSize(new Dimension(650, 25));
        titleContainer.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        titleContainer.setBackground(Color.DARK_GRAY);
        
        //The graveyard title
        graveyardTitle.setAlignmentX(CENTER_ALIGNMENT);
        graveyardTitle.setFont(new Font(graveyardTitle.getName(), Font.BOLD, 32));
        graveyardTitle.setBorder(BorderFactory.createEmptyBorder(25, 0, 0, 0));
        graveyardTitle.setForeground(Color.WHITE);
        titleContainer.add(graveyardTitle);
        
        //The JList of all deceased characters
        graveList.setFont(new Font("Serif", Font.PLAIN, 22));
        graveContainer.setAlignmentX(CENTER_ALIGNMENT);
        
        //The bottom container panel for the back button
        botContainer.setPreferredSize(new Dimension(650, 25));
        botContainer.setBackground(Color.DARK_GRAY);
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setMargin(new Insets(5, 5, 5, 5));
        botContainer.add(backButton);
        
        add(titleContainer);
        add(graveContainer);
        add(botContainer);
    }
    
    //Update the entries in the JList of the graveyard
    private void UpdateGraveyard()
    {
        graveList.setListData(GraveSystem.GetGraveyard().toArray());
    }
    
    //Assign controllers to buttons
    public void SetController(ActionListener cntrl, ComponentListener cL)
    {
        backButton.addActionListener(cntrl);
        this.addComponentListener(cL);
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        if(obj != null)
        {
            //Return to the main menu
            MainContainer.ChangeView("MainMenuView");
        }
        else
        {
           //Update graveyard list
           UpdateGraveyard(); 
        }
    }
    
}
