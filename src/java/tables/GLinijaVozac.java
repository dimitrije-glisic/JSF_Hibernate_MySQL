package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "glinija_vozac")
public class GLinijaVozac {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "glinija_vozacId")
    private Integer gvId;
    private Integer glinijaId;
    private Integer vozacId;

    //---------------------
    public GLinijaVozac() {
    }

    public Integer getGvId() {
        return gvId;
    }

    public void setGvId(Integer gvId) {
        this.gvId = gvId;
    }

    public Integer getGlinijaId() {
        return glinijaId;
    }

    public void setGlinijaId(Integer glinijaId) {
        this.glinijaId = glinijaId;
    }

    public Integer getVozacId() {
        return vozacId;
    }

    public void setVozacId(Integer vozacId) {
        this.vozacId = vozacId;
    }

}
