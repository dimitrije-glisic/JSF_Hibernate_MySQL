package tabele;

import java.time.Duration;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@Table(name = "vozac")
public class Vozac {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer vozacId;
    private String ime;
    private String prezime;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datumRodjenja;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datumZaposlenja;

   /* @Transient
    private double staz;*/

    //-----------------------------
    public Vozac() {
    }

    public Integer getVozacId() {
        return vozacId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public Date getDatumZaposlenja() {
        return datumZaposlenja;
    }

  /*  public double getStaz() {
        Date trenutno = new Date();
        long razlika = trenutno.getTime() - this.datumZaposlenja.getTime();
        long godinaUMs = 365 * 24 * 3600000;
        this.staz = razlika / godinaUMs;
        
        
                
        return staz;
    }*/

    public void setVozacId(Integer vozacId) {
        this.vozacId = vozacId;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setDatumZaposlenja(Date datumZaposlenja) {
        this.datumZaposlenja = datumZaposlenja;
    }

   /* public void setStaz(long staz) {
        this.staz = staz;
    }*/

}
