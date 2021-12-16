package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.MessageRepository;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.model.entities.User;

import java.util.Vector;

// Clase encargada de interactuar con el repositorio y el controlador.
public class MessageService {

    // Recupera los mensajes de un grupo llamando al repositorio.
    public static Vector<Message> messagesGroup(Integer id_group){
        return MessageRepository.messagesGroup(id_group);
    }

    // Crea un objeto Message y llama al repositorio para registrarlo.
    public static void sendMessage(String texto, User user, Group group){
        Message message = new Message(texto, user, group);
        MessageRepository.sendMessage(message);
    }
}
