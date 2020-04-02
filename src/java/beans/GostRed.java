package beans;

import java.sql.Time;
import java.util.Date;

public class GostRed {

    private Integer mid = null;
    private String prevoznik = "";
    private Date datum = null;
    private Date vremePolaska = null;
    private String medjustanice = "";

    //--------------------------------
    //--------------------------------
    public GostRed() {
    }

    public GostRed(Integer mid, String prevoznik, Date datum, Date vremePolaska, String medjustanice) {
        this.mid = mid;
        this.prevoznik = prevoznik;
        this.datum = datum;
        this.vremePolaska = vremePolaska;
        this.medjustanice = medjustanice;
    }

    public Integer getMid() {
        return mid;
    }

    public String getPrevoznik() {
        return prevoznik;
    }

    public Date getDatum() {
        return datum;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public String getMedjustanice() {
        return medjustanice;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public void setMedjustanice(String medjustanice) {
        this.medjustanice = medjustanice;
    }

}
