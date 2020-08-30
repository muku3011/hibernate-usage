package com.frameworks.hibernate.self_join.many_to_many;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;

public class SelfJoinManyToMany {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<Class>(Collections.singletonList(Employee.class)));

        Session session = sessionFactory.openSession();
        session.beginTransaction();


        Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");
        Employee employee3 = new Employee("Marrisa", "Mayer");
        Employee employee4 = new Employee("Matt", "Cutts");

        employee1.getColleagues().add(employee3);
        employee1.getColleagues().add(employee4);
        employee2.getColleagues().add(employee4);
        employee3.getColleagues().add(employee4);
        employee4.getColleagues().add(employee1);
        employee4.getColleagues().add(employee3);


        session.save(employee1);
        session.save(employee2);
        session.save(employee3);
        session.save(employee4);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
