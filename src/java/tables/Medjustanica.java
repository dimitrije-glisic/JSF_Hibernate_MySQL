package tabele;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "medjustanica")
public class Medjustanica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer medjustanicaId;
    private Integer mlinijaId;
    private Integer mstanicaId;
    @Temporal(TemporalType.TIME)
    private Date vremeDolaska;
    private Integer redosled;

    //---------------------------------
    public Medjustanica() {
    }

    //----------------------------------
    public int getMedjustanicaId() {
        return medjustanicaId;
    }

    public int getMlinijaId() {
        return mlinijaId;
    }

    public int getMstanicaId() {
        return mstanicaId;
    }

    public Date getVremeDolaska() {
        return vremeDolaska;
    }

    public int getRedosled() {
        return redosled;
    }

    public void setMedjustanicaId(int medjustanicaId) {
        this.medjustanicaId = medjustanicaId;
    }

    public void setMlinijaId(int mlinijaId) {
        this.mlinijaId = mlinijaId;
    }

    public void setMstanicaId(int mstanicaId) {
        this.mstanicaId = mstanicaId;
    }

    public void setVremeDolaska(Date vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

    public void setRedosled(int redosled) {
        this.redosled = redosled;
    }

}
