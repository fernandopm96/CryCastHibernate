package com.fernandojose.chat.controller;

import com.fernandojose.chat.controller.services.UserService;
import com.fernandojose.chat.model.entities.User;

import java.util.List;

public class Controller {

    public static List<User> allUsers(){
        return UserService.getAllUsers();
    }
    public static void showUsers(){
        UserService.showAllUsers();
    }
    public static void newUser(){
        UserService.newUser();
    }
}
