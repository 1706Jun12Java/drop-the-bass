package main.java.com.revature.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.util.Properties;

public class HibernateUtil
{
    private static final SessionFactory sf = sessionFactory();

    private static SessionFactory sessionFactory()
    {
        Properties prop = new Properties();

        try
        {
            prop.load(HibernateUtil.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Configuration c = new Configuration().configure("Hibernate.cfg.xml");

        if (prop != null)
            c.mergeProperties(prop);
        else
        {
            c.setProperty("hibernate.connection.username", System.getenv("DBNAME"));
            c.setProperty("hibernate.connection.password", System.getenv("DBPASS"));
            c.setProperty("hibernate.connection.url", System.getenv("DBURL"));
        }
        ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(c.getProperties()).build();

        return c.buildSessionFactory(sr);
    }

    public static Session getSession()
    {
        return sf.openSession();
    }
}
