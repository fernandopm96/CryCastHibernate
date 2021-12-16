package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

public class MessageRepository {

    private static Session session;

    public static Vector<Message> messagesGroupUser(Integer id_group) {
        session = HibernateUtil.getCurrentSession();
        Query<Message> query = session.createQuery("FROM Message m WHERE m.group.id = :id_group");
        query.setParameter("id_group", id_group);
        return new Vector<Message>(query.list());
    }

    public static void sendMessage(Message message) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
    }
}
