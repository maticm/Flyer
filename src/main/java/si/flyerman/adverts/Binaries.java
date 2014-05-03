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
 * @author Matic Makovec <matic.makovec@gmail.com>
 */

@Entity
@Table(name="Binaries")
@XmlRootElement(name="binaries")
public class Binaries implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="binPath")
    private String binPath;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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
