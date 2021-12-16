package com.fernandojose.chat.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateUtil(){

    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            Properties props = readProperties();
            Configuration configuration = new Configuration().addProperties(props);
            configuration.addAnnotatedClass(com.fernandojose.chat.model.entities.Group.class);
            configuration.addAnnotatedClass(com.fernandojose.chat.model.entities.User.class);
            configuration.addAnnotatedClass(com.fernandojose.chat.model.entities.Message.class);
            sessionFactory = configuration.buildSessionFactory();
        }
        return sessionFactory;
    }

    private static Properties readProperties() {
        Properties props_read = new Properties();

        try {
            props_read.load(new FileReader("hibernate.properties"));

        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return props_read;
    }

    public static void closeSessionFactory(){
        if(sessionFactory != null){
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    public static Session getCurrentSession(){
        if(session == null){
            session = sessionFactory.openSession();
        }
        return session;
    }

    public static void closeSession(){
        if(session != null){
            session.close();
            session = null;
        }
    }
}
