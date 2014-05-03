package si.flyerman.ads;

import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import si.flyerman.adverts.Advert;
import si.flyerman.interfaces.AdvertManager;

/**
 * REST Web Service
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
@Path("service")
public class AdvertService {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AdvertService
     */
    public AdvertService() {
    }

    /**
     * Retrieves representation of an instance of si.flyerman.ads.AdvertService
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml() {
        return "<h1>Get some REST!</h1>";
    }

    /**
     * PUT method for updating or creating an instance of AdvertService
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/html")
    public void putHtml(String content) {
    }
    
    /**
     * RESTful implementation for get all adverts
     * @return all adverts
     */
    @Path("advert")
    @GET
    @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    public ArrayList<Advert> getAllAdverts(){
        AdvertManager am = new AdvertManager();
        ArrayList<Advert> ad = am.getListAdverts();
        
        return ad;
    }
    
    @Path("advert/{id}")
    @GET
    @Produces({MediaType.TEXT_XML, MediaType.APPLICATION_JSON})
    @Consumes(MediaType.APPLICATION_JSON)
    public Advert getAdvert(@PathParam("id") Integer id){
        AdvertManager am = new AdvertManager();
        // dobi sliko in jo poslji klientu
        Advert ad = am.getAdvert(id.intValue());
        return ad;
    }
    
}
