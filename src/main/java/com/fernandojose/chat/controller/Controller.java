package com.fernandojose.chat.controller;

import com.fernandojose.chat.controller.repositories.MessageRepository;
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

public class Controller {

    public static User currentUser;
    public static Group currentGroup;

    public static User getCurrentUser() {
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

    public static User validateLogin(String name, String password) throws LoginException {
        return UserService.validateLogin(name, password);
    }
    public static void newUser(String name, String password, String password2) throws RegisterException {
        UserService.newUser(name, password, password2);
    }

    public static List<Message> messagesGroupUser(Integer id_group, Integer id_user){
        return MessageService.messagesGroupUser(id_group, id_user);
    }

    public static List<User> allUsers(){
        return UserService.getAllUsers();
    }

    public static void showUsers(){
        UserService.showAllUsers();
    }

    public static void newGroup(String name, List<String> namesUsers) throws GroupCreationException {
        GroupService.newGroup(name, namesUsers);
    }

    public static void sendMessage(String texto){
        MessageService.sendMessage(texto, currentUser, currentGroup);
    }
}
