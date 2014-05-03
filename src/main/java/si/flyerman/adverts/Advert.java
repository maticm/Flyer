package si.flyerman.adverts;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author maticm
 */
@Entity
@Table (name="Advert")
@XmlRootElement(name="advert")
public class Advert implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(nullable = false, name="name")
    private String name;
    @Column(nullable = false, name="publisherId")
    private Integer publisherId;
    @Column(nullable = false, name="active")
    private Boolean active;
    @Column(nullable = false, name="binaryId")
    private Integer binaryId;
    
    private String binPath;
    
    
    public Advert(){}
    
    public Advert(String name, Integer publisherId, Boolean active, Integer binaryId){
        this.name = name;
        this.publisherId = publisherId;
        this.active = active;
        this.binaryId = binaryId;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the publisherId
     */
    public Integer getPublisherId() {
        return publisherId;
    }

    /**
     * @param publisherId the publisherId to set
     */
    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Integer active) {
        this.setActive(active);
    }

    /**
     * @return the binaryId
     */
    public Integer getBinaryId() {
        return binaryId;
    }

    /**
     * @param binaryId the binaryId to set
     */
    public void setBinaryId(Integer binaryId) {
        this.binaryId = binaryId;
    }

    /**
     * @param active the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the binPath
     */
    public String getBinPath() {
        return binPath;
    }

    /**
     * @param binPath the binPath to set
     */
    public void setBinPath(String binPath) {
        this.binPath = binPath;
    }
}
