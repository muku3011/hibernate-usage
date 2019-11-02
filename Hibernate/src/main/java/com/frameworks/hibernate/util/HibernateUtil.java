package com.frameworks.hibernate.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    public static SessionFactory buildSessionFactory(List<Class> annotatedClasses) {
        try {
            Configuration configuration = new Configuration().configure("hibernate/hsqldb.cfg.xml");
            //Configuration configuration = new Configuration().configure("hibernate/h2.cfg.xml");
            for (Class aClass : annotatedClasses) {
                configuration.addAnnotatedClass(aClass);
            }

            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        // Close caches and connection pools
        sessionFactory.close();
    }

}