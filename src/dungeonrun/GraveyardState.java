/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dungeonrun;

/**
 *
 * @author Liam
 */
public class GraveyardState extends State{

    public void BackToMenu()
    {
        setChanged();
        notifyObservers("Back");
    }
    
    public void UpdateSelf()
    {
        setChanged();
        notifyObservers(null);
    }
    
    @Override
    public State RunState() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
