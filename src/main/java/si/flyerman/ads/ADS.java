package si.flyerman.ads;

import java.util.ArrayList;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.BindingType;

import si.flyerman.adverts.Advert;
import si.flyerman.config.DbConfig;
import si.flyerman.db.DbAccess;
import si.flyerman.interfaces.AdvertManager;
import si.flyerman.interfaces.UserManager;
import si.flyerman.users.Users;

/**
 *
 * @author maticm
 */
@LocalBean
@Stateless
@WebService(name = "ADS", targetNamespace = "http://flyerman.si/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.WRAPPED, style = SOAPBinding.Style.RPC)
@BindingType(value = "http://java.sun.com/xml/ns/jaxws/2003/05/soap/bindings/HTTP/")
public class ADS {    
    public ADS(){
        DbAccess.getSession();
        DbConfig dbConfig = new DbConfig();
        dbConfig.loadConfig();
    }
    
    /**
     * @param id
     * @return specific Advert
     */
    @WebMethod
    public Advert getAdvert(@WebParam(name="id") int id){
        AdvertManager al = new AdvertManager();
        Advert ad = al.getAdvert(id);
        return ad;
    }
    
    @WebMethod
    public ArrayList<Advert> getAllAdverts(){
        AdvertManager adm = new AdvertManager();
        ArrayList<Advert> list = adm.getListAdverts();
        
        return list;
    }
    
    /**
     * 
     * @param name
     * @param file
     * @return Integer of success
     */
    @WebMethod
    public Integer addAdvert(@WebParam(name="name")String name, @WebParam(name="filePath") String file){
        Integer result = 0;
        AdvertManager ad = new AdvertManager();
        //result = ad.addAdvert(name, file);
        return result;
    }
    
    @WebMethod
    public boolean addNewUser(@WebParam(name="username")String username, @WebParam(name="password")String password, 
    @WebParam(name="type")Integer type, @WebParam(name="firstName")String firstName, @WebParam(name="lastName")String lastName) throws Exception{
        UserManager um = new UserManager();
        boolean success = um.addUser(username, password, type, firstName, lastName);
        return success;
    }
    
    @WebMethod
    public Users getUserByUsername(@WebParam(name="username")String username){
        UserManager um = new UserManager();
        Users u = um.getUserByUsername(username);
        return u;
    }
    
    @WebMethod
    public Users getUserById(@WebParam(name="id")Integer id){
        UserManager um = new UserManager();
        Users u;
        try {
            u = um.getUserById(id);
        } catch (Exception e){
            return new Users();
        }
        if(u != null)
            return u;
        else
            return new Users();
    }
    
    @WebMethod
    public boolean login(@WebParam(name="username") String username, @WebParam(name="password")String password){
        boolean login = false;
        
        UserManager um = new UserManager();
        
        if(um.isValidUser(username) && um.isValidPassword(username, password)){
            login = true;
        }
        
        return login;
    }
}
