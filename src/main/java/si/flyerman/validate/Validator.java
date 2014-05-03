package si.flyerman.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.flyerman.exceptions.ContainsIllegalCharsException;
import si.flyerman.exceptions.ContainsNumbersException;
import si.flyerman.exceptions.EmptyStringException;
import si.flyerman.exceptions.FlyingNullException;
import si.flyerman.interfaces.AdvertManager;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class Validator {
    static final Logger log = LoggerFactory.getLogger(AdvertManager.class);    
    
    public static boolean isValidStringNoNumbers(String string) throws Exception {
        log.info("isValidStringNoNumbers called with parameter {}", string);
        boolean valid = false;
        
        if(string == null){
            log.info("String validation failed for string {} - the string is null", string);
            throw new FlyingNullException("Not a valid string");
        } else if(string.equals("")){
            log.info("String validation failed for string {} - the string is empty", string);
            throw new EmptyStringException("Empty string");
        } else if(string.matches(".*\\\\d.*")){
            log.info("String validation failed for string {} - string contains numbers", string);
            throw new ContainsNumbersException("String contains numbers");
        } else {
            valid = true;
        }
        
        return valid;
    }
    
    public static boolean isValidString(String string) throws Exception {
        log.info("isValidString called with parameter {}", string);
        boolean valid = false;
        
        if(string == null){
            log.info("String validation failed for string {} - the string is null", string);
            throw new FlyingNullException("Not a valid string");
        } else if(string.equals("")){
            log.info("String validation failed for string {} - the string is empty", string);
            throw new EmptyStringException("Empty string");
        } else {
            char[] arr = string.toCharArray();
            for(char c : arr){
                if(!(Character.isLetter(c) || Character.isDigit(c))){
                    log.info("String validation failed for string {} - the string contans an illegal character", string);
                    throw new ContainsIllegalCharsException("Contains an illegal character: "+c);
                }
            }
            valid = true;
        }
        
        return valid;
    }
}
