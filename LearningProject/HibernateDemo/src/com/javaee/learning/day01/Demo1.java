package com.javaee.learning.day01;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Demo1 {
    public static void main(String[] args) {
        Configuration configure = new Configuration().configure();
        SessionFactory factory = configure.buildSessionFactory();
        Session session = factory.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setCusName("郑爽");
        customer.setAge(18);
        customer.setHobby("猫");
        customer.setDescription("演员");
        session.save(customer);

        transaction.commit();
        session.close();
        factory.close();
    }
}
