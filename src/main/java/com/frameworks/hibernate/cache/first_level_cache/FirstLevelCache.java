package com.frameworks.hibernate.cache.first_level_cache;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;

public class FirstLevelCache {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<>(Collections.singletonList(Student.class)));

        Student student;

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        student = (Student) session1.get(Student.class, 101);
        System.out.println(student);

        session1.getTransaction().commit();
        session1.close();

        /*Closing first session and creating a new session*/

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        student = (Student) session2.get(Student.class, 101);
        System.out.println(student);

        session2.getTransaction().commit();
        session2.close();

        HibernateUtil.shutdown();
    }
}
