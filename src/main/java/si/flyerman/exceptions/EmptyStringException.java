package si.flyerman.exceptions;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class EmptyStringException extends Exception {
    public EmptyStringException(String exc){
        super(exc);
    }
    
    public EmptyStringException(String err, String exc){
        super(err+exc);
    }
}
