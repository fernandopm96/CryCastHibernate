package com.fernandojose.chat;

import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {
    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        System.out.println("Transacción");

        session.getTransaction().commit();
        session.close();
        HibernateUtil.closeSessionFactory();
    }
}
