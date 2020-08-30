package com.frameworks.hibernate.self_join.one_to_many;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;

public class SelfJoinOneToMany {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<Class>(Collections.singletonList(Employee.class)));

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Employee manager1 = new Employee("Chuck", "Norris");

        Employee employee1 = new Employee("Sergey", "Brin");
        Employee employee2 = new Employee("Larry", "Page");

        employee1.setManager(manager1);
        employee2.setManager(manager1);

        session.save(employee1);
        session.save(employee2);

        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
