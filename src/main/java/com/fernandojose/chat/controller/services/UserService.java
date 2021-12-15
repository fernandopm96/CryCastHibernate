package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.UserRepository;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.Scanner;

public class UserService {

    private static UserRepository repository = new UserRepository();
    private static Scanner scanner = new Scanner(System.in);

    public static List<User> getAllUsers(){
        return repository.getAllUsers();
    }
    public static void showAllUsers(){
        List<User> users = repository.getAllUsers();
        if(users.isEmpty()){
            System.out.println("No hay usuarios registrados. ");
        } else {
            users.forEach(user -> System.out.println(user));
        }
    }
    public static void newUser(){
        System.out.println("Introduce tu nombre: ");
        var name = scanner.nextLine();
        if(repository.nameAlreadyExist(name))
            System.out.println("Ese nombre ya existe. ");
        System.out.println("Introduce tu contraseña: ");
        var password = scanner.nextLine();

        User user = new User(name, password);
        repository.newUser(user);

    }


}
