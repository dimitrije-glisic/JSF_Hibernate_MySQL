package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "glinija_stajaliste")
public class GLinijaStajaliste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "glinija_stajalisteId")
    private Integer gsId;
    private Integer glinijaId;
    private Integer stajalisteId;
    private Integer redosled;

    //----------------------
    public GLinijaStajaliste() {
    }

    public Integer getGsId() {
        return gsId;
    }

    public void setGsId(Integer gsId) {
        this.gsId = gsId;
    }

    public int getGlinijaId() {
        return glinijaId;
    }

    public void setGlinijaId(Integer glinijaId) {
        this.glinijaId = glinijaId;
    }

    public int getStajalisteId() {
        return stajalisteId;
    }

    public void setStajalisteId(Integer stajalisteId) {
        this.stajalisteId = stajalisteId;
    }

    public int getRedosled() {
        return redosled;
    }

    public void setRedosled(Integer redosled) {
        this.redosled = redosled;
    }

    
}
