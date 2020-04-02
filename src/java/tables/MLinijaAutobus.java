package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="mlinija_autobus")
public class MLinijaAutobus {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mlinija_autobusId")
    private Integer maId;
    private Integer mlinijaId;
    private Integer autobusId;

    //-----------------------
    
    
    public MLinijaAutobus() {
    }

    public Integer getMaId() {
        return maId;
    }

    public Integer getMlinijaId() {
        return mlinijaId;
    }

    public Integer getAutobusId() {
        return autobusId;
    }

    public void setMaId(Integer maId) {
        this.maId = maId;
    }

    public void setMlinijaId(Integer mlinijaId) {
        this.mlinijaId = mlinijaId;
    }

    public void setAutobusId(Integer autobusId) {
        this.autobusId = autobusId;
    }
    
    
    
    
    
    
}
