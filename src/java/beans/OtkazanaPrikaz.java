package beans;

import java.util.Date;


public class OtkazanaPrikaz {
    
    private int glinijaId;
    private int brojLinije;
    private String polaznoStajaliste;
    private String krajnjeStajaliste;
    private Date otkazanaDo;
    
    //------------------------

    public OtkazanaPrikaz() {
    }

    public int getGlinijaId() {
        return glinijaId;
    }

    public void setGlinijaId(int glinijaId) {
        this.glinijaId = glinijaId;
    }
    
    
    
    
    public int getBrojLinije() {
        return brojLinije;
    }

    public void setBrojLinije(int brojLinije) {
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

    public Date getOtkazanaDo() {
        return otkazanaDo;
    }

    public void setOtkazanaDo(Date otkazanaDo) {
        this.otkazanaDo = otkazanaDo;
    }
    
    
    
    
    
    
}
