package si.flyerman.config;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import si.flyerman.db.DbAccess;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
public final class DbConfig {
    static final Logger log = LoggerFactory.getLogger(DbConfig.class);
    
    private String pathToPics;

    final String pathConst = "PATH_TO_IMAGES";
    
    public DbConfig(){}
    
    public void loadConfig(){
        setPathToPics(pathConst);
    }
    
    /**
     * @return the pathToPics
     */
    public String getPathToPics() {
        return pathToPics;
    }

    /**
     * @param pathToPics the pathToPics to set
     */
    public void setPathToPics(String pathToPics) {
        this.pathToPics = getDbVal(pathToPics);
    }
    
    private String getDbVal(String constant){
        String value = null;
        Session session = DbAccess.getSession();
        Configuration conf = new Configuration();
        try {
            conf = (Configuration)session.get(Configuration.class,constant);
            value = conf.getVal();
        } catch (HibernateException e){
            log.error(e.getMessage());
        }
        
        return value;
    }
}
