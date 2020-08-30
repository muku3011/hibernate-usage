package com.frameworks.hibernate.cache.query_level_cache;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;

public class QueryLevelCache {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<>(Collections.singletonList(Student.class)));

        Student student;

        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();

        Query query1 = session1.createQuery("from Student where USER_ID = 101");
        query1.setCacheable(true);
        student = (Student) query1.uniqueResult();
        System.out.println(student);

        session1.getTransaction().commit();
        session1.close();

        /*Closing first session and creating a new session*/

        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        Query query2 = session2.createQuery("from Student where USER_ID = 101");
        query2.setCacheable(true);
        student = (Student) query2.uniqueResult();
        System.out.println(student);

        session2.getTransaction().commit();
        session2.close();

        HibernateUtil.shutdown();
    }
}
