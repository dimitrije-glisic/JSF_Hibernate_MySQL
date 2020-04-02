package beans;

import baza.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Kartica;
import tabele.Korisnik;
import tabele.ZahtevKartica;

@ManagedBean
@RequestScoped
public class KupovinaKartice {

    private Date vaziOd = null;
    private Date vaziDoMesecna = null;
    private Date vaziDoGodisnja = null;
    private double mesecnaCena;
    private double godisnjaCena;

    Korisnik korisnik = (Korisnik) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("korisnik");

    Kartica mesecna = null;
    Kartica godisnja = null;

    Session session = null;

    {
        session = HibernateUtil.getSessionFactory().openSession();

        Date trenutno = new Date();

        Query q = session.createQuery("from Kartica k where k.korisnikId='" + korisnik.getKorisnikId() + "'"
                + " and k.oznaka='m'");
        Transaction t = session.beginTransaction();
        Kartica m = (Kartica) q.uniqueResult();
        t.commit();

        if (m != null) {

            if (trenutno.before(m.getVaziDo())) {
                this.mesecna = m;
            }

        }

        q = session.createQuery("from Kartica k where k.korisnikId='" + korisnik.getKorisnikId() + "'"
                + " and k.oznaka='g'");
        t = session.beginTransaction();
        Kartica g = (Kartica) q.uniqueResult();
        t.commit();

        if (g != null) {

            if (trenutno.before(g.getVaziDo())) {
                this.godisnja = g;
            }

        }

        vaziOd = new Date();

        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 1);
        vaziDoMesecna = c.getTime();

        c = Calendar.getInstance();
        c.add(Calendar.YEAR, 1);
        vaziDoGodisnja = c.getTime();
        
        mesecnaCena = mesecnaCena();
        godisnjaCena = godisnjaCena();

        session.close();

    }

    //-------------------------------------------------------------------------------
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

    public Date vaziOd() {

        vaziOd = new Date();
        return vaziOd;
    }

    public Date vaziDoMesecna() {

        return vaziDoMesecna;
    }

    public Date vaziDoGodisnja() {

        return vaziDoGodisnja;
    }

    public double mesecnaCena() {
        int popust = 0;
        char kategorija = korisnik.getKategorija();
        switch (kategorija) {

            case 'n':
                popust = Admin.NEZAPOSLEN_POPUST;
                break;
            case 'z':
                popust = Admin.ZAPOSLEN_POPUST;
                break;
            case 's':
                popust = Admin.STUDENT_POPUST;
                break;
            case 'l':
                popust = Admin.LICE_SA_INVALIDITETOM_POPUST;
                break;
            case 'p':
                popust = Admin.PENZIONER_POPUST;
                break;
            default:
                popust = 0;

        }

        this.mesecnaCena = Admin.MESECNA_CENA;
        this.mesecnaCena -= popust / 100 * mesecnaCena;

        return mesecnaCena;
    }

    public double godisnjaCena() {
        int popust = 0;
        char kategorija = korisnik.getKategorija();
        switch (kategorija) {

            case 'n':
                popust = Admin.NEZAPOSLEN_POPUST;
                break;
            case 'z':
                popust = Admin.ZAPOSLEN_POPUST;
                break;
            case 's':
                popust = Admin.STUDENT_POPUST;
                break;
            case 'l':
                popust = Admin.LICE_SA_INVALIDITETOM_POPUST;
                break;
            case 'p':
                popust = Admin.PENZIONER_POPUST;
                break;
            default:
                popust = 0;

        }

        this.godisnjaCena = Admin.GODISNJA_CENA;
        this.godisnjaCena -= popust / 100 * godisnjaCena;

        return godisnjaCena;
    }

    public String kupiMesecnu() {
        Kartica kartica = new Kartica();

        kartica.setCena(mesecnaCena);
        kartica.setKorisnikId(korisnik.getKorisnikId());
        kartica.setOznaka('m');
        kartica.setVaziOd(vaziOd);
        kartica.setVaziDo(vaziDoMesecna);
        kartica.setStanje(0);

        session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        Integer karticaId = (Integer) session.save(kartica);
        transaction.commit();

        this.mesecna = kartica;

        ZahtevKartica zahtevKartica = new ZahtevKartica();
        zahtevKartica.setKarticaId(karticaId);

        transaction = session.beginTransaction();
        session.save(zahtevKartica);
        transaction.commit();

        session.close();

        return "";
    }

    public String kupiGodisnju() {
        Kartica kartica = new Kartica();

        kartica.setCena(godisnjaCena);
        kartica.setKorisnikId(korisnik.getKorisnikId());
        kartica.setOznaka('g');
        kartica.setVaziOd(vaziOd);
        kartica.setVaziDo(vaziDoGodisnja);
        kartica.setStanje(0);

        session = HibernateUtil.getSessionFactory().openSession();

        Transaction transaction = session.beginTransaction();
        Integer karticaId = (Integer) session.save(kartica);
        transaction.commit();

        this.godisnja = kartica;

        ZahtevKartica zahtevKartica = new ZahtevKartica();
        zahtevKartica.setKarticaId(karticaId);

        transaction = session.beginTransaction();
        session.save(zahtevKartica);
        transaction.commit();

        session.close();

        return "";
    }

    public String kupiKartice() {
        session = HibernateUtil.getSessionFactory().openSession();

        Date trenutno = new Date();

        Query q = session.createQuery("from Kartica k where k.korisnikId='" + korisnik.getKorisnikId() + "'"
                + " and k.oznaka='m'");
        Transaction t = session.beginTransaction();
        Kartica m = (Kartica) q.uniqueResult();
        t.commit();

        if (m != null) {

            if (trenutno.before(m.getVaziDo())) {
                this.mesecna = m;
            }

        }

        q = session.createQuery("from Kartica k where k.korisnikId='" + korisnik.getKorisnikId() + "'"
                + " and k.oznaka='g'");
        t = session.beginTransaction();
        Kartica g = (Kartica) q.uniqueResult();
        t.commit();

        if (g != null) {

            if (trenutno.before(g.getVaziDo())) {
                this.godisnja = g;
            }

        }

        session.close();

        return "kartice";
    }

}
