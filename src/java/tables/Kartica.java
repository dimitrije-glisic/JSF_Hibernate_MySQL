package tabele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "kartica")
public class Kartica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer karticaId;
    private Integer korisnikId;
    private char oznaka;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vaziOd;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date vaziDo;
    private double cena;
    private Integer stanje;

    //--------------------------------
    public Kartica() {
    }

    public Integer getKarticaId() {
        return karticaId;
    }

    public void setKarticaId(Integer karticaId) {
        this.karticaId = karticaId;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public char getOznaka() {
        return oznaka;
    }

    public void setOznaka(char oznaka) {
        this.oznaka = oznaka;
    }

    public Date getVaziOd() {
        return vaziOd;
    }

    public void setVaziOd(Date vaziOd) {
        this.vaziOd = vaziOd;
    }

    public Date getVaziDo() {
        return vaziDo;
    }

    public void setVaziDo(Date vaziDo) {
        this.vaziDo = vaziDo;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public int getStanje() {
        return stanje;
    }

    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

}
