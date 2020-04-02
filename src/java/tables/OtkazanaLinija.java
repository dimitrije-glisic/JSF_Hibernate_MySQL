package tabele;

import java.util.Date;
import javax.faces.bean.SessionScoped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name = "otkazane_linije")
public class OtkazanaLinija {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "otkazane_linijeId")
    private Integer otkazanaId;
    private Integer glinijaId;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date otkazanaDo;

    //-------------------------------
    public OtkazanaLinija() {
    }

    public Integer getOtkazanaId() {
        return otkazanaId;
    }

    public void setOtkazanaId(Integer otkazanaId) {
        this.otkazanaId = otkazanaId;
    }

    public Integer getGlinijaId() {
        return glinijaId;
    }

    public void setGlinijaId(Integer glinijaId) {
        this.glinijaId = glinijaId;
    }

    public Date getOtkazanaDo() {
        return otkazanaDo;
    }

    public void setOtkazanaDo(Date otkazanaDo) {
        this.otkazanaDo = otkazanaDo;
    }

}
