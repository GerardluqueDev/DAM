package main;

import entities.Departamento;
import entities.Empleado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        String hql = "from Departamento";
        Query q = session.createQuery(hql);
        //Lo recorremos cargando todo en memoria de java
        //Hacemos una única petición a la BD

        List<Departamento> list = q.list();
        Iterator<Departamento> iter = list.iterator();

        System.out.println("----- NOMBRE DE LOS DEPARTAMENTOS con LIST-----");
        while (iter.hasNext()) {
            Departamento d = (Departamento) iter.next();
            System.out.println(d.getDnombre());
        }
        //Hacemos multiples peticiones
        System.out.println("----- CON ITERATE -----");
        String hql2 = "from Departamento";
        Query q2 = session.createQuery(hql2);
        List list2 = q2.list();
        Iterator iter2 = list2.iterator();
        while (iter2.hasNext()) {
            Departamento d = (Departamento) iter2.next();
            System.out.println(d.getDnombre());
        }

        // Realiza una consulta
        System.out.println("----- EMPLEADOS DEL DEPARTAMENTO 2O -----");
        String hql3 = "from Empleado as e where e.deptNo.id=20";
        Query q3 = session.createQuery(hql3);
        List list3 = q3.list();
        Iterator iter3 = list3.iterator();
        while (iter3.hasNext()) {
            Empleado e = (Empleado) iter3.next();
            System.out.println(e.getApellido() + " trabaja de " + e.getOficio() + " y tiene un salario de " + e.getSalario());
        }

        //HIBERNATE NOS PERMITE METERLO TODO EN ARRAYS DE
        // TIPO OBJECT DE TAL MANERA QUE SI QUEREMOS SACARA MUCHOS
        // DATOS DE CLASES DISTINTAS COMO UN INNER JOIN
        //PARA ESO TENEMOS QUE METERLO TODO EN OBJETOS

        //Ahora voy a meter en dos objetos un inner join de dos clases: OBJECT
        System.out.println(" ----- JOIN -----");
        String hql4 = "from Empleado as e, Departamento d where e.deptNo.id = d.id order by apellido";
        Query q4 = session.createQuery(hql4);
        List list4 = q4.list();
        Iterator iter4 = list4.iterator();
        while (iter4.hasNext()) {
            // al tenerlo en objetos, podemos sacar de cada objeto lo que nos interese
            Object[] partes = (Object[]) iter4.next();
            Empleado e = (Empleado) partes[0];
            Departamento d = (Departamento) partes[1];
            System.out.println(e.getApellido() + " del departamento " + d.getDnombre());

        }

        //Cuando solo vayamos a tener un resultado utilizamos uniqueResult()
        System.out.println(" ----- UNIQUERESULT() Si se nos dice que solo hay un registro en la salida-----");
        String hql5 = "select avg(em.salario) from Empleado as em";
        Query q5 = session.createQuery(hql5);
        Double media = (Double) q5.uniqueResult();
        System.out.println("La media de salario es: " + media + "€");

        //SENTENCIAS PARAMETRIZADAS:
        // OJO si se nos pide que el usuario meta algo, tiene que ser siempre parametrizado
        System.out.println("----- CONSULTA PARAMETRIZADA -----");
        String hql6 = "from Empleado e where e.deptNo.id = :numDep and e.oficio = :ofi";

        Query q6 = session.createQuery(hql6);
        q6.setParameter("numDep", (byte) 30);
        q6.setParameter("ofi", "vendedor");
        List list6 = q6.list();
        Iterator iter6 = list6.iterator();
        while (iter6.hasNext()) {
            Empleado e = (Empleado) iter6.next();
            System.out.println(e.getApellido());
        }

        session.close();
        sf.close();
    }
}
