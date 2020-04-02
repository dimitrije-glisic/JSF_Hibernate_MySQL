/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import baza.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Korisnik;

@ManagedBean
@SessionScoped
public class LoginController {
    
    @NotNull(message="Unesite korisnicko ime")
    private String korisnickoIme = null;
    @NotNull(message="Unesite sifru")
    private String sifra=null;

    private Session session = null;
    private Transaction transaction = null;

    //--------------------------------------
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

    //------------------------------------------
    public String login() {
        session = HibernateUtil.getSessionFactory().openSession();

        Korisnik k = null;

        Query q = session.createQuery("from Korisnik k where k.korisnickoIme='" + korisnickoIme
                + "' and k.sifra='" + sifra + "'");
        transaction = session.beginTransaction();
        k = (Korisnik) q.uniqueResult();
        transaction.commit();

        FacesMessage message = null;
        if (k == null) {
            message = new FacesMessage("Ne postoji takav korisnik!");
            FacesContext.getCurrentInstance().addMessage("loginForm", message);
            return "";
        }

        //ZAHTEV ZA REGISTRACIJU SE OBRADJUJE: JOS UVEK NIJE NI PRIHVACEN NI ODBIJEN
        if (k.getOdobren() == 0) {
            message = new FacesMessage("Vas zahtev za registraciju se obradjuje.");
            FacesContext.getCurrentInstance().addMessage("loginForm", message);
            return "";
        }

        if (k.getOdobren() == 2) {
            message = new FacesMessage("Vas zahtev za registraciju je odbijen.\n"
                    + "Pokusajte ponovo sa DRUGIM korisnickim imenom.");

            q = session.createQuery("delete from Korisnik k where k.korisnikId='" + k.getKorisnikId() + "'");
            transaction = session.beginTransaction();
            q.executeUpdate();
            transaction.commit();

            FacesContext.getCurrentInstance().addMessage("loginForm", message);
            return "";
        }

        this.korisnik=k;
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("korisnik", k);

        session.close();

        if (k.isAdmin()) {
            return "admin";
        } else {
            return "korisnik";
        }

    }
    
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpSession _session = (HttpSession) context.getExternalContext().getSession(false);
        _session.invalidate();
        
        return "index";
    }
    
    
    private Korisnik korisnik = null;

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }
    
    
    
}
