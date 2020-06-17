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
public class GraveyardView extends JPanel implements Observer{

    private JLabel graveyardTitle = new JLabel("GRAVEYARD");
    private JList graveList = new JList();
    private JScrollPane graveContainer = new JScrollPane(graveList);
    private JButton backButton = new JButton("Back");
    
    public GraveyardView()
    {
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        graveyardTitle.setPreferredSize(new Dimension(650, 100));
        graveyardTitle.setAlignmentX(CENTER_ALIGNMENT);
        graveyardTitle.setFont(new Font(graveyardTitle.getName(), Font.BOLD, 28));
        graveyardTitle.setForeground(Color.magenta);
        
        graveList.setFont(new Font("Serif", Font.PLAIN, 22));
        graveContainer.setAlignmentX(CENTER_ALIGNMENT);
        
        backButton.setAlignmentX(CENTER_ALIGNMENT);
        backButton.setMargin(new Insets(5, 5, 5, 5));
        
        add(graveyardTitle);
        add(graveContainer);
        add(backButton);
    }
    
    private void UpdateGraveyard()
    {
        graveList.setListData(GraveSystem.GetGraveyard().toArray());
    }
    
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
            MainContainer.ChangeView("MainMenuView");
        }
        else
        {
           UpdateGraveyard(); 
        }
    }
    
}
