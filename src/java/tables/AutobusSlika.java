package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="autobus_slika")
public class AutobusSlika {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="autobus_slikaId")
    private Integer asId;
    private Integer autobusId;
    private Integer slikaId;
    
    //---------------------------------
    
    public AutobusSlika() {
    }

    public Integer getAsId() {
        return asId;
    }

    public Integer getAutobusId() {
        return autobusId;
    }

    public Integer getSlikaId() {
        return slikaId;
    }

    public void setAsId(Integer asId) {
        this.asId = asId;
    }

    public void setAutobusId(Integer autobusId) {
        this.autobusId = autobusId;
    }

    public void setSlikaId(Integer slikaId) {
        this.slikaId = slikaId;
    }
    
    
    
}
