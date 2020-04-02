package beans;

import baza.HibernateUtil;
import bean_helpers.KarticaPrikaz;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Kartica;
import tabele.Korisnik;
import tabele.ZahtevKartica;

@ManagedBean
@RequestScoped
public class ZahtevKarticaPrikaz {

    private List<KarticaPrikaz> zahtevi = new ArrayList<>();
    
    private Integer odobreno;
    private Integer odbijeno;
    
    private Session session;
    private Transaction transaction;
    
    //------------------------------------
    
     public List<KarticaPrikaz> getZahtevi() {
        return zahtevi;
    }

    public void setZahtevi(List<KarticaPrikaz> zahtevi) {
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
    
    
    

    //--------------------------------------
    public ZahtevKarticaPrikaz() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select k.karticaId from ZahtevKartica k");
        transaction = session.beginTransaction();
        List<Integer> kids = q.list();
        transaction.commit();
        
        if(kids.isEmpty()) {
            session.close();
            return;
        }
        
        q = session.createQuery("from Kartica k where k.karticaId in :kids");
        q.setParameterList("kids", kids);
        transaction = session.beginTransaction();
        List<Kartica> kartice = q.list();
        transaction.commit();

        for (Kartica k : kartice) {
            KarticaPrikaz prikaz = new KarticaPrikaz();

            q = session.createQuery("from Korisnik k where k.korisnikId='" + k.getKorisnikId() + "'");
            transaction = session.beginTransaction();
            Korisnik korisnik = (Korisnik) q.uniqueResult();
            transaction.commit();

            prikaz.setKorisnickoIme(korisnik.getKorisnickoIme());
            prikaz.setTipKartice(k.getOznaka());

            zahtevi.add(prikaz);
        }
        
        session.close();
        
    }

   
    public void proveriZahteveZaKartice() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("from ZahtevKartica");
        transaction = session.beginTransaction();
        List<ZahtevKartica> zahtevi = q.list();
        transaction.commit();

        for (ZahtevKartica z : zahtevi) {
            
            if(odobreno == null) {
                odobreno=1; 
            } else {
                odobreno++;
            }
            
            q = session.createQuery("select k.karticaId from Kartica k where k.karticaId='" + z.getKarticaId() + "'");
            transaction = session.beginTransaction();
            Integer kid = (Integer) q.uniqueResult();
            transaction.commit();

            q = session.createQuery("update Kartica k set k.stanje='1' where k.karticaId='" + kid + "'");
            transaction = session.beginTransaction();
            q.executeUpdate();
            transaction.commit();

            q = session.createQuery("delete from ZahtevKartica z where z.zkId='" + z.getZkId() + "'");
            transaction = session.beginTransaction();
            q.executeUpdate();
            transaction.commit();

        }
        
        zahtevi = new ArrayList<>();
        
        session.close();

    }

}
