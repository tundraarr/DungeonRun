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
//This state is to signal the main that the game is ending
public class EndState extends State{

    public EndState()
    {
        theState = States.END;
    }
    //Dummy run state
    @Override
    public State RunState() {
        return null;
    }
    
}
