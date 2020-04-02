package beans;

import baza.HibernateUtil;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.OtkazanaLinija;


@ManagedBean
@RequestScoped
public class OtkazivanjeGLinija {

    private String otkazanaLinija;
    private Date otkazanaDo;
    
    private Session session;
    private Transaction transaction;

    //-------------------------------
    public String getOtkazanaLinija() {
        return otkazanaLinija;
    }

    public void setOtkazanaLinija(String otkazanaLinija) {
        this.otkazanaLinija = otkazanaLinija;
    }

    public Date getOtkazanaDo() {
        return otkazanaDo;
    }

    public void setOtkazanaDo(Date otkazanaDo) {
        this.otkazanaDo = otkazanaDo;
    }

    //--------------------------------------------
    public List<Integer> brojeviLinija() {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("select g.brojLinije from GLinija g where g.aktivna='1'");
        transaction = session.beginTransaction();
        List<Integer> brojevi = q.list();
        transaction.commit();

        session.close();

        return brojevi;

    }

    public String otkaziLiniju() {
        session = HibernateUtil.getSessionFactory().openSession();

        Integer brLinije = Integer.parseInt(otkazanaLinija);

        Query q = session.createQuery("select g.glinijaId from GLinija g where g.brojLinije='" + brLinije + "'");
        transaction = session.beginTransaction();
        Integer gid = (Integer) q.uniqueResult();
        transaction.commit();

        OtkazanaLinija otkazana = new OtkazanaLinija();
        otkazana.setGlinijaId(gid);
        otkazana.setOtkazanaDo(otkazanaDo);

        transaction = session.beginTransaction();
        session.save(otkazana);
        transaction.commit();

        q = session.createQuery("update GLinija g set g.aktivna='0' where g.glinijaId='" + gid + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        this.otkazanaLinija = null;
        this.otkazanaDo = null;

        session.close();

        return "";
    }

    public String aktivirajLiniju(OtkazanaPrikaz otkazana) {
        session = HibernateUtil.getSessionFactory().openSession();

        Query q = session.createQuery("delete from OtkazanaLinija o where o.glinijaId='" + otkazana.getGlinijaId() + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        q = session.createQuery("update GLinija g set g.aktivna='1' where g.glinijaId='" + otkazana.getGlinijaId() + "'");
        transaction = session.beginTransaction();
        q.executeUpdate();
        transaction.commit();

        session.close();

        FacesMessage poruka = new FacesMessage("Linija je aktivirana");
        FacesContext.getCurrentInstance().addMessage("otkazane", poruka);

        return "";

    }

}
