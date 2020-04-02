package beans;

import baza.HibernateUtil;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.faces.validator.ValidatorException;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Adresa;
import tabele.Korisnik;
import tabele.KorisnikAdresa;
import tabele.ZahtevRegistracija;

@ManagedBean
@SessionScoped
public class RegisterController {

    @Pattern(regexp = "^[A-Z][a-z]{2,15}( [A-Z][a-z]{2,15})?$", message = "Nepravilan unos")
    private String ime;

    @Pattern(regexp = "^[A-Z][a-z]{2,15}(-[A-Z][a-z]{2,15})?$", message = "Nepravilan unos")
    private String prezime;

    @Pattern(regexp = "^\\w.{3,14}$", message = "Korisnicko ime je duzine od 4 do 15 karaktera i mora poceti slovom"
            + " cifrom ili donjom crtom")
    private String korisnickoIme;

    //@Pattern(regexp = "((\\w*\\d+)|(\\d+\\w*)){5,10}", message = "Sifra mora sadrzati bar jednu cifru")
    private String sifra;

    @NotNull(message = "Niste uneli polje Potvrdi sifru")
    private String sifraPotvrda;

    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Datum rodjenja mora biti unet u odgovarajucem formatu")
    private String datumRodjenja;

    @Pattern(regexp = "^\\d{9,10}$", message = "Telefon ima 9 ili 10 cifara")
    private String telefon;

    @NotNull(message = "Niste uneli polje kategorija")
    private String kategorija;

    @Pattern(regexp = "^\\w.*@[a-z]{3,10}.((com)|(org)|(edu))$", message = "Nepravilan unos")
    private String email;

    //adresa
    @Pattern(regexp = "^[A-Z][a-z]{2,15}( [A-Z][a-z]{2,15})?( [A-Z][a-z]{2,15})?$", message = "Nepravilan unos")
    private String grad;

    @Pattern(regexp = "^[A-Z][a-z]{2,15}( [A-Z][a-z]{2,15})?( [A-Z][a-z]{2,15})?$", message = "Nepravilan unos")
    private String opstina;

    @Pattern(regexp = "^[A-Z][a-z]{2,15}( [A-Z][a-z]{2,15})?( [A-Z][a-z]{2,15})?$", message = "Nepravilan unos")
    private String ulica;

    @Pattern(regexp = "^\\d{1,3}$", message = "Nepravilan unos")
    private String broj;

    @Pattern(regexp = "^\\d{7}$")
    private String captcha;

    //-------------------------------------------
    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getSifraPotvrda() {
        return sifraPotvrda;
    }

    public void setSifraPotvrda(String sifraPotvrda) {
        this.sifraPotvrda = sifraPotvrda;
    }

