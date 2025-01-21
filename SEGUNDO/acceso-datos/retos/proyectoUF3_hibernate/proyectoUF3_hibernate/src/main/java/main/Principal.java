package main;

import entities.Autor;
import entities.Editorial;
import entities.Libro;
import entities.Tema;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class Principal {
    public static void main(String[] args) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session sesion = sf.openSession();

        //Muestra el ISBN, el título y el nombre del autor de todos los libros que hay.
        String hql = "SELECT l, a FROM Libro l JOIN l.idAutor a";
        Query q = sesion.createQuery(hql);
        List lista = q.list();
        Iterator iter = lista.iterator();
        while(iter.hasNext()){
            Object[] partes = (Object[]) iter.next();
            Libro l = (Libro) partes[0];
            Autor a = (Autor) partes[1];
            System.out.println("ISBN: "+l.getIsbn()+" Titulo: "+l.getTitulo()+" Autor: "+a.getNombreAutor());
        }

        //Muestra toda la información de los libros, autores y editoriales para el usuario final, es decir, que no aparezca ninguna id de ninguna tabla.
        String hql1 = "SELECT l, a, e, t\n" +
                "FROM Libro l\n" +
                "JOIN l.idAutor a\n" +
                "JOIN l.idEditorial e\n" +
                "JOIN l.idTema t";
        Query q1 = sesion.createQuery(hql1);
        List lista1 = q1.list();
        Iterator iter1 = lista1.iterator();
        while(iter1.hasNext()){
            Object[] partes = (Object[]) iter1.next();
            Libro l = (Libro) partes[0];
            Autor a = (Autor) partes[1];
            Editorial e = (Editorial) partes[2];
            Tema t = (Tema) partes[3];
            System.out.println("ISBN: "+l.getIsbn()+" Titulo: "+l.getTitulo()+" Numero ejemplares "
                    +l.getNumeroEjemplares() +" Autor: "+a.getNombreAutor()
                    +" Editorial: "+e.getNombreEditorial()+" Tema: "+t.getNombreTema());
            System.out.println("================================================");
        }

        //Recibe datos del usuario (lo puedes poner en una variable, no tienes porque pedirlo... )
        // y realiza una consulta para saber toda la información de ese libro como en el punto anterior.
        String isbn = "3456789212";
        String hql2 = "SELECT l, a, e, t FROM Libro l " +
                "JOIN l.idAutor a JOIN l.idEditorial e JOIN l.idTema t WHERE l.isbn = :isbn";
        Query q2 = sesion.createQuery(hql2);
        q2.setParameter("isbn", isbn);
        List lista2 = q2.list();
        Iterator iter2 = lista2.iterator();
        while (iter2.hasNext()){
            Object[] partes = (Object[]) iter2.next();
            Libro l = (Libro) partes[0];
            Autor a = (Autor) partes[1];
            Editorial e = (Editorial) partes[2];
            Tema t = (Tema) partes[3];
            System.out.println("ISBN: "+l.getIsbn()+" Titulo: "+l.getTitulo()+" Numero ejemplares "
                    +l.getNumeroEjemplares() +" Autor: "+a.getNombreAutor()
                    +" Editorial: "+e.getNombreEditorial()+" Tema: "+t.getNombreTema());
        }

        // Ejercicio uniqueResult()
        String sqlResult = "select avg(li.numeroEjemplares) from Libro as li";
        Query q3 = sesion.createQuery(sqlResult);
        Double result = (Double) q3.uniqueResult();
        System.out.println("La media de numero de ejemplares es: " + result);
        sesion.close();
        sf.close();
    }
}
