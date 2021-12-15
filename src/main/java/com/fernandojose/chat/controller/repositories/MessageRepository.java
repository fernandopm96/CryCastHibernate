package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class MessageRepository {

    private static Session session;

    public static List<Message> messagesGroupUser(Integer id_group, Integer id_user) {
        session = HibernateUtil.getCurrentSession();
        Query<Message> query = session.createQuery("FROM Message m WHERE m.user.id = :id_user AND m.group.id = :id_group");
        return query.list();
    }

    public static void sendMessage(Message message) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
    }
}
