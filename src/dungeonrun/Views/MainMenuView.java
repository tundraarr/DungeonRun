/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;
import dungeonrun.MainContainer;
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
public class MainMenuView extends JPanel implements Observer{

     private JLabel title = new JLabel("DUNGEON RUN");
     private JButton newGameBtn = new JButton("New Game");
     private JButton loadGameBtn = new JButton("Load Game");
     private JButton graveyardBtn = new JButton("Graveyard");
     private JButton exitBtn = new JButton("Exit");
     private JLabel noData = new JLabel();
     
     public MainMenuView()
     {
         this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
         
         title.setAlignmentX(CENTER_ALIGNMENT);
         title.setFont(new Font(title.getName(), Font.PLAIN, 36));
         title.setPreferredSize(new Dimension(150, 45));
         title.setBorder(BorderFactory.createEmptyBorder(25, 10, 25, 10));
         
         newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
         newGameBtn.setMargin(new Insets(10, 10, 10, 10));
    
         loadGameBtn.setAlignmentX(CENTER_ALIGNMENT);
         loadGameBtn.setMargin(new Insets(10, 10, 10, 10));
         
         graveyardBtn.setAlignmentX(CENTER_ALIGNMENT);
         graveyardBtn.setMargin(new Insets(10, 10, 10, 10));
         
         exitBtn.setAlignmentX(CENTER_ALIGNMENT);
         exitBtn.setMargin(new Insets(10, 10, 10, 10));
         
         noData.setAlignmentX(CENTER_ALIGNMENT);
         noData.setPreferredSize(new Dimension(50, 30));
         
         
         //add(bgPanel);
         add(title);
         add(newGameBtn);
         add(loadGameBtn);
         add(graveyardBtn);
         add(exitBtn);
         add(noData);
     }
     
    public void SetController(ActionListener cntrl, ComponentListener cL) 
    {
        newGameBtn.addActionListener(cntrl);
        loadGameBtn.addActionListener(cntrl);
        graveyardBtn.addActionListener(cntrl);
        exitBtn.addActionListener(cntrl);
        this.addComponentListener(cL);
    }
 
    public void SetNoDataTxt()
    {
        noData.setText("No Data Found!");
    }
    
    private void ClearNoDataTxt()
    {
        noData.setText("");
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        if(obj != null)
        {
            boolean dataExists = (boolean)obj;
            if(!dataExists)
            {
                SetNoDataTxt();
            }
            else
            {
                MainContainer.ChangeView("IntermissionView");
            }
        }
        else
        {
            ClearNoDataTxt();
        }
    }
    
}
