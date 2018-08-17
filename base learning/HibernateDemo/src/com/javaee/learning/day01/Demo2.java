package com.javaee.learning.day01;

import com.javaee.learning.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class Demo2 {
    @Test
    public void insert() {
        Customer customer = new Customer();
        customer.setCusName("唐嫣");
        customer.setAge(20);
        customer.setHobby("唱歌");
        customer.setDescription("美女");

        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.save(customer);
        transaction.commit();
//        session.close();
    }

    @Test
    public void query() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 1);
        System.out.println(customer);
        transaction.commit();
        session.close();
    }

    @Test
    public void update() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 1);
        customer.setDescription("楚雨荨");
        session.update(customer);
        transaction.commit();
        session.close();
    }

    @Test
    public void delete() {
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = session.get(Customer.class, 3);
        session.delete(customer);

        transaction.commit();
        session.close();
    }
}
