package com.fernandojose.chat.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    private HibernateUtil(){

    }

    public static SessionFactory getSessionFactory(){
        if(sessionFactory == null){
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
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
