package util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.HibernateException;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    // Bloque estático para inicializar el SessionFactory

    static {
        try {
            // Cargar la configuración desde hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (HibernateException ex) {
            // En caso de error, lanzar una excepción
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    // Metodo para obtener la instancia de SessionFactory

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // Metodo para cerrar el SessionFactory

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}

