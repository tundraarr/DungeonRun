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

     private JPanel bgPanel = new JPanel();
     private JLabel title = new JLabel("DUNGEON RUN");
     private JButton newGameBtn = new JButton("New Game");
     private JButton loadGameBtn = new JButton("Load Game");
     private JButton graveyardBtn = new JButton("Graveyard");
     private JButton exitBtn = new JButton("Exit");
     
     public MainMenuView()
     {
         this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
         //bgPanel.setLocation(500, 100);
         //bgPanel.setSize(350, 150);
         
         title.setAlignmentX(CENTER_ALIGNMENT);
         title.setFont(new Font(title.getName(), Font.PLAIN, 28));
         title.setPreferredSize(new Dimension(150, 45));
         title.setBorder(BorderFactory.createEmptyBorder(25, 10, 10, 10));
         
         newGameBtn.setAlignmentX(CENTER_ALIGNMENT);
         newGameBtn.setPreferredSize(new Dimension(75, 50));
         newGameBtn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    
         //add(bgPanel);
         add(title);
         add(newGameBtn);
         add(loadGameBtn);
         add(graveyardBtn);
         add(exitBtn);
     }
     
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
