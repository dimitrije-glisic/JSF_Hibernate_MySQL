package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "autobus")
public class Autobus {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer autobusId;
    private String marka;
    private String model;
    private Integer brojSedista;
    
    @Transient
    private byte brojSlika;

    public byte getBrojSlika() {
        return brojSlika;
    }

    public void setBrojSlika(byte brojSlika) {
        this.brojSlika = brojSlika;
    }
    
    
    
    //---------------------

    public Autobus() {
    }
    
    
    
    
    public Integer getAutobusId() {
        return autobusId;
    }

    public String getMarka() {
        return marka;
    }

    public String getModel() {
        return model;
    }

    public Integer getBrojSedista() {
        return brojSedista;
    }

    public void setAutobusId(Integer autobusId) {
        this.autobusId = autobusId;
    }

    public void setMarka(String marka) {
        this.marka = marka;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setBrojSedista(Integer brojSedista) {
        this.brojSedista = brojSedista;
    }

}
