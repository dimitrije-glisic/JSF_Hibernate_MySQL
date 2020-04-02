package beans;

import baza.HibernateUtil;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import tabele.Autobus;
import tabele.Kartica;
import tabele.Korisnik;
import tabele.MLinija;
import tabele.Rezervacija;
import tabele.Slika;
import tabele.Stanica;
import tabele.Vozac;
import tabele.ZahtevRezervacija;

@ManagedBean
@SessionScoped
public class KorisnikTabela {

    private Korisnik korisnik = (Korisnik) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("korisnik");

    private GostTabela gtabela = new GostTabela();  //enkapsuliramo GostTabela i koristimo njena polja i metode

    private List<GostRed> tabela = new ArrayList<>();   //ovo polje dohvata korisnik.xhtml

    {
        tabela = sveLinije();
    }

    private Integer odabranaLinija = null;      //ovo polje koriste DETALJI I REZERVISANJE

    private Autobus autobus;
    private Vozac vozac;
    private MapModel mapa;
    private List<Slika> galerija = new ArrayList<>();

    //Polja za komunikaciju sa bazom
    private Session session;
    private Transaction transaction;

    //---------------------------
    public KorisnikTabela() {

    }

    public List<GostRed> getTabela() {
        return tabela;
    }

    public GostTabela getGtabela() {
        return gtabela;
    }

    public Integer getOdabranaLinija() {
        return odabranaLinija;
    }

