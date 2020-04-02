package beans;

import baza.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
import tabele.Autobus;
import tabele.AutobusSlika;
import tabele.Slika;

@ManagedBean
@ViewScoped
public class DodavanjeAutobus {

    private Autobus autobus = new Autobus();
    private List<String> slike = new ArrayList<>();

    private Session session;
    private Transaction transaction;

    //------------------------------
    public Autobus getAutobus() {
        return autobus;
    }

    public void setAutobus(Autobus autobus) {
        this.autobus = autobus;
    }

    public List<String> getSlike() {
        return slike;
    }

    public void setSlike(List<String> slike) {
        this.slike = slike;
    }

    //--------------------------------------------------
    public void upload(FileUploadEvent event) {
        if (slike.size() == 5) {
            FacesMessage poruka = new FacesMessage("Ne mozete vise da dodajete fotografije:"
                    + "jedan autobus moze imati najvise 5 fotografija!");
            FacesContext.getCurrentInstance().addMessage("upload", poruka);
            return;
        }

        UploadedFile f = event.getFile();
        String naziv = f.getFileName();
        slike.add(naziv);
    }

    public void dodajAutobus() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query q = null;

        transaction = session.beginTransaction();
        Integer aid = (Integer) session.save(this.autobus);
        transaction.commit();

        if (!slike.isEmpty()) {

            for (String s : slike) {
                transaction = session.beginTransaction();
                q = session.createQuery("select count(s) from Slika s where s.naziv='" + s + "'");
                Long cnt = (Long) q.uniqueResult();
                transaction.commit();

                if (cnt == 0) {

                    Slika slika = new Slika();
                    slika.setNaziv(s);
                    transaction = session.beginTransaction();
                    session.save(slika);
                    transaction.commit();

                }

                q = session.createQuery("select s.slikaId from Slika s where s.naziv='" + s + "'");
                transaction = session.beginTransaction();
                Integer sid = (Integer) q.uniqueResult();
                transaction.commit();

                AutobusSlika as = new AutobusSlika();
                as.setAutobusId(aid);
                as.setSlikaId(sid);
                transaction = session.beginTransaction();
                session.save(as);
                transaction.commit();

            }
        }
        
        FacesMessage poruka = new FacesMessage("Autobus je dodat");
        FacesContext.getCurrentInstance().addMessage("dodavanje_autobus", poruka);
        
        autobus = new Autobus();
        slike= new ArrayList<>();
        
        session.close();

    }

}
