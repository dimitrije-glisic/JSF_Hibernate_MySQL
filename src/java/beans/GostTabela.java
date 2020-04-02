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
import tabele.MLinija;
import tabele.Stanica;

@ManagedBean
@ViewScoped
public class GostTabela {

    private Date datum = null;
    private String polaziste = null;
    private String odrediste = null;
    private String prevoznik = null;
    private Date polaziNakon = null;
    private Date polaziPre = null;

    //---------------------------------------
    private List<GostRed> tabela = new ArrayList<>();

    private FacesMessage message = null;

    //---------------------------------------
    private Session session = null;
    private Transaction transaction = null;

    //---------------------------------------
    public GostTabela() {

    }

    public List<GostRed> getTabela() {
        return tabela;
    }

    public void setTabela(List<GostRed> tabela) {
        this.tabela = tabela;
    }

    public Date getDatum() {
        return datum;
    }

    public String getPolaziste() {
        return polaziste;
    }

    public String getOdrediste() {
        return odrediste;
    }

    public String getPrevoznik() {
        return prevoznik;
    }

    public Date getPolaziNakon() {
        return polaziNakon;
    }

    public Date getPolaziPre() {
        return polaziPre;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public void setPolaziste(String polaziste) {
        this.polaziste = polaziste;
    }

    public void setOdrediste(String odrediste) {
        this.odrediste = odrediste;
    }

    public void setPrevoznik(String prevoznik) {
        this.prevoznik = prevoznik;
    }

    public void setPolaziNakon(Date polaziNakon) {
        this.polaziNakon = polaziNakon;
    }

    public void setPolaziPre(Date polaziPre) {
        this.polaziPre = polaziPre;
    }

    //---------------------------------------------
    public List<String> getStanice() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = null;

        Query q = session.createQuery("select s.naziv from Stanica s");
        transaction = session.beginTransaction();
        List<String> stanice = q.list();
        transaction.commit();

        session.close();

        return stanice;
    }

    public List<String> getPrevoznici() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = null;

        Query q = session.createQuery("select p.naziv from Prevoznik p");
        transaction = session.beginTransaction();
        List<String> prevoznici = q.list();
        transaction.commit();

        session.close();

