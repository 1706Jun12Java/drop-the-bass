package com.revature.util;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	private static SessionFactory sf = null;
	private static SessionFactory sessionFactory(String filename) {
		if(sf == null){
		Configuration c = new Configuration().configure(filename);
			System.out.println(System.getenv("DBNAME"));
		c.setProperty("hibernate.connection.username", System.getenv("DBNAME"));
		c.setProperty("hibernate.connection.password", System.getenv("DBPASS"));
		c.setProperty("hibernate.connection.url", System.getenv("DBURL"));
		ServiceRegistry sr = new StandardServiceRegistryBuilder().applySettings(
				c.getProperties()).build();
			sf =  c.buildSessionFactory(sr);
		return sf;
		} else {
			return sf;
		} 
	}

	public static Session getSession() {
		return sessionFactory("Hibernate.cfg.xml").openSession();
	}
	
	public static void closeFactory(){
		if(sf != null){
			sf.close();
			sf = null;
		}
	}
}