    public String getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(String datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getKategorija() {
        return kategorija;
    }

    public void setKategorija(String kategorija) {
        this.kategorija = kategorija;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrad() {
        return grad;
    }

    public void setGrad(String grad) {
        this.grad = grad;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getTrenutnaCaptcha() {
        return trenutnaCaptcha;
    }

    public void setTrenutnaCaptcha(String trenutnaCaptcha) {
        this.trenutnaCaptcha = trenutnaCaptcha;
    }

    private int flag = 0;

    //--------------------------------------------------
    public String registracija() {

        if (!captcha.equals(trenutnaCaptcha)) {
            FacesMessage poruka = new FacesMessage("Pogresna captcha");
            FacesContext.getCurrentInstance().addMessage("registerform", poruka);
            trenutnaCaptcha=napraviBroj();
            return "";
        }
        
        trenutnaCaptcha=napraviBroj();
        
        if (!sifra.equals(sifraPotvrda)) {
            FacesMessage poruka = new FacesMessage("Polje [potvrdi sifru] mora biti isto kao polje [sifra]!");
            FacesContext.getCurrentInstance().addMessage("registerform", poruka);
            return "";
        }

        if (!isDatum(datumRodjenja)) {
            FacesMessage poruka = new FacesMessage("Nepravilan unos za datum");
            FacesContext.getCurrentInstance().addMessage("registerform", poruka);
            return "";
        }

        Session session = HibernateUtil.getSessionFactory().openSession();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date datum = null;
        try {
            datum = format.parse(datumRodjenja);
        } catch (ParseException ex) {
            Logger.getLogger(RegisterController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Korisnik korisnik = new Korisnik();
        korisnik.setIme(ime);
        korisnik.setPrezime(prezime);
        korisnik.setKorisnickoIme(korisnickoIme);
        korisnik.setSifra(sifra);
        korisnik.setDatumRodjenja(datum);
        korisnik.setTelefon(telefon);
        korisnik.setEmail(email);

        Transaction transaction = session.beginTransaction();
        Integer korisnikId = (Integer) session.save(korisnik);
        transaction.commit();

        Adresa adresa = new Adresa();
        adresa.setGrad(grad);
        adresa.setOpstina(opstina);
        adresa.setUlica(ulica);
        adresa.setBroj(Integer.parseInt(broj));

        transaction = session.beginTransaction();
        Integer adresaId = (Integer) session.save(adresa);
        transaction.commit();

        transaction = session.beginTransaction();
        KorisnikAdresa ka = new KorisnikAdresa();
        ka.setAdresaId(adresaId);
        ka.setKorisnikId(korisnikId);
        session.save(ka);
        transaction.commit();

        transaction = session.beginTransaction();
        ZahtevRegistracija zr = new ZahtevRegistracija();
        zr.setKorisnikId(korisnikId);
        session.save(zr);
        transaction.commit();

        session.close();

        //reset fields
        this.broj = this.datumRodjenja = this.email = this.grad = this.ime = this.kategorija = this.korisnickoIme = this.opstina = null;
        this.prezime = this.sifra = this.sifraPotvrda = this.telefon = this.ulica = null;
        this.captcha=null;

        return "";

    }

    private boolean isDatum(String datum) {

        String sgodina = datum.substring(0, 4);
        String smesec = datum.substring(5, 7);
        String sdan = datum.substring(8, 10);

        int godina = Integer.parseInt(sgodina);
        int mesec = Integer.parseInt(smesec);
        int dan = Integer.parseInt(sdan);

        if (godina < 1950 || godina > 2010) {
            return false;
        }

        if (dan < 1 || mesec < 1 || mesec > 12) {
            return false;
        }

        if (mesec == 2) {
            if (isPrestupna(godina)) {
                return dan <= 29;
            } else {
                return dan <= 28;
            }
        } else if (mesec == 4 || mesec == 6 || mesec == 9 || mesec == 11) {
            return dan <= 30;
        } else {
            return dan <= 31;
        }

    }

    private boolean isPrestupna(int godina) {
        return godina % 4 == 0 && (godina % 100 != 0 || godina % 400 == 0);
    }

    public void validateSifra(final FacesContext context, final UIComponent component,
            final Object value) {

        String sifra = (String) value;

        int malaSlova = 0; //min 3
        int velikaSlova = 0; //min 1
        int istiUzastopno = 0; //max 2
        int brojeviISpecZnaci = 0; //min 1
        Character prethodni;

        int duzina = sifra.length();

        if (duzina < 6 || duzina > 12) {

            FacesMessage poruka = new FacesMessage("Sifra mora da: ima 6-12 karaktera, pocinje slovom, ima min 1 veliko slovo"
                    + " min 3 mala slova, min 1 spec karakter ili cifra i najvise 2 ista uzastopna znaka");
            throw new ValidatorException(poruka);

        }

        Character ch = sifra.charAt(0);
        if (!Character.isLetter(ch)) {
            FacesMessage poruka = new FacesMessage("Sifra mora da: ima 6-12 karaktera, pocinje slovom, ima min 1 veliko slovo"
                    + " min 3 mala slova, min 1 spec karakter ili cifra i najvise 2 ista uzastopna znaka");
            throw new ValidatorException(poruka);
        } else {
            if (Character.isUpperCase(ch)) {
                velikaSlova++;
            } else {
                malaSlova++;
            }
        }

        prethodni = ch;
        for (int i = 1; i < duzina; i++) {
            ch = sifra.charAt(i);
            if (prethodni.equals(ch)) {
                istiUzastopno++;
            }
            if (Character.isLetter(ch)) {
                if (Character.isUpperCase(ch)) {
                    velikaSlova++;
                } else {
                    malaSlova++;
                }
            } else {
                brojeviISpecZnaci++;
            }
            prethodni = ch;
        }

        if (malaSlova < 3 || velikaSlova < 1 || istiUzastopno > 2 || brojeviISpecZnaci < 1) {
            FacesMessage poruka = new FacesMessage("Sifra mora da: ima 6-12 karaktera, pocinje slovom, ima min 1 veliko slovo"
                    + " min 3 mala slova, min 1 spec karakter ili cifra i najvise 2 ista uzastopna znaka");
            throw new ValidatorException(poruka);
        }

    }

    private String trenutnaCaptcha = napraviBroj();

    public String napraviBroj() {
        Random rand = new Random();
        String broj = "";

        for (int i = 0; i < 7; i++) {

            broj += Integer.toString(rand.nextInt(10));

        }

        this.trenutnaCaptcha = broj;

        return broj;

    }

}
