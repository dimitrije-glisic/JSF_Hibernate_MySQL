/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baza;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import tabele.*;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author MITA
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .addAnnotatedClass(Adresa.class)
                    .addAnnotatedClass(Korisnik.class)
                    .addAnnotatedClass(KorisnikAdresa.class)
                    .addAnnotatedClass(ZahtevRegistracija.class)
                    .addAnnotatedClass(Stanica.class)
                    .addAnnotatedClass(MLinija.class)
                    .addAnnotatedClass(Prevoznik.class)
                    .addAnnotatedClass(MLinijaPrevoznik.class)
                    .addAnnotatedClass(Medjustanica.class)
                    .addAnnotatedClass(Rezervacija.class)
                    .addAnnotatedClass(ZahtevRezervacija.class)
                    .addAnnotatedClass(MLinijaAutobus.class)
                    .addAnnotatedClass(Autobus.class)
                    .addAnnotatedClass(Vozac.class)
                    .addAnnotatedClass(MLinijaVozac.class)
                    .addAnnotatedClass(Slika.class)
                    .addAnnotatedClass(AutobusSlika.class)
                    .addAnnotatedClass(GLinija.class)
                    .addAnnotatedClass(Stajaliste.class)
                    .addAnnotatedClass(GLinijaStajaliste.class)
                    .addAnnotatedClass(GLinijaVozac.class)
                    .addAnnotatedClass(RedVoznje.class)
                    .addAnnotatedClass(GLinijaRedVoznje.class)
                    .addAnnotatedClass(Kartica.class)
                    .addAnnotatedClass(ZahtevKartica.class)
                    .addAnnotatedClass(OtkazanaLinija.class)
                    .configure()
                    .buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
