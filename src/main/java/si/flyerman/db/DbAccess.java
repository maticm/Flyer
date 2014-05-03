package si.flyerman.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public class DbAccess {
    private static SessionFactory factory;
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<>();
    static final Logger log = LoggerFactory.getLogger(DbAccess.class);
    
    static {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            log.error(e.getMessage());
            throw  new HibernateException(e);
        }
    }
    
    private DbAccess(){}
    
    public static Session getSession() throws HibernateException{
        Session session = (Session)threadLocal.get();
        
        if(session == null || !session.isOpen()){
            if(factory == null){
                rebuildSessionFactory();
            }
            session = (factory != null) ? factory.openSession() : null;
            threadLocal.set(session);
        }
        return session;
    }
    
    public static void rebuildSessionFactory() {
        try {
            factory = new Configuration().configure().buildSessionFactory();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);

        if (session != null) {
            session.close();
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return factory;
    }
    
    /*public synchronized SessionFactory getSession(){
        return factory;
    }*/
}
