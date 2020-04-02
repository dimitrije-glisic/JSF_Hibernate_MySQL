package beans;

import baza.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.UploadedFile;
import tabele.Autobus;
import tabele.AutobusSlika;
import tabele.GLinija;
import tabele.GLinijaRedVoznje;
import tabele.GLinijaStajaliste;
import tabele.GLinijaVozac;
import tabele.Korisnik;
import tabele.MLinija;
import tabele.MLinijaAutobus;
import tabele.MLinijaPrevoznik;
import tabele.MLinijaVozac;
import tabele.Medjustanica;
import tabele.OtkazanaLinija;
import tabele.Prevoznik;
import tabele.RedVoznje;
import tabele.Rezervacija;
import tabele.Slika;
import tabele.Vozac;
import tabele.ZahtevKartica;
import tabele.ZahtevRezervacija;

@ManagedBean
@SessionScoped
@SuppressWarnings("empty-statement")
public class Admin {

    //----DODAVANJE AUTOBUS-------------------------------
    private Autobus autobus;
    private List<String> slike;

    //----DODAVANJE GLINIJE---------------------------------------
    public static final int MAX_STAJALISTA = 10;

    //------------------------------------------------------------
    public static final int MAX_REZERVACIJA = 3;

    //-------KARTICA (MESECNA | GODISNJA) -----------------------
    public static final double MESECNA_CENA = 3000;
    public static final double GODISNJA_CENA = 10000;

    public static final int STUDENT_POPUST = 30;
    public static final int NEZAPOSLEN_POPUST = 20;
    public static final int ZAPOSLEN_POPUST = 0;
    public static final int PENZIONER_POPUST = 30;
    public static final int LICE_SA_INVALIDITETOM_POPUST = 40;

    public static int getMAX_REZERVACIJA() {
        return MAX_REZERVACIJA;
    }

    Session session = null;
    Transaction transaction = null;

    //----------------------------------------------------
    //----------------------------------------------
    public List<Integer> slobodniVozaci() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select m.vozacId from MLinijaVozac m");
        transaction = session.beginTransaction();
        List<Integer> mvids = q.list();
        transaction.commit();

        q = session.createQuery("select g.vozacId from GLinijaVozac g");
        transaction = session.beginTransaction();
        List<Integer> gvids = q.list();
        transaction.commit();

        List<Integer> vids = new ArrayList<>(mvids);
        vids.addAll(gvids);

        List<Integer> slobodniVozaci = null;
        if (!vids.isEmpty()) {

            q = session.createQuery("select v.vozacId from Vozac v where v.vozacId not in (:vids)");
            q.setParameterList("vids", vids);
            transaction = session.beginTransaction();
            slobodniVozaci = q.list();
            transaction.commit();

        }

        return slobodniVozaci;

    }

    //-----DODAVANJE MLINIJA--------------
    //----------------------------------
    //------------------------------------------------------
    //----------------------------------------------------------------
    private String prevoznik;
    private String polaziste;
    private String odrediste;
    private String[] medjustanice_M = new String[Admin.MAX_STAJALISTA];
    private Date[] vremenaDolazaka = new Date[Admin.MAX_STAJALISTA];

    private Date vremePolaska;
    private Date vremeDolaska;
    private Date datum;

    private String autobus_M;
    private String autobusString;
    private String vozac_M;
    private String vozacString;

    public String getPrevoznik() {
        return prevoznik;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
    }

    public String getPolaziste() {
        return polaziste;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public String[] getMedjustanice_M() {
        return medjustanice_M;
    }

    public void setMedjustanice_M(String[] medjustanice_M) {
        this.medjustanice_M = medjustanice_M;
    }

    public Date getVremePolaska() {
        return vremePolaska;
    }

    public void setVremePolaska(Date vremePolaska) {
        this.vremePolaska = vremePolaska;
    }

    public Date getVremeDolaska() {
        return vremeDolaska;
    }

    public void setVremeDolaska(Date vremeDolaska) {
        this.vremeDolaska = vremeDolaska;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getAutobus_M() {
        return autobus_M;
    }

    public void setAutobus_M(String autobus_M) {
        this.autobus_M = autobus_M;

        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from Autobus a where a.autobusId='" + autobus_M + "'");
        transaction = session.beginTransaction();
        Autobus a = (Autobus) q.uniqueResult();
        transaction.commit();

        this.autobusString = "id" + a.getAutobusId() + "   broj sedista: " + a.getBrojSedista();

        session.close();

    }

    public String getVozac_M() {
        return vozac_M;
    }

    public void setVozac_M(String vozac_M) {
        this.vozac_M = vozac_M;

        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from Vozac v where v.vozacId='" + vozac_M + "'");
        transaction = session.beginTransaction();
        Vozac v = (Vozac) q.uniqueResult();
        transaction.commit();

        vozacString = "id" + v.getVozacId() + "   " + v.getIme() + " " + v.getPrezime();

        session.close();
    }

    public Date[] getVremenaDolazaka() {
        return vremenaDolazaka;
    }

    public void setVremenaDolazaka(Date[] vremenaDolazaka) {
        this.vremenaDolazaka = vremenaDolazaka;
    }

    public String getAutobusString() {
        return autobusString;
    }

    public void setAutobusString(String autobusString) {
        this.autobusString = autobusString;
    }

    public String getVozacString() {
        return vozacString;
    }

    public void setVozacString(String vozacString) {
        this.vozacString = vozacString;
    }

    //------------------
    public String dodavanjeMLinija() {
        prevoznik = polaziste = odrediste = null;
        for (int i = 0; i < Admin.MAX_STAJALISTA; i++) {
            medjustanice_M[i] = null;
            vremenaDolazaka[i] = null;
        }
        vremePolaska = vremeDolaska = datum = null;
        autobus_M = autobusString = vozac_M = vozacString = null;

        return "dodavanje_mlinija";
    }

    public List<Integer> slobodniAutobusi() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select ma.autobusId from MLinijaAutobus ma");
        transaction = session.beginTransaction();
        List<Integer> zauzetiAutobusi = q.list();
        transaction.commit();

        q = session.createQuery("select a.autobusId from Autobus a where a.autobusId not in :zauzetiAutobusi");
        q.setParameterList("zauzetiAutobusi", zauzetiAutobusi);
        transaction = session.beginTransaction();
        List<Integer> slobodniAutobusi = q.list();
        transaction.commit();

        session.close();

        return slobodniAutobusi;
    }

   

    //----------------------------
    //-------DODAVANJE PREVOZNIKA--------------
    //-------------------------------------------------
    private Prevoznik p = new Prevoznik();

    private String slika;

    public Prevoznik getP() {
        return p;
    }

    public void setP(Prevoznik p) {
        this.p = p;
    }

    public String getSlika() {
        return slika;
    }

    public void setSlika(String slika) {
        this.slika = slika;
    }

    public String dodavanjePrevoznik() {
        this.p = new Prevoznik();
        slika = null;

        return "dodavanje_prevoznik";
    }

    public void uploadPrevoznik(FileUploadEvent event) {
        if (slika != null) {
            return;
        }

        UploadedFile f = event.getFile();
        String naziv = f.getFileName();
        slika = naziv;
    }

    public String dodajPrevoznik() {
        session = HibernateUtil.getSessionFactory().openSession();

        if (slika != null) {

            this.p.setLogo(slika);

        }

        transaction = session.beginTransaction();
        session.save(p);
        transaction.commit();

        session.close();

        this.p = new Prevoznik();
        this.slika = null;

        return "";
    }

    public String promeniLogo() {
        this.slika = null;
        return "";
    }

}
