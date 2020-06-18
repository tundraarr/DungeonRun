/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun.Views;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author Liam
 */

//The view for when the player is starting a new game
public class NewGameView extends JPanel implements Observer{

    //Components for the top panel of this view
    private JPanel topPanel = new JPanel();
    private JLabel newGameLabel = new JLabel("| Starting A New Game |");
    private JLabel nameInputLabel = new JLabel("Input the name of your character: ");
    private JLabel errorLabel = new JLabel();
    
    //Components for the middle panel of this view
    private JPanel midPanel = new JPanel();
    private JTextField nameInputField = new JTextField();
    private JButton confirmButton = new JButton("Confirm");
    
    //Bottom panel for error label
    private JPanel botPanel = new JPanel();
    
    public NewGameView()
    {
        //Set overall page layout
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        
        //Top panel
        topPanel.setPreferredSize(new Dimension(650, 200));
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.PAGE_AXIS));
        
        newGameLabel.setAlignmentX(CENTER_ALIGNMENT);
        newGameLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
        newGameLabel.setFont(new Font(newGameLabel.getName(), Font.PLAIN, 28));
        
        nameInputLabel.setAlignmentX(CENTER_ALIGNMENT);
        nameInputLabel.setFont(new Font(nameInputLabel.getName(), Font.PLAIN, 18));
        nameInputLabel.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));
        
        
        //Mid Panel
        midPanel.setPreferredSize(new Dimension(650, 15));
        midPanel.setLayout(new BoxLayout(midPanel, BoxLayout.PAGE_AXIS));
        
        nameInputField.setAlignmentX(CENTER_ALIGNMENT);
        nameInputField.setPreferredSize(new Dimension(150, 10));
        
        confirmButton.setAlignmentX(CENTER_ALIGNMENT);
        
        //Bottom panel
        botPanel.setPreferredSize(new Dimension(650, 155));
        
        errorLabel.setAlignmentX(RIGHT_ALIGNMENT);
        errorLabel.setForeground(Color.red);
        
        //Adding of the components into the main panel
        topPanel.add(newGameLabel);
        topPanel.add(nameInputLabel);
        add(topPanel);
        
        midPanel.add(nameInputField);
        midPanel.add(confirmButton);
        add(midPanel);
        
        botPanel.add(errorLabel);
        add(botPanel);
    }
    
    //Get the user's input for their character's name from the text field
    public String GetUserInput()
    {
        return nameInputField.getText();
    }
    
    //If their name input was invalid, display this message
    private void SetInvalidNameTxt()
    {
        errorLabel.setText("Invalid name! Name cannot start with a space or be null.");
    }
    
    //Clear the invalid name text
    private void ClearInvalidText()
    {
        errorLabel.setText("");
    }
    
    //Set the controllers for all listenables on this view
    public void SetController(ActionListener cntrl, ComponentListener cL)
    {
        confirmButton.addActionListener(cntrl);
        this.addComponentListener(cL);
    }
    
    @Override
    public void update(Observable o, Object obj) 
    {
        if(obj != null)
        {
            //Update the view depending on whether their name input was valid or not
            boolean validName = (boolean)obj;
            if(!validName)
            {
                SetInvalidNameTxt();
            }
        }
        else
        {
            //Cleare the invalid text when this view is revisited
            ClearInvalidText();
        }
    }
    
}
