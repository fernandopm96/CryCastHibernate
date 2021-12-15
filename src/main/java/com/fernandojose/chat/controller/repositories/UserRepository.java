package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserRepository implements IRepository<User> {

    private Session session;

    public UserRepository(){
        session = HibernateUtil.getSessionFactory().openSession();
    }

    public List<User> getAllUsers(){
        CreateSession();
        Query<User> query = session.createQuery("FROM User");
        List<User> users = query.list();
        CloseSession();
        return users;
    }

    public void newUser(User user){
        CreateSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        System.out.println("Usuario registrado. ");
        CloseSession();
    }

    public boolean nameAlreadyExist(String name){
        boolean exists;

        CreateSession();
        Query query = session.createQuery("SELECT u.id FROM User u WHERE u.name = :name");
        query.setParameter("name", name);
        Integer id = (Integer) query.uniqueResult();
        if(id == null){
            exists = false;
        } else {
            exists = true;
        }
        CloseSession();
        return exists;
    }

    public void CreateSession(){
        if(session == null){
            session = HibernateUtil.getSessionFactory().openSession();
        }
    }
    public void CloseSession(){
        if(session != null){
            session.close();
            session = null;
        }
    }

}
