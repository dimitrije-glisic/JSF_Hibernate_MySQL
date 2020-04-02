package beans;

import baza.HibernateUtil;
import bean_helpers.RezPrikaz;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Korisnik;
import tabele.Rezervacija;
import tabele.ZahtevRezervacija;

@ManagedBean
@RequestScoped
public class ZahtevRezervacijaPrikaz {

    private RezPrikaz prikaz = new RezPrikaz();

    private List<RezPrikaz> zahtevi = new ArrayList<>();

    private Integer odobreno = null;
    private Integer odbijeno = null;

    private Session session = null;
    private Transaction transaction = null;

    //---------------------------------------------------------------------------
    public List<RezPrikaz> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<RezPrikaz> zahtevi) {
        this.zahtevi = zahtevi;
    }

    public Integer getOdobreno() {
        return odobreno;
    }

    public void setOdobreno(Integer odobreno) {
        this.odobreno = odobreno;
    }

    public Integer getOdbijeno() {
        return odbijeno;
    }

    public void setOdbijeno(Integer odbijeno) {
        this.odbijeno = odbijeno;
    }

    //------------------------------------------------------
    public ZahtevRezervacijaPrikaz() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select zr.rezervacijaId from ZahtevRezervacija zr");
        transaction = session.beginTransaction();
        List<Integer> rids = q.list();
        transaction.commit();

        if (rids.isEmpty()) {
            session.close();
            return;
        }

        q = session.createQuery("from Rezervacija r where r.rezervacijaId in :rids");
        q.setParameterList("rids", rids);
        transaction = session.beginTransaction();
        List<Rezervacija> rezervacije = q.list();
        transaction.commit();

        for (Rezervacija r : rezervacije) {
            RezPrikaz p = new RezPrikaz();

            q = session.createQuery("from Korisnik k where k.korisnikId='" + r.getKorisnikId() + "'");
            transaction = session.beginTransaction();
            Korisnik k = (Korisnik) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("select count(r) from Rezervacija r where r.mlinijaId='" + r.getMlinijaId() + "' and"
                    + " r.stanje='1'");
            transaction = session.beginTransaction();
            Long zauzeto = (Long) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("select ma.autobusId from MLinijaAutobus ma where ma.mlinijaId='" + r.getMlinijaId() + "'");
            transaction = session.beginTransaction();
            Integer aid = (Integer) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("select a.brojSedista from Autobus a where a.autobusId='" + aid + "'");
            transaction = session.beginTransaction();
            Integer brojSedista = (Integer) q.uniqueResult();
            transaction.commit();

            p.setKorisnickoIme(k.getKorisnickoIme());
            p.setLinija(r.getMlinijaId());
            p.setSlobodno(brojSedista - zauzeto.intValue());

            zahtevi.add(p);

        }

    }

    public void proveriZahteveRez() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from ZahtevRezervacija");
        transaction = session.beginTransaction();
        List<ZahtevRezervacija> zrs = q.list();
        transaction.commit();

        for (ZahtevRezervacija zr : zrs) {

            q = session.createQuery("from Rezervacija r where r.rezervacijaId='" + zr.getRezervacijaId() + "'");
            transaction = session.beginTransaction();
            Rezervacija r = (Rezervacija) q.uniqueResult();
            transaction.commit();

            // provera uslova :
            // 1) da li je korisnik vec napravio 3 rezervacije:
            Integer korisnik = r.getKorisnikId();
            q = session.createQuery("select count(r) from Rezervacija r where r.korisnikId='" + korisnik + "' and"
                    + " r.stanje='1'");
            transaction = session.beginTransaction();
            Long brojRezervacija = (Long) q.uniqueResult();
            transaction.commit();

            if (brojRezervacija == Admin.MAX_REZERVACIJA) {
                if (odbijeno == null) {
                    odbijeno = 1;
                } else {
                    odbijeno++;
                }
                q = session.createQuery("update Rezervacija r set r.stanje='2' where r.rezervacijaId='" + r.getRezervacijaId() + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();

                q = session.createQuery("delete from ZahtevRezervacija z where z.zrId='" + zr.getZrId() + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();

                continue;

            }

            //provera zauzeto < brSedista
            q = session.createQuery("select count(r) from Rezervacija r where r.mlinijaId='" + r.getMlinijaId() + "' and"
                    + " r.stanje='1'");
            transaction = session.beginTransaction();
            Long zauzeto = (Long) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("select ma.autobusId from MLinijaAutobus ma where ma.mlinijaId='" + r.getMlinijaId() + "'");
            transaction = session.beginTransaction();
            Integer aid = (Integer) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("select a.brojSedista from Autobus a where a.autobusId='" + aid + "'");
            transaction = session.beginTransaction();
            Integer brojSedista = (Integer) q.uniqueResult();
            transaction.commit();

            if (zauzeto.equals(brojSedista)) {
                if (odbijeno == null) {
                    odbijeno = 1;
                } else {
                    odbijeno++;
                }
                q = session.createQuery("update Rezervacija r set r.stanje='3' where r.rezervacijaId='" + r.getRezervacijaId() + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();

                q = session.createQuery("delete from ZahtevRezervacija z where z.zrId='" + zr.getZrId() + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();

                continue;

            }

            // zahtev je prosao uslove - odobri
            if (odobreno == null) {
                odobreno = 1;
            } else {
                odobreno++;
            }

            q = session.createQuery("update Rezervacija r set r.stanje='1' where r.rezervacijaId='" + r.getRezervacijaId() + "'");
            transaction = session.beginTransaction();
            q.executeUpdate();
            transaction.commit();

            q = session.createQuery("delete from ZahtevRezervacija z where z.zrId='" + zr.getZrId() + "'");
            transaction = session.beginTransaction();
            q.executeUpdate();
            transaction.commit();

        }
        
        this.zahtevi = new ArrayList<>();

        session.close();

    }

}