    public Autobus getAutobus() {
        return autobus;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public MapModel getMapa() {
        return mapa;
    }

    public List<Slika> getGalerija() {
        return galerija;
    }

    public void setTabela(List<GostRed> tabela) {
        this.tabela = tabela;
    }

    public void setGtabela(GostTabela gtabela) {
        this.gtabela = gtabela;
    }

    public void setOdabranaLinija(Integer odabranaLinija) {
        this.odabranaLinija = odabranaLinija;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public void setMapa(MapModel mapa) {
        this.mapa = mapa;
    }

    public void setGalerija(List<Slika> galerija) {
        this.galerija = galerija;
    }

    //--------------------------------
    public String pretraga() {

        gtabela.pretraga();
        this.tabela = gtabela.getTabela();
        return "";
    }

    public String detalji() {
        session = HibernateUtil.getSessionFactory().openSession();

        //autobus:
        Query q = session.createQuery("select ma.autobusId from MLinijaAutobus ma where ma.mlinijaId='" + odabranaLinija + "'");
        transaction = session.beginTransaction();
        Integer aid = (Integer) q.uniqueResult();
        transaction.commit();

        q = session.createQuery("from Autobus a where a.autobusId='" + aid + "'");
        transaction = session.beginTransaction();
        this.autobus = (Autobus) q.uniqueResult();
        transaction.commit();

        //--------SLIKE--------
        if (!(this.galerija.isEmpty())) {
            this.galerija = new ArrayList<>();
        }

        q = session.createQuery("select a.slikaId from AutobusSlika a where a.autobusId='" + aid + "'");
        transaction = session.beginTransaction();
        List<Integer> sids = q.list();
        transaction.commit();

        for (Integer sid : sids) {

            q = session.createQuery("from Slika s where s.slikaId='" + sid + "'");
            transaction = session.beginTransaction();
            Slika s = (Slika) q.uniqueResult();
            transaction.commit();

            this.galerija.add(s);

        }
        //----------------------
        //vozac:
        q = session.createQuery("select mv.vozacId from MLinijaVozac mv where mv.mlinijaId='" + odabranaLinija + "'");
        transaction = session.beginTransaction();
        Integer vid = (Integer) q.uniqueResult();
        transaction.commit();

        q = session.createQuery("from Vozac v where v.vozacId='" + vid + "'");
        transaction = session.beginTransaction();
        this.vozac = (Vozac) q.uniqueResult();
        transaction.commit();

        //---------MAPA---------
        napraviMapu(odabranaLinija);

        //---------------------
        return "detalji";
    }

    public String rezervisi() {
        session = HibernateUtil.getSessionFactory().openSession();

        Rezervacija r = new Rezervacija();
        r.setKorisnikId(korisnik.getKorisnikId());
        r.setMlinijaId(odabranaLinija);
        r.setStanje(0);

        transaction = session.beginTransaction();
        Integer rid = (Integer) session.save(r);
        transaction.commit();

        ZahtevRezervacija zr = new ZahtevRezervacija();
        zr.setRezervacijaId(rid);
        transaction = session.beginTransaction();
        session.save(zr);
        transaction.commit();

        session.close();

        return "";
    }

    public List<RezervacijaPrikaz> dohvatiRezervacije() {
        session = HibernateUtil.getSessionFactory().openSession();

        List<RezervacijaPrikaz> rps = new ArrayList<>();

        Query q = session.createQuery("from Rezervacija r where r.korisnikId='" + korisnik.getKorisnikId() + "'");
        transaction = session.beginTransaction();
        List<Rezervacija> rez = q.list();
        transaction.commit();

        for (Rezervacija r : rez) {

            q = session.createQuery("from MLinija m where m.mlinijaId='" + r.getMlinijaId() + "'");
            transaction = session.beginTransaction();
            MLinija m = (MLinija) q.uniqueResult();
            transaction.commit();
            
            Date datumVremeDolaska = new Date(m.getDatum().getTime() + m.getVremeDolaska().getTime());
            
            if (datumVremeDolaska.before(new Date())) {
                q = session.createQuery("delete from Rezervacija r where r.rezervacijaId='" + r.getRezervacijaId() + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();
                continue;
            }

            RezervacijaPrikaz rp = new RezervacijaPrikaz();
            rp.setLinija(m);
            rp.setRezervacija(r);

            rps.add(rp);

        }

        session.close();

        return rps;

    }

    public String ukloniRezervaciju(Rezervacija r) {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("delete from Rezervacija r where r.rezervacijaId='" + r.getRezervacijaId() + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        q = session.createQuery("delete from ZahtevRezervacija zr where zr.rezervacijaId='" + r.getRezervacijaId() + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        session.close();

        return "";
    }

    public String otkaziRezervaciju(RezervacijaPrikaz r) {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select m.datum from MLinija m where m.mlinijaId='" + r.getLinija().getMlinijaId() + "'");
        transaction = session.beginTransaction();
        Date datum = (Date) q.uniqueResult();
        transaction.commit();

        q = session.createQuery("select m.vremePolaska from MLinija m where m.mlinijaId='" + r.getLinija().getMlinijaId() + "'");
        transaction = session.beginTransaction();
        Date vreme = (Date) q.uniqueResult();
        transaction.commit();

        //hakujemo
        long ispravljamoBag = 3600000;
        Date vremePolaska = new Date(datum.getTime() + vreme.getTime() + ispravljamoBag);

        Date trenutnoVreme = new Date();

        if (trenutnoVreme.getTime() > vremePolaska.getTime() - 3600000) {
            FacesMessage poruka = new FacesMessage("Rezervaciju mozete otkazati najkasnije sat vremena pred polazak!");
            FacesContext.getCurrentInstance().addMessage("rezervacije", poruka);
            return "";
        }

        q = session.createQuery("delete from Rezervacija r where r.rezervacijaId='" + r.getRezervacija().getRezervacijaId() + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        FacesMessage poruka = new FacesMessage("Otkazali ste rezervaciju");
        FacesContext.getCurrentInstance().addMessage("rezervacije", poruka);

        return "";
    }

    //----------------------------------------------------------------------------------------------
    //-----------------------------------------------------------------------------------------------
    //Pomocne metode
    private List<GostRed> sveLinije() {
        List<GostRed> linije = new ArrayList<>();
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from MLinija");
        transaction = session.beginTransaction();
        List<MLinija> mlinije = q.list();
        transaction.commit();

        for (MLinija m : mlinije) {
            GostRed red = napraviRed(m);
            linije.add(red);
        }

        return linije;
    }

    public List<String> sveStanice() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select s.naziv from Stanica s");
        transaction = session.beginTransaction();
        List<String> sveStanice = q.list();
        transaction.commit();

        return sveStanice;
    }

    private GostRed napraviRed(MLinija m) {
        GostRed red = new GostRed();

        String medjustanice = medjustaniceString(m.getMlinijaId());

        String prevoznik = dohvatiPrevoznika(m.getMlinijaId());

        red.setMid(m.getMlinijaId());
        red.setPrevoznik(prevoznik);
        red.setDatum(m.getDatum());
        red.setVremePolaska(m.getVremePolaska());
        red.setMedjustanice(medjustanice);

        return red;
    }

    private String dohvatiPrevoznika(int mid) {
        String prevoznik = "";

        Query q = session.createQuery("select m.prevoznikId from MLinijaPrevoznik m where m.mlinijaId='" + mid + "'");
        transaction = session.beginTransaction();
        Integer pid = (Integer) q.uniqueResult();
        transaction.commit();

        q = session.createQuery("select p.naziv from Prevoznik p where p.prevoznikId='" + pid + "'");
        transaction = session.beginTransaction();
        prevoznik = (String) q.uniqueResult();
        transaction.commit();

        return prevoznik;
    }

    private String medjustaniceString(Integer mid) {
        Query q = session.createQuery("select m.mstanicaId from Medjustanica m"
                + " where m.mlinijaId='" + mid + "' order by redosled");
        transaction = session.beginTransaction();
        List<Integer> sids = q.list();
        transaction.commit();

        //nazivi stanica- ono sto nam treba i to sortirani zbog order by u prethodnom upitu
        List<String> stanice = new ArrayList<>();
        for (Integer sid : sids) {

            q = session.createQuery("select s.naziv from Stanica s where s.stanicaId='" + sid + "'");
            transaction = session.beginTransaction();
            String stanica = (String) q.uniqueResult();
            transaction.commit();

            stanice.add(stanica);

        }

        String mstanice = stanice.toString();
        mstanice = mstanice.substring(1, mstanice.length() - 1);
        return mstanice;
    }

    private List<Stanica> medjustaniceLista(Integer mid) {
        Query q = session.createQuery("select m.mstanicaId from Medjustanica m"
                + " where m.mlinijaId='" + mid + "' order by redosled");
        transaction = session.beginTransaction();
        List<Integer> sids = q.list();
        transaction.commit();

        //nazivi stanica- ono sto nam treba i to sortirani zbog order by u prethodnom upitu
        List<Stanica> stanice = new ArrayList<>();
        for (Integer sid : sids) {

            q = session.createQuery("from Stanica s where s.stanicaId='" + sid + "'");
            transaction = session.beginTransaction();
            Stanica stanica = (Stanica) q.uniqueResult();
            transaction.commit();

            stanice.add(stanica);

        }

        return stanice;

    }

    private void napraviMapu(Integer odabranaLinija) {
        this.mapa = new DefaultMapModel();

        List<Stanica> stanice = medjustaniceLista(odabranaLinija);

        int redosled = 0;
        for (Stanica s : stanice) {
            //dodajemo marker na mapu
            ++redosled;
            Marker marker = new Marker(new LatLng(s.getGsirina(), s.getGduzina()), "(" + redosled + ")" + s.getNaziv());
            this.mapa.addOverlay(marker);

        }

    }

    //----------------KARTICE--------------------
    private Kartica mesecna = null;
    private Kartica godisnja = null;

    public Kartica getMesecna() {
        return mesecna;
    }

    public void setMesecna(Kartica mesecna) {
        this.mesecna = mesecna;
    }

    public Kartica getGodisnja() {
        return godisnja;
    }

    public void setGodisnja(Kartica godisnja) {
        this.godisnja = godisnja;
    }

    public String vidiKartice() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from Kartica k where k.korisnikId='" + korisnik.getKorisnikId() + "'"
                + " and k.oznaka='m'");
        Transaction t = session.beginTransaction();
        this.mesecna = (Kartica) q.uniqueResult();
        t.commit();

        q = session.createQuery("from Kartica k where k.korisnikId='" + korisnik.getKorisnikId() + "'"
                + " and k.oznaka='g'");
        t = session.beginTransaction();
        this.godisnja = (Kartica) q.uniqueResult();
        t.commit();

        session.close();

        return "vidikartice";

    }

}
