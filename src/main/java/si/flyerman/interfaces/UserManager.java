package si.flyerman.interfaces;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.flyerman.db.DbAccess;
import si.flyerman.exceptions.ContainsNumbersException;
import si.flyerman.exceptions.EmptyStringException;
import si.flyerman.exceptions.FlyingNullException;
import si.flyerman.users.BCrypt;
import si.flyerman.users.Users;
import si.flyerman.validate.Validator;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class UserManager {
    static final Logger log = LoggerFactory.getLogger(UserManager.class);
    
    /**
     * Validates existing username
     * @param username
     * @return boolean true or false
     */
    public boolean isValidUser(String username){
        log.info("isValidUser called with parameter {}", username);
        boolean valid = false;
        List<Users> li = new ArrayList<>();
        Session session = DbAccess.getSession();
       // Transaction tx = null;
        
        //Users user = new Users();
        
        try {
            log.info("Checking the input against database");
       //     tx = session.beginTransaction();
            log.debug("Querying the database");
            Query q = session.createQuery("FROM Users WHERE USERNAME=:username");
            q.setString("username", username);
            li = q.list();
            Users u = null;
            if(li != null && li.size()>0)
                u = li.get(0);
                        
            if(u != null){
                log.debug("Database query successful");
                log.info("User found in database");
                valid = true;
            }
      //      tx.commit();
        } catch (Exception e){
            log.error(e.getMessage());
        }
        log.info("Returning the value isValidUser with result {}", valid);
        return valid;
    }
    
    /**
     * Validates password for a user
     * @param username
     * @param password
     * @return boolean true or false
     */
    public boolean isValidPassword(String username, String password){
        log.info("isValidPassword called with parameter username {} and parameter password {}", username, password);
        boolean valid = false;
        
        Session session = DbAccess.getSession();
       
        List li = new ArrayList();
        
        try {
            log.info("Checking password against database");
           // tx = session.beginTransaction();
            Query q = session.createQuery("FROM Users WHERE username=? AND password=?");
            q.setString(0, username);
            q.setString(1, password);
            li = q.list();
            Users user = null;
            if(li != null && li.size()>0)
                user = (Users)li.get(0);
            if(user != null){
                log.info("Got usersname and password match");
                valid = true;
            }
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return valid;
    }
    
    /**
     * Adds a new user of any type
     * @param username
     * @param password
     * @param type
     * @param firstName
     * @param lastName
     * @return true or false
     * @throws Exception 
     */
    
    public boolean addUser(String username, String password, Integer type, String firstName, String lastName) throws Exception{
        log.info("addUser function called with parameters username {}, password {}, type {}, firstName {}, lastName {}", username,password);
        //log.info("addUser function called with parameters username {}, password {}, type {}, firstName {}, lastName {}", username,password,type,firstName,lastName);
        boolean success = false;
        
        Session session = DbAccess.getSession();
        Transaction tx = null;
        
        try {
            tx = session.beginTransaction();
            log.info("Validating parameters");
            if(Validator.isValidStringNoNumbers(firstName) && Validator.isValidStringNoNumbers(lastName) 
                    && Validator.isValidString(username) 
                    && (type.intValue() == 0 || type.intValue() == 1 || type.intValue() == 2)){
                
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt(15));
                
                Query q = session.createSQLQuery("INSERT INTO USERS(username, password, firstName, lastName, type) VALUES(:username, :password, :firstName, :lastName, :type)");
                q.setString("username", username);
                q.setString("password", hashed);
                q.setString("firstName", firstName);
                q.setString("lastName", lastName);
                q.setInteger("type", type);
                
                try {
                    int res = q.executeUpdate();
                    if(res > 0){
                        tx.commit();
                        success = true;
                        log.info("User successfuly added");
                    } else {
                        tx.rollback();
                        log.info("User insert failed");
                    }
                } catch (HibernateException e){
                    log.info("addUser insert failed: "+e.getMessage());
                    tx.rollback();
                    log.info("Transaction did a rollback");
                }
            }
        } catch(FlyingNullException fne) {
            log.error("Flying null - some wierd error occured: "+fne.getMessage());
        } catch (ContainsNumbersException cne){
            log.error("String contains numbers but it shouldnt: "+cne.getMessage());
        } catch (EmptyStringException ese){
            log.error("String is empty: "+ese.getMessage());
        } catch (Exception e){
            log.error("Some general error occured: "+e.getMessage());
        }
        return success;
    }
    
    public Users getUserByUsername(String username){
        log.info("getUserByUsername called with parameter {}", username);
        List<Users> u = new ArrayList<Users>();
        Users us = new Users();
        
        Session session = DbAccess.getSession();
        
        try {
            if(Validator.isValidString(username)){
                log.info("Querying database");
                Query q = session.createQuery("FROM Users WHERE USERNAME=:username");
                q.setString("username", username);
                u = q.list();
                if(u != null && u.size() > 0){
                    log.info("User found in database");
                    us = u.get(0);
                } else {
                    log.info("User not found in the database");
                    throw new Exception("User not found in database");
                }
            } else {
                log.info("Invalid parameter username given");
            }
        } catch (Exception e){
            log.error("Getting user from username failed: "+e.getMessage());
        }
        
        log.info("Returning the user object {}", us);
        return us;
    }
    
    public Users getUserById(Integer id){
        log.info("getUserById called with parameter {}", id);
        Users u = new Users();
        
        Session session = DbAccess.getSession();
        
        try {
            u = (Users)session.get(Users.class, id);
            if(u != null){
                log.info("User successfuly retrieved from database");
            } else {
                log.info("No user with id {} in database", id);
                throw new Exception("No user with such id in database: "+id);
            }
        } catch (Exception e){
            log.error("Getting user from id failed: "+e.getMessage());
        }
        return u;
    }
}
