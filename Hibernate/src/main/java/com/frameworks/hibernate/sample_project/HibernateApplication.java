package com.frameworks.hibernate.sample_project;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class HibernateApplication {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<>(Collections.singletonList(DatabaseUser.class)));

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        DatabaseUser user = new DatabaseUser();

        // user.setUserId(1);
        user.setUsername("demo");
        user.setCreatedBy("demo");
        user.setCreatedDate(new Date());

        session.save(user);
        session.getTransaction().commit();
        session.close();

        HibernateUtil.shutdown();
    }
}
