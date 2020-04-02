package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zahtev_kartica")
public class ZahtevKartica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "zahtev_karticaId")
    private Integer zkId;
    private Integer karticaId;

    //----------------------------------
    public ZahtevKartica() {
    }

    public Integer getZkId() {
        return zkId;
    }

    public void setZkId(int zkId) {
        this.zkId = zkId;
    }

    public Integer getKarticaId() {
        return karticaId;
    }

    public void setKarticaId(Integer karticaId) {
        this.karticaId = karticaId;
    }

}
