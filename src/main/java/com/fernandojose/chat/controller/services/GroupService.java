package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.GroupRepository;
import com.fernandojose.chat.controller.repositories.UserRepository;
import com.fernandojose.chat.exceptions.GroupCreationException;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

// Clase encargada de interactuar con el repositorio y el controlador.
public class GroupService {

    /* Creación de grupo. Comprueba que el nombre no exista ya en la base de datos, lanzando una excepción en caso de que ya exista uno.
    Posteriormente, mapea los nombres de usuarios recibidos en objetos usuario, para despúes crear el objeto Group y llamar al repositorio
    para su registro.*/
    public static void newGroup(String name, List<String> namesUsers) throws GroupCreationException {

        if(loadGroupByName(name) != null){
            throw new GroupCreationException("Ese grupo ya existe. ");
        }
        List<User> users = namesUsers.stream().map(UserRepository::loadUserByName).collect(Collectors.toList());
        Group group = new Group(name, users);
        GroupRepository.newGroup(group);

    }

    // Llama al repositorio para obtener los grupos de un usuario dado.
    public static Vector<Group> groupsByUser(User currentUser){
        return GroupRepository.groupsByUser(currentUser);
    }

    // A través del repositorio, obtiene un grupo a partir de su nombre.
    public static Group loadGroupByName(String name) {
        return GroupRepository.loadGroupByName(name);
    }


}
