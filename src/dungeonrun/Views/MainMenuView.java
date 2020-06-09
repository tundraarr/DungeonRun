/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionListener;
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
         title.setFont(new Font(title.getName(), Font.PLAIN, 28));
         title.setPreferredSize(new Dimension(150, 45));
         title.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));
         
         newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
         newGameBtn.setPreferredSize(new Dimension(75, 50));
         newGameBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
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
     
    public void SetController(ActionListener cntrl) 
    {
        newGameBtn.addActionListener(cntrl);
        loadGameBtn.addActionListener(cntrl);
        graveyardBtn.addActionListener(cntrl);
        exitBtn.addActionListener(cntrl);
    }
 
    public void SetNoDataTxt()
    {
        noData.setText("No Data Found!");
    }
    
    private void ClearNoDataTxt()
    {
        
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        //TODO: Remove this particular instance of reset/update, not needed in main menu
        //Reseting/Updating the panel when user revists it
        if(obj == null)
        {
            ClearNoDataTxt();
        }
        else
        {
            boolean dataExists = (boolean)obj;
            if(!dataExists)
            {
                SetNoDataTxt();
            }
        }
    }
    
}
