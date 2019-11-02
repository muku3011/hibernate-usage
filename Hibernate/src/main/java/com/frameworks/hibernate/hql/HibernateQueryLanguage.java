package com.frameworks.hibernate.hql;

import com.frameworks.hibernate.util.HibernateUtil;
import org.hibernate.*;
import java.util.*;

public class HibernateQueryLanguage {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory(new ArrayList<>(Collections.singletonList(Student.class)));

        /*Adding entries in database*/
        Session session1 = sessionFactory.openSession();
        session1.beginTransaction();
        for (int i = 0; i < 50; i++) {
            Student student = new Student();
            student.setUserId(i);
            student.setCreatedBy("mukesh " + i);
            student.setCreatedDate(new Date());
            student.setUsername("Joshi " + i);
            session1.save(student);               //Run only once to populate DB
        }
        session1.getTransaction().commit();
        session1.close();

        /*Creating session for HQL queries*/
        Session session2 = sessionFactory.openSession();
        session2.beginTransaction();

        /*Running hibernate query*/

        /*Query one*/
        System.out.println("Running Query One");

        Query query1 = session2.createQuery("from Student where USER_ID = 49");
        Student student = (Student) query1.uniqueResult();
        System.out.println(student);

        /*Query two*/
        System.out.println("Running Query Two");

        Query query2 = session2.createQuery("from Student");
        List<Student> students = query2.list();
        for (Student stu : students) {
            System.out.println(stu);
        }

        /*Query three*/
        System.out.println("Running Query Three");

        Query query3 = session2.createQuery("from Student where user_id between 10 and 20");
        List<Student> studentsList = query3.list();
        for (Student stu : studentsList) {
            System.out.println(stu);
        }

        /*Query four*/
        System.out.println("Running Query Four");

        Query query4 = session2.createQuery("select userId, username, createdBy, createdDate from Student where userId = 10");
        Object[] studentResultObject = (Object[]) query4.uniqueResult();
        System.out.println("Student Id : " + studentResultObject[0] + " Student name : " + studentResultObject[0] + " Student created by : " + studentResultObject[0]);

        /*Query five*/
        System.out.println("Running Query Five");

        Query query5 = session2.createQuery("select userId, username, createdBy, createdDate from Student");
        List<Object[]> studentResultObjectArray = query5.list();
        for (Object[] studentObject : studentResultObjectArray) {
            System.out.println("Student Id : " + studentObject[0] + " Student name : " + studentObject[0] + " Student created by : " + studentObject[0]);
        }

        /*Query six*/
        System.out.println("Running Query Six");
        String runtimeValues = "Mukesh";

        Query query6 = session2.createQuery("select userId, username, createdBy, createdDate from Student where username = :b");
        query6.setParameter("b", runtimeValues);
        List<Object[]> studentResult = query6.list();
        for (Object[] studentObject : studentResult) {
            System.out.println("Student Id : " + studentObject[0] + " Student name : " + studentObject[0] + " Student created by : " + studentObject[0]);
        }

        /*Native Query*/
        /*Query seven*/
        System.out.println("Running Query Seven");

        SQLQuery query7 = session2.createSQLQuery("SELECT * FROM A_STUDENT WHERE username = 'Mukesh' AND created_by = 'Mukesh'");
        query7.addEntity(Student.class);
        List<Student> sqlQueryResult = query7.list();
        for (Student studentObject : sqlQueryResult) {
            System.out.println("Student Id : " + studentObject.getUserId() + " Student name : " + student.getUsername() + " Student created by : " + student.getCreatedBy());
        }

        /*Query eight*/
        System.out.println("Running Query Eight");

        SQLQuery query8 = session2.createSQLQuery("SELECT USER_ID, USERNAME, CREATED_BY FROM A_STUDENT WHERE username = 'Mukesh' AND created_by = 'Mukesh'");
        query8.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List sqlResult = query8.list();
        for (Object studentObject : sqlResult) {
            Map m = (Map) studentObject;
            System.out.println("Student Id : " + m.get("USER_ID") + " Student name : " + m.get("USERNAME") + " Student created by : " + m.get("CREATED_BY"));
        }

        /*Closing HQL queries*/
        session2.getTransaction().commit();
        session2.close();

        HibernateUtil.shutdown();
    }
}
