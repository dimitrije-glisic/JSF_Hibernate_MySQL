/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean_helpers;

/**
 *
 * @author MITA
 */
public class RezPrikaz {

    private String korisnickoIme;
    private Integer linija;
    private Integer slobodno;

    public RezPrikaz() {
        korisnickoIme = null;
        linija = slobodno = null;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public Integer getLinija() {
        return linija;
    }

    public void setLinija(Integer linija) {
        this.linija = linija;
    }

    public Integer getSlobodno() {
        return slobodno;
    }

    public void setSlobodno(Integer slobodno) {
        this.slobodno = slobodno;
    }
    
    

}
