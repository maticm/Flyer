package si.flyerman.config;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Matic Makovec <matic.makovec@gmail.com>
 */
@Entity
@Table(name="Configuration")
public class Configuration implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    @Column(name="const")
    private String constant;
    @Column(name="val")
    private String val;

    /**
     * @return the constant
     */
    public String getConstant() {
        return constant;
    }

    /**
     * @param constant the constant to set
     */
    public void setConstant(String constant) {
        this.constant = constant;
    }

    /**
     * @return the value
     */
    public String getVal() {
        return val;
    }

    /**
     * @param value the value to set
     */
    public void setVal(String value) {
        this.val = value;
    }
}
