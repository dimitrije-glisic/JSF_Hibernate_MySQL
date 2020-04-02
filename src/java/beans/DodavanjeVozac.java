package beans;

import baza.HibernateUtil;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.Vozac;


@ManagedBean
@RequestScoped
public class DodavanjeVozac {
    
    private Vozac vozac = new Vozac();
    
    private Session session;
    private Transaction transaction;
    
    //--------------------------------------
    
    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public String dodajVozaca() {
        session = HibernateUtil.getSessionFactory().openSession();

        transaction = session.beginTransaction();
        session.save(this.vozac);
        transaction.commit();

        session.close();

        FacesMessage poruka = new FacesMessage("Vozac je dodat");
        FacesContext.getCurrentInstance().addMessage("dodavanje_vozac", poruka);

        vozac = new Vozac();

        return "";
    }
    
}
