/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import baza.HibernateUtil;
import static beans.Admin.MAX_STAJALISTA;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import tabele.GLinija;
import tabele.GLinijaRedVoznje;
import tabele.GLinijaStajaliste;
import tabele.GLinijaVozac;
import tabele.RedVoznje;

@ManagedBean
@RequestScoped
public class DodavanjeGLinija {

    private GLinija glinija;
    private List<String> medjustanice;
    private RedVoznje redVoznje;
    private List<String> vozaci;
    private String[] stajalista;

    private Session session;
    private Transaction transaction;

    //---------------------------------------
    public DodavanjeGLinija() {
        glinija = new GLinija();
        medjustanice = new ArrayList<>();
        vozaci = new ArrayList<>();
        redVoznje = new RedVoznje();
        stajalista = new String[MAX_STAJALISTA];

        {
            for (int i = 0; i < MAX_STAJALISTA; i++) {
                stajalista[i] = null;
            }
        }
    }

    public GLinija getGlinija() {
        return glinija;
    }

    public void setGlinija(GLinija glinija) {
        this.glinija = glinija;
    }

    public List<String> getMedjustanice() {
        return medjustanice;
    }

    public void setMedjustanice(List<String> medjustanice) {
        this.medjustanice = medjustanice;
    }

    public String[] getStajalista() {
        return stajalista;
    }

    public void setStajalista(String[] stajalista) {
        this.stajalista = stajalista;
    }

    public RedVoznje getRedVoznje() {
        return redVoznje;
    }

    public void setRedVoznje(RedVoznje redVoznje) {
        this.redVoznje = redVoznje;
    }

    public List<String> getVozaci() {
        return vozaci;
    }

    public void setVozaci(List<String> vozaci) {
        this.vozaci = vozaci;
    }

    //-------------------------------------------------------
    public String dodajGLiniju() {
        session = HibernateUtil.getSessionFactory().openSession();

        if (glinija.getBrojLinije() == 0) {
            FacesMessage poruka = new FacesMessage("Morate uneti broj linije!");
            FacesContext.getCurrentInstance().addMessage("dodavanje_glinije", poruka);
            return "";
        }

        if (glinija.getKrajnjeStajaliste() == null || glinija.getPolaznoStajaliste() == null) {
            FacesMessage poruka = new FacesMessage("Morate uneti i polaziste i odrediste!");
            FacesContext.getCurrentInstance().addMessage("dodavanje_glinije", poruka);
            return "";
        }

        if (redVoznje.getIntervaln() == null || redVoznje.getIntervalrd() == null || redVoznje.getIntervals() == null || redVoznje.getPredvidjenoVremeVoznje() == 0) {
            FacesMessage poruka = new FacesMessage("Morate popuniti sva polja za red voznje!");
            FacesContext.getCurrentInstance().addMessage("dodavanje_glinije", poruka);
            return "";
        }

        this.glinija.setAktivna(1);

        transaction = session.beginTransaction();
        Integer gid = (Integer) session.save(this.glinija);
        transaction.commit();

        //dodavanje medjustanica
        if (medjustanice == null) {
            medjustanice = new ArrayList<>();
        }

        medjustanice.add(this.glinija.getPolaznoStajaliste());

        for (int i = 0; i < MAX_STAJALISTA; i++) {

            if (stajalista[i] == null) {
                break;
            }

            medjustanice.add(stajalista[i]);

        }

        medjustanice.add(this.glinija.getKrajnjeStajaliste());

        int redosled = 0;
        for (String stajaliste : medjustanice) {

            ++redosled;
            Query q = session.createQuery("select s.stajalisteId from Stajaliste s where s.naziv='" + stajaliste + "'");
            transaction = session.beginTransaction();
            Integer sid = (Integer) q.uniqueResult();
            transaction.commit();

            GLinijaStajaliste gs = new GLinijaStajaliste();
            gs.setGlinijaId(gid);
            gs.setStajalisteId(sid);
            gs.setRedosled(redosled);

            transaction = session.beginTransaction();
            session.save(gs);
            transaction.commit();

        }

        if (!vozaci.isEmpty()) {

            for (String vid : vozaci) {
                int vidInt = Integer.parseInt(vid);

                GLinijaVozac gv = new GLinijaVozac();
                gv.setGlinijaId(gid);
                gv.setVozacId(vidInt);

                transaction = session.beginTransaction();
                session.save(gv);
                transaction.commit();

            }

        }

        //red voznje
        transaction = session.beginTransaction();
        Integer rid = (Integer) session.save(redVoznje);

        transaction.commit();

        GLinijaRedVoznje grv = new GLinijaRedVoznje();

        grv.setGlinijaId(gid);

        grv.setRedvoznjeId(rid);

        transaction = session.beginTransaction();

        session.save(grv);

        transaction.commit();

        glinija = new GLinija();
        medjustanice = new ArrayList<>();
        vozaci = new ArrayList<>();
        redVoznje = new RedVoznje();
        for (int i = 0; i < MAX_STAJALISTA; i++) {
            stajalista[i] = null;
        }

        session.close();

        FacesMessage poruka = new FacesMessage("Linija je dodata");
        FacesContext.getCurrentInstance().addMessage("dodavanje_glinija", poruka);

        return "";
    }

}
