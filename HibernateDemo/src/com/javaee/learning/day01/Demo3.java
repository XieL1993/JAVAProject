package com.javaee.learning.day01;

import com.javaee.learning.HibernateUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class Demo3 {
    @Test
    public void insert() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setCusName("素素");
        customer.setAge(18);
        customer.setHobby("旅游");
        customer.setDescription("可爱");
        session.save(customer);
        transaction.commit();
    }

    @Test
    public void query1() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Customer");
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        transaction.commit();
    }

    @Test
    public void query2() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Customer");
        query.setFirstResult(3);
        query.setMaxResults(3);
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        transaction.commit();
    }

    @Test
    public void query3() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createSQLQuery("select * from customer limit 2,1 ");
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }
        transaction.commit();
    }

    @Test
    public void query4() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Criteria criteria = session.createCriteria(Customer.class);
        Criterion expression = Restrictions.eq("cusName", "素素");
        Criterion expression1 = Restrictions.eq("age", 18);
        criteria.add(expression).add(expression1);
        List<Customer> list = criteria.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        transaction.commit();
    }
}
