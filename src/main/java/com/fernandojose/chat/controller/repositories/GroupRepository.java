package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class GroupRepository {

    private static Session session;

    public static void newGroup(Group group){
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(group);
        session.getTransaction().commit();
    }

    public static Group loadGroupByName(String name) {
        session = HibernateUtil.getCurrentSession();
        Query<Group> query = session.createQuery("FROM Group g WHERE g.name = :name");
        query.setParameter("name", name);
        return (Group)query.uniqueResult();
    }

    /*public List<Group> groupsUser(User user) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        user = session.load(User.class, user.getId());
        return user.getGroups();
    }*/
}
