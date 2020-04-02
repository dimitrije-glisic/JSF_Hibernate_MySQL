package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="glinija")
public class GLinija {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer glinijaId;
    private Integer brojLinije;
    private String polaznoStajaliste;
    private String krajnjeStajaliste;
    private Integer aktivna;
    
    //--------------------------------

    public GLinija() {
    }

    public Integer getGlinijaId() {
        return glinijaId;
    }

    public void setGlinijaId(Integer glinijaId) {
        this.glinijaId = glinijaId;
    }

    public Integer getBrojLinije() {
        return brojLinije;
    }

    public void setBrojLinije(Integer brojLinije) {
        this.brojLinije = brojLinije;
    }

    public String getPolaznoStajaliste() {
        return polaznoStajaliste;
    }

    public void setPolaznoStajaliste(String polaznoStajaliste) {
        this.polaznoStajaliste = polaznoStajaliste;
    }

    public String getKrajnjeStajaliste() {
        return krajnjeStajaliste;
    }

    public void setKrajnjeStajaliste(String krajnjeStajaliste) {
        this.krajnjeStajaliste = krajnjeStajaliste;
    }

    public Integer getAktivna() {
        return aktivna;
    }

    public void setAktivna(Integer aktivna) {
        this.aktivna = aktivna;
    }
    
    
    
}
