package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.UserRepository;
import com.fernandojose.chat.exceptions.LoginException;
import com.fernandojose.chat.exceptions.RegisterException;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    public static List<User> getAllUsers(){
        return UserRepository.getAllUsers();
    }

    public static void showAllUsers(){
        List<User> users = UserRepository.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No hay usuarios registrados. ");
        } else {
            users.forEach(user -> System.out.println(user));
        }
    }

    public static User newUser(String name, String password, String password2) throws RegisterException {

        String error = "";
        if(UserRepository.nameAlreadyExist(name)){
            error = "Ese nombre ya existe";
            throw new RegisterException(error);
        }
        else if(!password.matches("^(?=.*[A-Z])(?=.*[\\W])(?=.*[0-9])(?=.*[a-z]).{8,25}$")){
            error = "La contraseña no puede tener un tamaño menor a 8 dígitos y debe contener al menos una minúscula, una mayúscula, un número y un símbolo.\nIntroduce otra contraseña:  ";
            throw new RegisterException(error);
        } else if(!password.equals(password2)){
            error = "Las contraseñas no coinciden";
            throw new RegisterException(error);
        } else {
            User user = new User(name, password);
            return UserRepository.newUser(user);
        }
    }

    public static User validateLogin(String name, String password) throws LoginException {
        if(loadUserByName(name) == null){
            throw new LoginException("El nombre de usuario no existe. ");
        }
        User user = UserRepository.loadUserWithPasswordAndName(name, password);
        if(user == null){
            throw new LoginException("La contraseña no es correcta. ");
        }
        return user;
    }

    public static User loadUserByName(String name) {
        return UserRepository.loadUserByName(name);
    }
}
