package tabele;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mstanica")
public class Stanica {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "mstanicaId")
    private Integer stanicaId;
    private String naziv;
    private double gsirina;
    private double gduzina;

    //-----------------------------------------------------
    public Stanica() {
    }

    public Integer getStanicaId() {
        return stanicaId;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getGsirina() {
        return gsirina;
    }

    public double getGduzina() {
        return gduzina;
    }

    public void setStanicaId(int stanicaId) {
        this.stanicaId = stanicaId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setGsirina(double gsirina) {
        this.gsirina = gsirina;
    }

    public void setGduzina(double gduzina) {
        this.gduzina = gduzina;
    }

}
