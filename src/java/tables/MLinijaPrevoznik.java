package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="mlinija_prevoznik")
public class MLinijaPrevoznik {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mlinija_prevoznikId")
    private Integer mpId;
    private Integer mlinijaId;
    private Integer prevoznikId;
    
    
    //---------------------------------------
    
    public MLinijaPrevoznik() {
    }

    public Integer getMpId() {
        return mpId;
    }

    public Integer getMlinijaId() {
        return mlinijaId;
    }

    public Integer getPrevoznikId() {
        return prevoznikId;
    }

    public void setMlinijaId(Integer mlinijaId) {
        this.mlinijaId = mlinijaId;
    }

    public void setPrevoznikId(Integer prevoznikId) {
        this.prevoznikId = prevoznikId;
    }

    public void setMpId(Integer mpId) {
        this.mpId = mpId;
    }

}
