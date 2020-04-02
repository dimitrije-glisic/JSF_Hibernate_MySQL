/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import baza.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.GLinija;
import tabele.OtkazanaLinija;
import tabele.RedVoznje;
import tabele.Vozac;

@ManagedBean(name = "glinija")
@SessionScoped
public class GLinijaTabela {

    //-----------------------------------
    private int brojLinije;
    private String polaznoStajaliste;
    private String krajnjeStajaliste;

    //----------------------------
    private List<GLinija> tabela = null;

    {
        tabela = sveLinije();
    }

    private GLinija odabranaLinija = null;

    private List<Vozac> vozaci = null;

    private RedVoznje redVoznje = null;

    //----------------------------
    private Session session = null;
    private Transaction transaction = null;

    //----------------------------------
    //-----------------------------------
   /* @PostConstruct
     public void init() {
     tabela = sveLinije();
     }
     */
    public GLinijaTabela() {
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

    public List<GLinija> getTabela() {
        return tabela;
    }

    public void setTabela(List<GLinija> tabela) {
        this.tabela = tabela;
    }

    public GLinija getOdabranaLinija() {
        return odabranaLinija;
    }

    public void setOdabranaLinija(GLinija odabranaLinija) {
        this.odabranaLinija = odabranaLinija;
    }

    public List<Vozac> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<Vozac> vozaci) {
        this.vozaci = vozaci;
    }

    public RedVoznje getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(RedVoznje redVoznje) {
        this.redVoznje = redVoznje;
    }

    //--------------------------------------------
    public List<String> getStajalista() {

        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select s.naziv from Stajaliste s");
        transaction = session.beginTransaction();
        List<String> stajalista = q.list();
        transaction.commit();

        session.close();

        return stajalista;

    }

    //------------------------------------
    public String pretraga() {

        session = HibernateUtil.getSessionFactory().openSession();

        if (!tabela.isEmpty()) {
            tabela = new ArrayList<>();
        }

        //-------------------
        if (polaznoStajaliste == null && krajnjeStajaliste == null) {
            FacesMessage message = new FacesMessage("Unesite bar polaziste odnosno odrediste!");
            FacesContext.getCurrentInstance().addMessage("gpretraga", message);
            return "";
        }

        //osnovna provera u redu - moze pretraga
        if (polaznoStajaliste != null && krajnjeStajaliste != null) {

            if (polaznoStajaliste.equals(krajnjeStajaliste)) {

                FacesMessage message = new FacesMessage("Unesite razlicita polja za polaziste i odrediste!");
                FacesContext.getCurrentInstance().addMessage("gpretraga", message);
                return "";

            }

            polazisteOdredistePretraga();

        } else if (polaznoStajaliste != null) {

            polazistePretraga();

        } else {

            odredistePretraga();

        }

        polaznoStajaliste = krajnjeStajaliste = null;

        session.close();

        return "";

    }

    public void polazisteOdredistePretraga() {

        Query q = session.createQuery("from GLinija g where g.aktivna='1'");
        transaction = session.beginTransaction();
        List<GLinija> linije = q.list();
        transaction.commit();

        //Za svaku liniju dohvatamo medjustanice i gledamo da li se unutar sekvence medjustanica
        //nalaze polaziste i odrediste
        for (GLinija linija : linije) {

            List<String> stanice = stajalistaZaLiniju(linija.getGlinijaId());

            if (stanice.contains(polaznoStajaliste) && stanice.contains(krajnjeStajaliste)) {
                tabela.add(linija);
            }
        }

        if (tabela.isEmpty()) {
            FacesMessage message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("gpretraga", message);
        }

    }

    public void polazistePretraga() {
        Query q = session.createQuery("from GLinija g where g.aktivna='1'");
        transaction = session.beginTransaction();
        List<GLinija> linije = q.list();
        transaction.commit();

        //Za svaku liniju dohvatamo medjustanice i gledamo da li se unutar sekvence medjustanica
        //nalaze polaziste i odrediste
        for (GLinija linija : linije) {

            List<String> stanice = stajalistaZaLiniju(linija.getGlinijaId());

            if (stanice.contains(polaznoStajaliste)) {
                tabela.add(linija);
            }
        }

        if (tabela.isEmpty()) {
            FacesMessage message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("gpretraga", message);
        }
    }

    public void odredistePretraga() {
        Query q = session.createQuery("from GLinija g where g.aktivna='1'");
        transaction = session.beginTransaction();
        List<GLinija> linije = q.list();
        transaction.commit();

        //Za svaku liniju dohvatamo medjustanice i gledamo da li se unutar sekvence medjustanica
        //nalaze polaziste i odrediste
        for (GLinija linija : linije) {

            List<String> stanice = stajalistaZaLiniju(linija.getGlinijaId());

            if (stanice.contains(krajnjeStajaliste)) {
                tabela.add(linija);
            }
        }

        if (tabela.isEmpty()) {
            FacesMessage message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("gpretraga", message);
        }
    }

