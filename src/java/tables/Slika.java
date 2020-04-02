package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "slika")
public class Slika {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer slikaId;
    private String naziv;

    //------------------------------
    public Slika() {
    }

    public Integer getSlikaId() {
        return slikaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setSlikaId(Integer slikaId) {
        this.slikaId = slikaId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
