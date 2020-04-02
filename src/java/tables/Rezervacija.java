package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "rezervacija")
public class Rezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rezervacijaId;
    private Integer korisnikId;
    private Integer mlinijaId;
    private Integer stanje;

    //---------------------
    public Rezervacija() {
    }

    public Integer getRezervacijaId() {
        return rezervacijaId;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public Integer getMlinijaId() {
        return mlinijaId;
    }

    public Integer getStanje() {
        return stanje;
    }

    public void setRezervacijaId(Integer rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public void setMlinijaId(Integer mlinijaId) {
        this.mlinijaId = mlinijaId;
    }

    public void setStanje(Integer stanje) {
        this.stanje = stanje;
    }

}
