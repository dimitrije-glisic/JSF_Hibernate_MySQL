package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zahtev_registracija")
public class ZahtevRegistracija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zahtev_registracijaId")
    private Integer zrId;
    private Integer korisnikId;

    public ZahtevRegistracija() {
    }

    public int getZrId() {
        return zrId;
    }

    public Integer getKorisnikId() {
        return korisnikId;
    }

    public void setZrId(Integer zrId) {
        this.zrId = zrId;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

}
