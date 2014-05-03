/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package si.flyerman.exceptions;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class FlyingNullException extends Exception {
    public FlyingNullException(String exc){
        super(exc);
    }
    
    public FlyingNullException(String error, String exc){
        super(error+exc);
    }
}
