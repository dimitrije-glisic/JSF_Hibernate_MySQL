package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "stajaliste")
public class Stajaliste {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer stajalisteId;
    private String naziv;

    public Stajaliste() {
    }

    public Integer getStajalisteId() {
        return stajalisteId;
    }

    public void setStajalisteId(int stajalisteId) {
        this.stajalisteId = stajalisteId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

}
