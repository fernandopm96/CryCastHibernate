package com.fernandojose.chat.controller;

import com.fernandojose.chat.controller.services.GroupService;
import com.fernandojose.chat.controller.services.MessageService;
import com.fernandojose.chat.controller.services.UserService;
import com.fernandojose.chat.exceptions.GroupCreationException;
import com.fernandojose.chat.exceptions.LoginException;
import com.fernandojose.chat.exceptions.RegisterException;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.Vector;

public class Controller {

    // Usuario que inicia sesión
    private static User currentUser;
    // Grupo en el que se encuentra el usuario en cada momento.
    private static Group currentGroup;

    // Métodos para establecer el usuario y grupo actual.
    public static User getCurrentUser() {
        updateUser();
        return currentUser;
    }
    public static void setCurrentUser(String name) {
        currentUser = UserService.loadUserByName(name);
    }
    public static Group getCurrentGroup() {
        return currentGroup;
    }
    public static void setCurrentGroup(String name) {
        currentGroup = GroupService.loadGroupByName(name);
    }

    /* LOGIN Y REGISTRO*/
    // Login
    public static User validateLogin(String name, String password) throws LoginException {
        return UserService.validateLogin(name, password);
    }
    // Registro
    public static User newUser(String name, String password, String password2) throws RegisterException {
        return UserService.newUser(name, password, password2);
    }

    /* USER */
    // Listado de todos los nombres de los usuarios
    public static List<String> allNamesUsers(){
        List<String> names = UserService.getNamesUsers();
        names.remove(currentUser.getName());
        return names;
    }
    // Listado de todos los usuarios
    public static Vector<User> allUsers(){
        return UserService.allUsers();
    }
    // Actualiza los datos de un usuario cargándolo desde la base de datos.
    public static void updateUser() {
        String name = currentUser.getName();
        currentUser = UserService.loadUserByName(name);
    }

    /* GROUP */
    // Creación de grupo
    public static void newGroup(String name, List<String> namesUsers) throws GroupCreationException {
        namesUsers.add(currentUser.getName());
        GroupService.newGroup(name, namesUsers);
    }
    // Actualización de los grupos del usuario
    public static Vector<Group> groupsByUser(User currentUser){
        return GroupService.groupsByUser(currentUser);
    }

    /* MESSAGE */
    // Enviar mensaje
    public static void sendMessage(String texto){
        MessageService.sendMessage(texto, currentUser, currentGroup);
    }

    // Actualizar mensajes de grupo
    public static Vector<Message> messagesGroupUser(Integer id_group){
        return MessageService.messagesGroup(id_group);
    }


}
