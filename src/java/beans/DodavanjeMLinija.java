package beans;

import baza.HibernateUtil;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FlowEvent;
import tabele.Autobus;
import tabele.MLinija;
import tabele.MLinijaAutobus;
import tabele.MLinijaPrevoznik;
import tabele.MLinijaVozac;
import tabele.Medjustanica;
import tabele.Vozac;

@ManagedBean
@ViewScoped
public class DodavanjeMLinija {

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

    private Session session;
    private Transaction transaction;

    //-----------------------------------
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

    //---------------------------------------------------------------------
    public String dodajMLiniju() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q;

        //MLinija
        MLinija mlinija = new MLinija();
        mlinija.setPolaziste(polaziste);
        mlinija.setOdrediste(odrediste);
        mlinija.setDatum(datum);
        mlinija.setVremePolaska(vremePolaska);
        mlinija.setVremeDolaska(vremeDolaska);

        transaction = session.beginTransaction();
        Integer mid = (Integer) session.save(mlinija);
        transaction.commit();

        //MLinijaPrevoznik
        MLinijaPrevoznik linijaPrevoznik = new MLinijaPrevoznik();
        linijaPrevoznik.setMlinijaId(mid);
        //Prevoznik
        q = session.createQuery("select p.prevoznikId from Prevoznik p where p.naziv='" + prevoznik + "'");
        transaction = session.beginTransaction();
        Integer pid = (Integer) q.uniqueResult();
        transaction.commit();

        linijaPrevoznik.setPrevoznikId(pid);
        transaction = session.beginTransaction();
        session.save(linijaPrevoznik);
        transaction.commit();

        //Medjustanice:
        Query qstanica = session.createQuery("select s.stanicaId from Stanica s where s.naziv = :stanica");

        qstanica.setParameter("stanica", polaziste);
        transaction = session.beginTransaction();
        Integer sid = (Integer) qstanica.uniqueResult();
        transaction.commit();

        //Polaziste
        Medjustanica medjustanica = new Medjustanica();
        medjustanica.setMlinijaId(mid);
        medjustanica.setMstanicaId(sid);
        medjustanica.setVremeDolaska(vremePolaska);
        medjustanica.setRedosled(1);

        transaction = session.beginTransaction();
        session.save(medjustanica);
        transaction.commit();

        int i;
        for (i = 0; i < Admin.MAX_STAJALISTA; i++) {
            if (medjustanice_M[i] != null) {

                qstanica.setParameter("stanica", medjustanice_M[i]);
                transaction = session.beginTransaction();
                sid = (Integer) qstanica.uniqueResult();
                transaction.commit();

                medjustanica = new Medjustanica();
                medjustanica.setMlinijaId(mid);
                medjustanica.setMstanicaId(sid);
                medjustanica.setVremeDolaska(vremenaDolazaka[i]);
                medjustanica.setRedosled(i + 2);

                transaction = session.beginTransaction();
                session.save(medjustanica);
                transaction.commit();

            } else {
                break;
            }

        }

        //Odrediste
        qstanica.setParameter("stanica", odrediste);
        transaction = session.beginTransaction();
        sid = (Integer) qstanica.uniqueResult();
        transaction.commit();

        medjustanica = new Medjustanica();
        medjustanica.setMlinijaId(mid);
        medjustanica.setMstanicaId(sid);
        medjustanica.setVremeDolaska(vremeDolaska);
        medjustanica.setRedosled(i + 2);

        transaction = session.beginTransaction();
        session.save(medjustanica);
        transaction.commit();

        //MLinijaAutobus/Vozac
        if (autobus_M != null) {

            MLinijaAutobus linijaAutobus = new MLinijaAutobus();
            linijaAutobus.setMlinijaId(mid);
            linijaAutobus.setAutobusId(Integer.parseInt(autobus_M));

            transaction = session.beginTransaction();
            session.save(linijaAutobus);
            transaction.commit();

        }

        if (vozac_M != null) {

            MLinijaVozac linijaVozac = new MLinijaVozac();
            linijaVozac.setMlinijaId(mid);
            linijaVozac.setVozacId(Integer.parseInt(vozac_M));

            transaction = session.beginTransaction();
            session.save(linijaVozac);
            transaction.commit();

        }

        prevoznik = polaziste = odrediste = null;
        for (i = 0; i < Admin.MAX_STAJALISTA; i++) {
            medjustanice_M[i] = null;
            vremenaDolazaka[i] = null;
        }
        vremePolaska = vremeDolaska = datum = null;
        autobus_M = autobusString = vozac_M = vozacString = null;

        return "";
    }

    public String onFlowProcess(FlowEvent event) {
        return event.getNewStep();
    }

}