        return prevoznici;
    }

    //-----------10 NAJSKORIJIH POLAZAKA------------
    //----------------------------------------------
    public List<MLinija> getNajskorijiPolasci() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = null;

        Query q = session.createQuery("from MLinija m order by m.datum, m.vremePolaska");
        q.setMaxResults(10);
        transaction = session.beginTransaction();
        List<MLinija> linije = q.list();
        transaction.commit();

        session.close();

        return linije;

    }

    //--------------------------------------------------
    public String pretraga() {

        session = HibernateUtil.getSessionFactory().openSession();

        if (!tabela.isEmpty()) {
            tabela = new ArrayList<>();
        }

        //-------------------
        if (datum == null) {
            message = new FacesMessage("Morate uneti datum polaska!");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
            return "";
        }

        if (polaziste == null && odrediste == null) {
            message = new FacesMessage("Unesite bar polaziste odnosno odrediste!");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
            return "";
        }

        if (polaziNakon != null && polaziPre != null) {
            if (polaziNakon.after(polaziPre)) {
                message = new FacesMessage("Interval polazaka(Polazi nakon - Polazi pre) nije u redu!");
                FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
                return "";
            }
        }

        //osnovna provera u redu - moze pretraga
        if (polaziste != null && odrediste != null) {

            polazisteOdredistePretraga();

        } else if (polaziste != null) {

            polazistePretraga();

        } else {

            odredistePretraga();

        }

        polaziste = odrediste = prevoznik = null;
        datum = null;
        polaziNakon = polaziPre = null;

        session.close();

        return "";

    }

    private void polazisteOdredistePretraga() {
        Query q = null;

        List<Integer> mids = zajednickiFilter();

        if (mids.isEmpty()) {
            message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);

            return;
        }

        //Za svaku liniju dohvatamo medjustanice i gledamo da li se unutar sekvence medjustanica
        //nalaze polaziste i odrediste
        for (Integer mid : mids) {

            List<String> stanice = medjustanice(mid);

            int indOdrediste = stanice.indexOf(odrediste);
            int indPolaziste = stanice.indexOf(polaziste);
            if ((indOdrediste > indPolaziste) && (indPolaziste != -1)) {
                //sada mozemo preci na formiranje reda tabele koja je rezultat pretrage:

                //Medjustanice:
                List<String> subStanice = stanice.subList(indPolaziste, indOdrediste + 1);
                String medjustanice = subStanice.toString();
                medjustanice = medjustanice.substring(1, medjustanice.length() - 1); //da izbrisem uglaste zagrade

                GostRed red = napraviUlazZaTabelu(mid, medjustanice, polaziste);

                //unutar dodajRed jos jedna provera:
                dodajRed(red);

            }

        }

        if (tabela.isEmpty()) {
            message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
        }
        return;
    }

    private void polazistePretraga() {

        Query q = null;

        List<Integer> mids = zajednickiFilter();

        if (mids.isEmpty()) {
            message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);

            return;
        }

        //Za svaku liniju dohvatamo medjustanice i gledamo da li se unutar sekvence medjustanica
        //nalazi polaziste i da li se nalazi na nekoj poziciji koja nije krajnja
        for (Integer mid : mids) {

            List<String> stanice = medjustanice(mid);

            if (stanice.contains(polaziste)) {

                List<String> subStanice = stanice.subList(stanice.indexOf(polaziste), stanice.size());

                if (subStanice.size() > 1) {

                    String medjustanice = subStanice.toString();
                    medjustanice = medjustanice.substring(1, medjustanice.length() - 1);

                    GostRed red = napraviUlazZaTabelu(mid, medjustanice, polaziste);

                    dodajRed(red);

                }
            }
        }

        if (tabela.isEmpty()) {
            message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
        }
        return;
    }

    private void odredistePretraga() {

        Query q = null;

        List<Integer> mids = zajednickiFilter();

        if (mids.isEmpty()) {
            message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);

            return;
        }

        //Za svaku liniju dohvatamo medjustanice i gledamo da li se unutar sekvence medjustanica
        //nalazi odrediste i da li se nalazi na ne-prvoj poziciji
        for (Integer mid : mids) {

            List<String> stanice = medjustanice(mid);

            if (stanice.contains(odrediste)) {

                List<String> subStanice = stanice.subList(0, stanice.indexOf(odrediste) + 1);

                if (subStanice.size() > 1) {

                    String medjustanice = subStanice.toString();
                    medjustanice = medjustanice.substring(1, medjustanice.length() - 1);

                    GostRed red = napraviUlazZaTabelu(mid, medjustanice, stanice.get(0));

                    dodajRed(red);

                }
            }
        }

        if (tabela.isEmpty()) {
            message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
            FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
        }
        return;
    }

    private List<Integer> zajednickiFilter() {
        // "GRADIMO" upit 

        //ovaj filter je obavezan
        String query = "select m.mlinijaId from MLinija m where m.datum=:datum";
        Query q = null;

        //proizvoljan filter : prevoznik
        List<Integer> ids = null;
        if (prevoznik != null) {

            q = session.createQuery("select p.prevoznikId from Prevoznik p where p.naziv='" + prevoznik + "'");
            transaction = session.beginTransaction();
            Integer id = (Integer) q.uniqueResult();
            transaction.commit();

            if (id == null) {
                message = new FacesMessage("Ovo ne bi trebalo da se desi");
                FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
                return null;
            }

            q = session.createQuery("select m.mlinijaId from MLinijaPrevoznik m where m.prevoznikId='" + id + "'");
            transaction = session.beginTransaction();
            ids = q.list();
            transaction.commit();
            if (ids.isEmpty()) {
                message = new FacesMessage("Nema takvih linija: ponovite pretragu sa drugim parametrima");
                FacesContext.getCurrentInstance().addMessage("pretragaForma", message);
                return null;
            }

            query += " and m.mlinijaId in (:ids)";
        }

        
        //UPIT JE FORMIRAN
        q = session.createQuery(query);
        q.setParameter("datum", datum);

        if (prevoznik != null) {
            q.setParameterList("ids", ids);
        }

        transaction = session.beginTransaction();
        List<Integer> mids = q.list();
        transaction.commit();

        return mids;
    }

    protected List<String> medjustanice(Integer mid) {
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

        return stanice;
    }

    private GostRed napraviUlazZaTabelu(Integer mid, String medjustanice, String stanicaPolazak) {
        Query q = null;

        //Vreme polaska:
        Time vremePolaska = null;
        Integer stanicaId = null;
        q = session.createQuery("select s.stanicaId from Stanica s where s.naziv='" + stanicaPolazak + "'");
        transaction = session.beginTransaction();
        stanicaId = (Integer) q.uniqueResult();
        transaction.commit();

        q = session.createQuery("select m.vremeDolaska from Medjustanica m where"
                + " m.mlinijaId='" + mid + "'" + " and m.mstanicaId='" + stanicaId + "'");
        transaction = session.beginTransaction();
        vremePolaska = (Time) q.uniqueResult();
        transaction.commit();

        //Prevoznik - ako vec neki nije izabran kao filter za pretragu:
        if (prevoznik == null) {

            q = session.createQuery("select m.prevoznikId from MLinijaPrevoznik m where m.mlinijaId='" + mid + "'");
            transaction = session.beginTransaction();
            Integer pid = (Integer) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("select p.naziv from Prevoznik p where p.prevoznikId='" + pid + "'");
            transaction = session.beginTransaction();
            prevoznik = (String) q.uniqueResult();
            transaction.commit();
        }

        //Formiranje ulaza u tabelu koja predstavlja rezultat pretrage:
        Date vreme = new Date(vremePolaska.getTime());  //zbog lakseg ispisa(konvertovanja) i tako to...
        return new GostRed(mid, prevoznik, datum, vreme, medjustanice);

    }

    private void dodajRed(GostRed red) {
        Date vreme = red.getVremePolaska();

        if (polaziNakon != null && polaziPre != null) {
            if (polaziNakon.getTime() <= vreme.getTime() && polaziPre.getTime() >= vreme.getTime()) {
                tabela.add(red);
                return;
            } else {
                return;
            }

        } else if (polaziNakon != null) {
            if (polaziNakon.getTime() <= vreme.getTime()) {
                tabela.add(red);
                return;
            } else {
                return;
            }

        } else if (polaziPre != null) {
            if (polaziPre.getTime() >= vreme.getTime()) {
                tabela.add(red);
                return;
            } else {
                return;
            }

        }

        tabela.add(red);

    }

}
