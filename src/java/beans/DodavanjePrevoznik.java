package beans;

import baza.HibernateUtil;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tabele.Prevoznik;

@ManagedBean
@ViewScoped
public class DodavanjePrevoznik {

    private Prevoznik p = new Prevoznik();
    private String slika;
    
    private Session session;
    private Transaction transaction;
    
    //----------------------------------------------------
    //----------------------------------------------------
    
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