    public String odabranaLinijaStajalista() {
        session = HibernateUtil.getSessionFactory().openSession();

        List<String> stajalista = stajalistaZaLiniju(odabranaLinija.getGlinijaId());
        String s = stajalista.toString();
        s = s.substring(1, s.length() - 1);
        s = s.replace(',', '-');

        session.close();

        return s;
    }

    private List<String> stajalistaZaLiniju(int gid) {
        Query q = session.createQuery("select gs.stajalisteId from GLinijaStajaliste gs"
                + " where gs.glinijaId='" + gid + "' order by redosled");
        transaction = session.beginTransaction();
        List<Integer> sids = q.list();
        transaction.commit();

        //nazivi stanica- ono sto nam treba i to sortirani zbog order by u prethodnom upitu
        List<String> stajalista = new ArrayList<>();
        for (Integer sid : sids) {

            q = session.createQuery("select s.naziv from Stajaliste s where s.stajalisteId='" + sid + "'");
            transaction = session.beginTransaction();
            String stajaliste = (String) q.uniqueResult();
            transaction.commit();

            stajalista.add(stajaliste);

        }

        return stajalista;

    }

    public String detalji() {
        session = HibernateUtil.getSessionFactory().openSession();

        this.vozaci = new ArrayList<>();
        this.redVoznje = new RedVoznje();

        Query q = session.createQuery("select count(gv) from GLinijaVozac gv where gv.glinijaId='" + odabranaLinija.getGlinijaId() + "'");
        transaction = session.beginTransaction();
        Long cntv = (Long) q.uniqueResult();
        transaction.commit();

        if (cntv > 0) {
            //postoji bar 1 vozac za liniju

            q = session.createQuery("select gv.vozacId from GLinijaVozac gv where gv.glinijaId='" + odabranaLinija.getGlinijaId() + "'");
            transaction = session.beginTransaction();
            List<Integer> vids = q.list();
            transaction.commit();

            q = session.createQuery("from Vozac v where v.vozacId in :vids");
            q.setParameterList("vids", vids);
            transaction = session.beginTransaction();
            this.vozaci = q.list();
            transaction.commit();

        }

        q = session.createQuery("select count(g) from GLinijaRedVoznje g where g.glinijaId='" + odabranaLinija.getGlinijaId() + "'");
        transaction = session.beginTransaction();
        Long cntrv = (Long) q.uniqueResult();
        transaction.commit();

        if (cntrv > 0) {
            //postoji red voznje za liniju 

            q = session.createQuery("select grv.redvoznjeId from GLinijaRedVoznje grv where grv.glinijaId='" + odabranaLinija.getGlinijaId() + "'");
            transaction = session.beginTransaction();
            Integer rvid = (Integer) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("from RedVoznje rv where rv.redvoznjeId='" + rvid + "'");
            transaction = session.beginTransaction();
            this.redVoznje = (RedVoznje) q.uniqueResult();
            transaction.commit();

        }

        session.close();

        return "gdetalji";
    }

    public List<GLinija> sveLinije() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from GLinija g where g.aktivna='1'");
        transaction = session.beginTransaction();
        List<GLinija> sveLinije = q.list();
        transaction.commit();

        session.close();

        return sveLinije;
    }
    
    public List<OtkazanaPrikaz> otkazaneLinije() {
        session = HibernateUtil.getSessionFactory().openSession();
        
        List<OtkazanaPrikaz> otkazaneLinije = new ArrayList<>();
        
        Query q = session.createQuery("from OtkazanaLinija");
        transaction = session.beginTransaction();
        List<OtkazanaLinija> otkazane = q.list();
        transaction.commit();
        
        for(OtkazanaLinija o : otkazane) {
            
            q = session.createQuery("from GLinija g where g.glinijaId='" + o.getGlinijaId() + "'");
            transaction = session.beginTransaction();
            GLinija g = (GLinija) q.uniqueResult();
            transaction.commit();
            
            OtkazanaPrikaz otkazana = new OtkazanaPrikaz();
            otkazana.setGlinijaId(g.getGlinijaId());
            otkazana.setBrojLinije(g.getBrojLinije());
            otkazana.setPolaznoStajaliste(g.getPolaznoStajaliste());
            otkazana.setKrajnjeStajaliste(g.getKrajnjeStajaliste());
            otkazana.setOtkazanaDo(o.getOtkazanaDo());
            
            otkazaneLinije.add(otkazana);
            
        }
        
        return otkazaneLinije;
        
    }

}
