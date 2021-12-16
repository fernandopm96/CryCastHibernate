package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.exceptions.LoginException;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

// Repositorio asociado con la entidad User. Se encargará de realizar todas las operaciones en la BBDD que tengan relación con User.
public class UserRepository {

    // Referencia estática en la que se almacenará la instancia de la sesión abierta durante el programa, cuando esta sea requerida.
    private static Session session;

    // Obtiene todos los usuarios registrados en la base de datos.
    public static Vector<User> getAllUsers(){
        session = HibernateUtil.getCurrentSession();
        Query<User> query = session.createQuery("FROM User");
        Vector<User> users = new Vector<User>(query.getResultList());
        return users;
    }

    // Registra un nuevo usuario.
    public static User newUser(User user){
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        return user;
    }

    // Comprueba si el string recibido como parámetro coincide con algún nombre de usuario.
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

    // Carga un usuario a través de su nombre y contraseña.
    public static User loadUserWithPasswordAndName(String name, String password) throws LoginException {

        User user = null;

        session = HibernateUtil.getCurrentSession();

        Query query = session.createQuery("FROM User u WHERE u.name = :name AND u.password = :password");
        query.setParameter("name", name);
        query.setParameter("password", password);

        return (User)query.uniqueResult();

    }
    // Carga un usuario a través de su nombre.
    public static User loadUserByName(String name) {
        session = HibernateUtil.getCurrentSession();
        Query<User> query = session.createQuery("FROM User u WHERE u.name = :name");
        query.setParameter("name", name);
        return (User)query.uniqueResult();
    }

    // Actualiza el estado de un usuario.
    public static void updateUser(User currentUser) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        currentUser = (User)session.merge(currentUser);
        session.getTransaction().commit();
    }
}
