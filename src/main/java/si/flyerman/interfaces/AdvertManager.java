package si.flyerman.interfaces;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import si.flyerman.adverts.Advert;
import si.flyerman.db.DbAccess;

/**
 *
 * @author maticm
 */
public class AdvertManager {
    static final Logger log = LoggerFactory.getLogger(AdvertManager.class);
    
    /**
     *
     * @param id
     * @return ArrayList
     */
    public Advert getAdvert(int id){
        log.info("listAdverts called with parameter {}", id);
        Session session = DbAccess.getSession();
        Transaction tx = null;
        
        Advert ad = new Advert();
        
        try {
            tx = session.beginTransaction();
            ad = (Advert)session.get(Advert.class, id);
            tx.commit();
        } catch(Exception e){
            log.error(e.getMessage());
        } finally {
            if(session != null){
                session.close();
            }
        }
        log.info("Returning the ad {}", ad);
        log.info("Looking for binPath now");
        
        if(ad == null)
            return new Advert();
        else {
            BinaryManager bm = new BinaryManager();
            String bp = bm.getBinPathForId(ad.getId());
            ad.setBinPath(bp);
        }
        return ad;
    }
    
    public Integer addAdvert(String name, Integer publisherId, Boolean active){
        log.info("Called method addAdvert with parameters name: {}, publisher: {}, active: {}",name, publisherId.toString());
        //log.info("Called method addAdvert with parameters name: {}, publisher: {}, active: {}", name, publisherId, active);
        /* factory = new Configuration().configure().buildSessionFactory();*/
        Session session = DbAccess.getSession();
        Transaction tx = null;
        Integer add = 0;
        Advert ad = new Advert();
        
        try {
            tx = session.beginTransaction();
            if(name != null && !name.equals("")){
                ad.setName(name);
            } else {
                throw new IllegalArgumentException("The name cannot be empty");
            }
            
            if(publisherId != null && publisherId > 0){
                ad.setPublisherId(publisherId);
            } else {
                throw new IllegalArgumentException("The file path cannot be empty");
            }
                        
            if(active != null){
                ad.setActive(active);
            } else {
                throw new IllegalArgumentException("The file path cannot be empty");
            }
            
            session.save(ad);
            tx.commit();
            add = 1;
        } catch (HibernateException | IllegalArgumentException e) {
            log.error(e.getMessage());
        } finally {
            session.close();
        }
        
        return add;
    }
    
    /**
     * Returns all adverts
     * @return an arraylist with complete Advert objects
     */
    public ArrayList getListAdverts(){
        log.info("Called method listAdverts");
        ArrayList list = new ArrayList<>();
        
        /* factory = new Configuration().configure().buildSessionFactory();
         * Session session = factory.openSession();*/
        Session session = DbAccess.getSession();
//        Transaction tx = null;
        
        try {
            List temp = session.createQuery("FROM Advert").list();
            for(Iterator it = temp.iterator(); it.hasNext();){
                list.add(it.next());
            }
  //          tx.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            session.close();
        }
        
        return list;
    }
}
