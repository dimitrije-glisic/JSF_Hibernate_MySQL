package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="adresa")
public class Adresa {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer adresaId;
    private String grad;
    private String opstina;
    private String ulica;
    private Integer broj;

    public Adresa() {
    }

    public int getAdresaId() {
        return adresaId;
    }

    public String getGrad() {
        return grad;
    }

    public String getOpstina() {
        return opstina;
    }

    public String getUlica() {
        return ulica;
    }

    public Integer getBroj() {
        return broj;
    }

    public void setAdresaId(int adresaId) {
        this.adresaId = adresaId;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public void setBroj(Integer broj) {
        this.broj = broj;
    }

}
