package com.javaee.learning.day04;

import com.javaee.learning.HibernateUtils;
import com.javaee.learning.day01.Customer;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

public class Demo1 {
    @Test
    public void add() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        Category category = new Category();
        category.setName("衬衫");
        for (int i = 1; i <= 10; i++) {
            Product product = new Product();
            product.setName("雅戈尔" + i);
            product.setPrice(1000 * i);
            product.setCategory(category);
            session.save(product);
        }
        session.save(category);
        transaction.commit();
    }

    @Test
    public void test1() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Category> list = session.createQuery("from Category").list();
        for (Category c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }

    @Test
    public void test2() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Object> list = session.createQuery("select c.name from Category c").list();
        for (Object c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }

    @Test
    public void test3() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Object> list = session.createQuery("select c.name from Category c order by id desc").list();
        for (Object c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }

    @Test
    public void test4() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Product where price > ? and cid = ?");
        query.setParameter(0, 6000);
        query.setParameter(1, 1);
        List<Product> list = query.list();
        for (Product c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }

    @Test
    public void test5() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Product> list = session.createQuery("select new Product(name,price) from Product").list();
        for (Product c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }

    @Test
    public void test6() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Product");
        query.setMaxResults(10);
        query.setFirstResult(15);
        List<Product> list = query.list();
        for (Product c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }

    @Test
    public void test7() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select count(*) from Product");
        Object object = query.uniqueResult();
        System.out.println(object);
        transaction.commit();
    }

    @Test
    public void test8() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("select price ,count(*) from Product group by price having price > ?");
        query.setParameter(0, 5000);
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }
        transaction.commit();
    }

    @Test
    public void test9() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Category c inner join c.products");
        List<Object[]> list = query.list();
        for (Object[] o : list) {
            System.out.println(Arrays.toString(o));
        }
        transaction.commit();
    }

    @Test
    public void test10() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        SQLQuery sqlQuery = session.createSQLQuery("select * from product");
        sqlQuery.addEntity(Product.class);
        List<Product> list = sqlQuery.list();
        for (Product c : list) {
            System.out.println(c);
        }
        transaction.commit();
    }
}
