package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "korisnik_adresa")
public class KorisnikAdresa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "korisnik_adresaId")
    private Integer kaId;
    private Integer korisnikId;
    private Integer adresaId;

    //------------------------------
    public KorisnikAdresa() {
    }

    public Integer getKaId() {
        return kaId;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public Integer getAdresaId() {
        return adresaId;
    }

    public void setKaId(Integer kaId) {
        this.kaId = kaId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public void setAdresaId(Integer adresaId) {
        this.adresaId = adresaId;
    }

}
