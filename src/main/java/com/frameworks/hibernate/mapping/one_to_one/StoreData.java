package com.frameworks.hibernate.mapping.one_to_one;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreData {

    public static void main(String[] args) {

        Session session = HibernateUtil.buildSessionFactory(new ArrayList<Class>(Arrays.asList(Laptop.class, Student.class))).openSession();

        Transaction transaction = session.beginTransaction();

        Laptop laptop = new Laptop();
        laptop.setModel("Apple");

        Student student = new Student();
        student.setCourse("MBA");
        student.setName("Ankit");
        student.setLaptop(laptop);

        int laptopId = (int) session.save(laptop);
        int studentId = (int) session.save(student);

        transaction.commit();
        session.close();
        HibernateUtil.shutdown();

        System.out.println("Student Id is : " + studentId);
        System.out.println("Laptop Id is : " + laptopId);
    }
}
