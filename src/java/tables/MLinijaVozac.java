package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mlinija_vozac")
public class MLinijaVozac {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mlinija_vozacId")
    private Integer mvId;
    private Integer mlinijaId;
    private Integer vozacId;

    public MLinijaVozac() {
    }

    public Integer getMvId() {
        return mvId;
    }

    public Integer getMlinijaId() {
        return mlinijaId;
    }

    public Integer getVozacId() {
        return vozacId;
    }

    public void setMvId(Integer mvId) {
        this.mvId = mvId;
    }

    public void setMlinijaId(Integer mlinijaId) {
        this.mlinijaId = mlinijaId;
    }

    public void setVozacId(Integer vozacId) {
        this.vozacId = vozacId;
    }

}
