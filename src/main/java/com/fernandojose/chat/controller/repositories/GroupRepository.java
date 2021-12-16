package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.Vector;

// Repositorio asociado con la entidad Group. Se encargará de realizar todas las operaciones en la BBDD que tengan relación con Group.
public class GroupRepository {

    // Referencia estática en la que se almacenará la instancia de la sesión abierta durante el programa, cuando esta sea requerida.
    private static Session session;

    // Método encargado de registrar un grupo
    public static void newGroup(Group group){
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
    }

    // Dado un nombre de grupo, carga la entidad de la base de datos y la devuelve.
    public static Group loadGroupByName(String name) {
        session = HibernateUtil.getCurrentSession();
        Query<Group> query = session.createQuery("FROM Group g WHERE g.name = :name");
        query.setParameter("name", name);
        return (Group)query.uniqueResult();
    }

    // Devuelve los grupos en los que se encuentre el usuario pasado como parámetro.
    public static Vector<Group> groupsByUser(User currentUser) {
        session = HibernateUtil.getCurrentSession();
        Query<Group> userGroups = session.createQuery("SELECT u.groups FROM User u WHERE u.id = :currentUserId ");
        userGroups.setParameter("currentUserId", currentUser.getId());
        return new Vector<Group>(userGroups.list());
    }

}
