package si.flyerman.interfaces;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.flyerman.db.DbAccess;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class BinaryManager {
    static final Logger log = LoggerFactory.getLogger(BinaryManager.class);
    
    public String getBinPathForId(Integer id){
        String path = null;
        log.info("getBinPathForId called with parameter {}", id);
        Session session = DbAccess.getSession();
        
        try {
            Query q = session.createQuery("SELECT binPath FROM Binaries WHERE id = :id");
            q.setInteger("id", id);
            List res = q.list();
            if(res != null && (res.size() > 0)){
                path = res.get(0).toString();
            }
        } catch(Exception e){
            log.error("Error while getting binary path for specified id: "+e.getMessage());
        }
        
        return path;
    }
}
