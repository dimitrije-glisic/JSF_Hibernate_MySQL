package tabele;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "redvoznje")
public class RedVoznje {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer redvoznjeId;
    private String intervalrd;
    private String intervals;
    private String intervaln;
    private Integer predvidjenoVremeVoznje;

    //------------------------------------
    public RedVoznje() {
    }

    public int getRedvoznjeId() {
        return redvoznjeId;
    }

    public void setRedvoznjeId(int redvoznjeId) {
        this.redvoznjeId = redvoznjeId;
    }

    public String getIntervalrd() {
        return intervalrd;
    }

    public void setIntervalrd(String intervalrd) {
        this.intervalrd = intervalrd;
    }

    public String getIntervals() {
        return intervals;
    }

    public void setIntervals(String intervals) {
        this.intervals = intervals;
    }

    public String getIntervaln() {
        return intervaln;
    }

    public void setIntervaln(String intervaln) {
        this.intervaln = intervaln;
    }

    public Integer getPredvidjenoVremeVoznje() {
        return predvidjenoVremeVoznje;
    }

    public void setPredvidjenoVremeVoznje(Integer predvidjenoVremeVoznje) {
        this.predvidjenoVremeVoznje = predvidjenoVremeVoznje;
    }
    
    

}
