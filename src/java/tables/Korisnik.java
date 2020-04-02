package tabele;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="korisnik")
public class Korisnik {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer korisnikId;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String sifra;
    private Date datumRodjenja;
    private String telefon;
    private char kategorija;
    private String email;
    private boolean admin;
    private short odobren;
    
    
    
    
    
    //--------------------------------------------
    
    
    public Korisnik() {
    }

    public Korisnik(String ime, String prezime, String korisnickoIme, String sifra, Date datumRodjenja, String telefon, char kategorija, String email) {
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.sifra = sifra;
        this.datumRodjenja = datumRodjenja;
        this.telefon = telefon;
        this.kategorija = kategorija;
        this.email = email;
    }
    
    
    
    public Integer getKorisnikId() {
        return korisnikId;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public char getKategorija() {
        return kategorija;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return admin;
    }

    public short getOdobren() {
        return odobren;
    }

    public void setKorisnikId(Integer korisnikId) {
        this.korisnikId = korisnikId;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public void setKategorija(char kategorija) {
        this.kategorija = kategorija;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setOdobren(short odobren) {
        this.odobren = odobren;
    }
    
    
}
