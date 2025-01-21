import entities.Autor;
import entities.Editorial;
import entities.Libro;
import entities.Tema;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//
//            //Objetos necesarios para crear un libro
//            Autor idAutor = session.get(Autor.class, 1);
//            Editorial idEditorial = session.get(Editorial.class, 1);
//            Tema idTema = session.get(Tema.class, 1);
//
//            //Creación de un libro
//            Libro libro1 = new Libro();
//            libro1.setIsbn("162983784-2837");
//            libro1.setTitulo("PRIMER LIBRO CON HIBERNATE");
//            libro1.setNumeroEjemplares((byte)40);
//            libro1.setIdAutor(idAutor);
//            libro1.setIdEditorial(idEditorial);
//            libro1.setIdTema(idTema);
//
//            //Guardar el objeto libro creado
//            session.persist(libro1);
//
//            //Guardar el libro en la base de datos
//            tx.commit();
//
//            System.out.println("Libro guardado con éxito");
//        }catch (Exception e) {
//            if (tx != null) {
//                System.out.println("Error al guardar el libro");
//                tx.rollback();
//            }
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//
//        sessionFactory.close();

        Query q = null;
        try {
            q = session.createQuery("from Autor");
            List<Autor> autores = q.list();
            //Obtenemos un iterador para recorrer la lista
            Iterator<Autor> iterator = autores.iterator();
            //Cantidad de autores
            System.out.println("Numero de Autores: " + autores.size());

            //recorremos
            while (iterator.hasNext()) {
                Autor autor = iterator.next();
                System.out.println(autor.getId()+" Nombre del autor: "+autor.getNombreAutor());
            }


        }catch (Exception e) {
            System.out.println("Error al realizar la consulta "+e.getMessage());
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
}
