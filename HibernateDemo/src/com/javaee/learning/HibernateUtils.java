package com.javaee.learning;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

public class HibernateUtils {
    static Configuration configure;
    static SessionFactory factory;

    static {
        configure = new Configuration().configure();
        factory = configure.buildSessionFactory();
    }

    public static Session openSession() {
        return factory.openSession();
    }

    public static Session getCurrentSession() {
        return factory.getCurrentSession();
    }
    @Test
    public void test(){
        Session currentSession = getCurrentSession();
    }
}
