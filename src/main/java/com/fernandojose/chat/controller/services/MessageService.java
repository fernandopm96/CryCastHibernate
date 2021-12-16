package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.MessageRepository;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.Vector;

public class MessageService {

    public static Vector<Message> messagesGroupUser(Integer id_group){
        return MessageRepository.messagesGroupUser(id_group);
    }

    public static void sendMessage(String texto, User user, Group group){
        Message message = new Message(texto, user, group);
        MessageRepository.sendMessage(message);
    }
}
