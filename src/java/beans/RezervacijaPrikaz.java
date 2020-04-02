package beans;

import tabele.MLinija;
import tabele.Rezervacija;

public class RezervacijaPrikaz {

    private Rezervacija rezervacija;
    private MLinija linija;
    
    
    
    //------------------------------------------
    
    
    public RezervacijaPrikaz() {
    }

    public Rezervacija getRezervacija() {
        return rezervacija;
    }

    public MLinija getLinija() {
        return linija;
    }

    public void setRezervacija(Rezervacija rezervacija) {
        this.rezervacija = rezervacija;
    }

    public void setLinija(MLinija linija) {
        this.linija = linija;
    }
    
    
    
}
