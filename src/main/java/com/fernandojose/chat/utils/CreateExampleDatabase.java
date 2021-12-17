package com.fernandojose.chat.utils;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.controller.services.GroupService;
import com.fernandojose.chat.controller.services.UserService;
import com.fernandojose.chat.exceptions.GroupCreationException;
import com.fernandojose.chat.exceptions.RegisterException;
import com.fernandojose.chat.model.entities.User;

import java.util.ArrayList;
import java.util.List;

/* Clase encargada de inicializar la base de datos con información a modo de prueba. Se registrarán usuarios, grupos
y mensajes, a fin de disponer de datos en la primera ejecución. */
public class CreateExampleDatabase {

    // Atributos que contienene la información utilizada para llenar la BD.
    private static List<String> usersNames = List.of("Ricardo", "Fernando", "Jose" , "Blas", "Rubén", "Juan Elías", "Manu");
    private static List<String> messages = List.of("Buenas tardes", "Ieeeee", "Como vais", "Habéis terminado lo de Ricardo?", "Que va", "Hola buenas noches", ":)", ":(", "Jajajajja");
    private static List<String> groupsNames = List.of("2 DAM", "Acceso a datos", "CRYSEC");
    private static String password = ".Accesoadatos2021";

    public static void initializeDatabase(){
        setUsers();
        setGroups();
        setMessages();
    }
    // Establece los usuarios
    private static void setUsers() {
        usersNames.forEach(name -> {
            try {
                UserService.newUser(name, password, password);
            } catch (RegisterException e) {
                e.printStackTrace();
            }
        });
    }
    // Establece los grupos
    private static void setGroups(){
        groupsNames.forEach(name -> {
            try{
                if(name.equals("CRYSEC")){
                    GroupService.newGroup(name, List.of(usersNames.get(0),usersNames.get(1), usersNames.get(2)));
                }
                else{
                    GroupService.newGroup(name, usersNames);
                }
            } catch(GroupCreationException e){
                e.printStackTrace();
            }
        });
    }
    // Crea mensajes y los asigna a un usuario y grupo concreto.
    private static void setMessages(){
        for(int i = 0 ; i < 5; i++){
            Controller.setCurrentUser(usersNames.get(i + 1));
            Controller.setCurrentGroup(groupsNames.get(1));
            Controller.sendMessage(messages.get(i));
        }
        Controller.setCurrentGroup(groupsNames.get(2));
        Controller.setCurrentUser(usersNames.get(1));
        Controller.sendMessage(messages.get(5));
        for(int i = 6, j = 3; i < messages.size(); i++, j++){
            Controller.setCurrentUser(usersNames.get(j));
            Controller.setCurrentGroup(groupsNames.get(0));
            Controller.sendMessage(messages.get(i));
        }
    }
}
