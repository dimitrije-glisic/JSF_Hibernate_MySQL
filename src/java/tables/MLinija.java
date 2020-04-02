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
@Table(name = "mlinija")
public class MLinija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer mlinijaId;
    private String polaziste;
    private String odrediste;
    @Temporal(TemporalType.DATE)
    private Date datum;
    @Temporal(TemporalType.TIME)
    private Date vremePolaska;
    @Temporal(TemporalType.TIME)
    private Date vremeDolaska;

    //----------------------------------
    public MLinija() {
    }

    public Integer getMlinijaId() {
        return mlinijaId;
    }

    public String getPolaziste() {
        return polaziste;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public Date getDatum() {
        return datum;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public Date getVremeDolaska() {
        return vremeDolaska;
    }

    public void setMlinijaId(Integer mlinijaId) {
        this.mlinijaId = mlinijaId;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public void setVremeDolaska(Date vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

}
