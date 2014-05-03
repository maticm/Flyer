package si.flyerman.exceptions;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class ContainsNumbersException extends Exception {
    public ContainsNumbersException(String exc){
        super(exc);
    }
    
    public ContainsNumbersException(String msg, String exc){
        super(msg+exc);
    }
}
