package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prevoznik")
public class Prevoznik {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer prevoznikId;
    private String naziv;
    private String telefon;
    private String logo;

    //----------------------------
    public Prevoznik() {
    }

    public int getPrevoznikId() {
        return prevoznikId;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getLogo() {
        return logo;
    }

    public void setPrevoznikId(int prevoznikId) {
        this.prevoznikId = prevoznikId;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

}
