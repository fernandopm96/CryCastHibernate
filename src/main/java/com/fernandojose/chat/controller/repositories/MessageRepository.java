package com.fernandojose.chat.controller.repositories;

import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Vector;

// Repositorio asociado con la entidad Message. Se encargará de realizar todas las operaciones en la BBDD que tengan relación con Message.
public class MessageRepository {

    // Referencia estática en la que se almacenará la instancia de la sesión abierta durante el programa, cuando esta sea requerida.
    private static Session session;

    // Obtiene los mensajes de un grupo.
    public static Vector<Message> messagesGroup(Integer id_group) {
        session = HibernateUtil.getCurrentSession();
        Query<Message> query = session.createQuery("FROM Message m WHERE m.group.id = :id_group");
        query.setParameter("id_group", id_group);
        return new Vector<Message>(query.list());
    }
    // Registra un mensaje en la base de datos.
    public static void sendMessage(Message message) {
        session = HibernateUtil.getCurrentSession();
        session.beginTransaction();
        session.save(message);
        session.getTransaction().commit();
    }
}
