package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zahtev_rezervacija")
public class ZahtevRezervacija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zahtev_rezervacijaId")
    private Integer zrId;
    private Integer rezervacijaId;

    public ZahtevRezervacija() {
    }

    public int getZrId() {
        return zrId;
    }

    public Integer getRezervacijaId() {
        return rezervacijaId;
    }

    public void setZrId(int zrId) {
        this.zrId = zrId;
    }

    public void setRezervacijaId(Integer rezervacijaId) {
        this.rezervacijaId = rezervacijaId;
    }

}
