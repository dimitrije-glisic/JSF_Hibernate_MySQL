package beans;

import baza.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Korisnik;

@ManagedBean
@RequestScoped
public class ZahtevRegistracijaPrikaz {

    private Session session;
    private Transaction transaction;

    private List<Korisnik> zahtevi = new ArrayList<>();

    private Integer odobreno = null;
    private Integer odbijeno = null;

    //---------------------------------------------------------------
    public List<Korisnik> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<Korisnik> zahtevi) {
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

    public ZahtevRegistracijaPrikaz() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = null;

        Query q = session.createQuery("select zr.korisnikId from ZahtevRegistracija zr");

        transaction = session.beginTransaction();
        List<Integer> ids = q.list();
        transaction.commit();

        for (Integer id : ids) {
            q = session.createQuery("from Korisnik k where k.korisnikId='" + id + "'");
            transaction = session.beginTransaction();
            Korisnik korisnik = (Korisnik) q.uniqueResult();
            transaction.commit();

            this.zahtevi.add(korisnik);
        }

    }

    public String proveriZahteveReg() {
        session = HibernateUtil.getSessionFactory().openSession();
        transaction = null;

        Query q = session.createQuery("select zr.korisnikId from ZahtevRegistracija zr");

        transaction = session.beginTransaction();
        List<Integer> ids = q.list();
        transaction.commit();

        if (ids == null) {
            return "";
        }

        for (Integer id : ids) {
            boolean isRegOk = true;
            q = session.createQuery("from Korisnik k where k.korisnikId='" + id + "'");
            transaction = session.beginTransaction();
            Korisnik korisnik = (Korisnik) q.uniqueResult();
            transaction.commit();
            String korisnickoIme = korisnik.getKorisnickoIme();

            //PROVERA: postoji li neki korisnik u sistemu sa istim username-om:
            q = session.createQuery("from Korisnik  k where not k.korisnikId='" + id + "'");
            transaction = session.beginTransaction();
            List<Korisnik> korisnici = q.list();
            transaction.commit();

            if (korisnici != null) {
                for (Korisnik k : korisnici) {
                    if (k.getKorisnickoIme().equals(korisnickoIme)) {
                        if (k.getOdobren() == 1) {
                            //USERNAME VEC ZAUZETO - NE MOZE REGISTRACIJA                            
                            isRegOk = false;
                            odbijZahtevReg(id);
                            this.zahtevi.remove(korisnik);
                            break;
                        } else {
                            //MOZE REGISTRACIJA (KO PRVI DEVOJCI...), a ovaj leti napolje
                            int kid = k.getKorisnikId();
                            odbijZahtevReg(kid);
                            this.zahtevi.remove(korisnik);
                        }
                    }
                }
            }

            //REGISTRACIJA:
            if (isRegOk) {

                if (this.odobreno == null) {
                    this.odobreno = 1;
                } else {
                    this.odobreno++;
                }

                q = session.createQuery("update Korisnik k set k.odobren='1' where k.korisnikId='" + id + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();

                q = session.createQuery("delete from ZahtevRegistracija zr where zr.korisnikId='" + id + "'");
                transaction = session.beginTransaction();
                q.executeUpdate();
                transaction.commit();

            }
        }
        session.close();
        return "";
    }

    private void odbijZahtevReg(int id) {
        if (this.odbijeno == null) {
            this.odbijeno = 1;
        } else {
            this.odbijeno++;
        }

        Query q = session.createQuery("select ka.adresaId from KorisnikAdresa ka where ka.korisnikId='" + id + "'");
        transaction = session.beginTransaction();
        Integer adresaId = (Integer) q.uniqueResult();
        transaction.commit();

        q = session.createQuery("delete from Adresa a where a.adresaId='" + adresaId + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        //Nisam lepo uradio za tabelu korisnik_adresa ONDELETE CASCADE pa moram da brisem rucno:
        q = session.createQuery("delete from KorisnikAdresa a where a.adresaId='" + adresaId + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        //ovde moze da se postavi neka vrednost kao status na primer 2 koja ce da kaze da je 
        //zahtev odbijen to bi trebalo
        q = session.createQuery(
                "update Korisnik k set k.odobren='2'" + "where k.korisnikId='" + id + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        q = session.createQuery("delete from ZahtevRegistracija zr where zr.korisnikId='" + id + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

    }

}
