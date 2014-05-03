package si.flyerman.exceptions;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class ContainsIllegalCharsException extends Exception {
    public ContainsIllegalCharsException(String exc){
        super(exc);
    }
    
    public ContainsIllegalCharsException(String exc, String err){
        super(exc+err);
    }
}
