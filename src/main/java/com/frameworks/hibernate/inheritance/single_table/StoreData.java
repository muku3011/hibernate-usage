package com.frameworks.hibernate.inheritance.single_table;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreData {

    public static void main(String[] args) {

        Session session = HibernateUtil.buildSessionFactory(new ArrayList<Class>(Arrays.asList(Contract_Employee.class, Employee.class, Regular_Employee.class))).openSession();

        Transaction transaction = session.beginTransaction();

        Employee e1 = new Employee();
        e1.setName("sonoo");

        Regular_Employee e2 = new Regular_Employee();
        e2.setName("Vivek Kumar");
        e2.setSalary(50000);
        e2.setBonus(5);

        Contract_Employee e3 = new Contract_Employee();
        e3.setName("Arjun Kumar");
        e3.setPay_per_hour(1000);
        e3.setContract_duration("15 hours");

        session.persist(e1);
        session.persist(e2);
        session.persist(e3);

        transaction.commit();

        session.close();

        HibernateUtil.shutdown();
    }
}
