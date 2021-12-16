package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.exceptions.LoginException;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class UserRepository {

    private static Session session;

    public static Vector<User> getAllUsers(){
        session = HibernateUtil.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        Vector<User> users = new Vector<User>(query.getResultList());
        return users;
    }

    public static User newUser(User user){
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    public static boolean nameAlreadyExist(String name){

        boolean exists;
        session = HibernateUtil.getCurrentSession();
        Query query = session.createQuery("SELECT u.id FROM User u WHERE u.name = :name");
        query.setParameter("name", name);
        Integer id = (Integer) query.uniqueResult();

        if(id == null){
            exists = false;
        } else {
            exists = true;
        }
        return exists;
    }

    public static User loadUserWithPasswordAndName(String name, String password) throws LoginException {

        User user = null;

        session = HibernateUtil.getCurrentSession();

        Query query = session.createQuery("FROM User u WHERE u.name = :name AND u.password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);

        return (User)query.uniqueResult();

    }

    public static User loadUserByName(String name) {
        session = HibernateUtil.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.name = :name");
        query.setParameter("name", name);
        return (User)query.uniqueResult();
    }

    public static void updateUser(User currentUser) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        currentUser = (User)session.merge(currentUser);
        session.getTransaction().commit();
    }
}
