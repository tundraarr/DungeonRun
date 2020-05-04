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
public class NullInputException extends Exception{
    
    public NullInputException()
    {
        super("Input must not be null or start with space");
    }
    
}
