package com.frameworks.hibernate.cache.second_level_cache;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;

public class SecondLevelCache {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<Class>(Collections.singletonList(Employee.class)));

        Employee employee;

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        employee = (Employee) session1.get(Employee.class, 201);
        System.out.println(employee);

        session1.getTransaction().commit();
        session1.close();

        /*Closing first session and creating a new session*/

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        employee = (Employee) session2.get(Employee.class, 201);
        System.out.println(employee);

        session2.getTransaction().commit();
        session2.close();

        HibernateUtil.shutdown();
    }
}
