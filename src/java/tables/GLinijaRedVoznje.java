package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "glinija_redvoznje")
public class GLinijaRedVoznje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "glinija_redvoznjeId")
    private Integer grvId;
    private Integer glinijaId;
    private Integer redvoznjeId;
    

    //-------------------------
    public GLinijaRedVoznje() {
    }

    public Integer getGrvId() {
        return grvId;
    }

    public void setGrvId(Integer grvId) {
        this.grvId = grvId;
    }

    public Integer getGlinijaId() {
        return glinijaId;
    }

    public void setGlinijaId(Integer glinijaId) {
        this.glinijaId = glinijaId;
    }

    public Integer getRedvoznjeId() {
        return redvoznjeId;
    }

    public void setRedvoznjeId(Integer redvoznjeId) {
        this.redvoznjeId = redvoznjeId;
    }

}
