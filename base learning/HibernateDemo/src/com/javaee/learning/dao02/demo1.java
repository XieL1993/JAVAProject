package com.javaee.learning.dao02;

import com.javaee.learning.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

public class demo1 {
    @Test
    public void test() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("郑爽");
        Linkman linkman1 = new Linkman();
        Linkman linkman2 = new Linkman();
        linkman1.setName("楚雨荨");
        linkman2.setName("微微");

        customer.getLinkMans().add(linkman1);
        customer.getLinkMans().add(linkman2);
        session.save(customer);
//        linkman1.setCustomer(customer);
//        linkman2.setCustomer(customer);
//        session.save(linkman1);

        transaction.commit();
    }

    @Test
    public void delete() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 1);
        session.delete(customer);
        transaction.commit();
    }

    @Test
    public void demo() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = session.get(Customer.class, 3);
        Linkman linkman = session.get(Linkman.class, 4);
        // 双向关联
        linkman.setCustomer(customer);
        customer.getLinkMans().add(linkman);

        transaction.commit();
    }
}
