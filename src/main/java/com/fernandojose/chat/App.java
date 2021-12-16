package com.fernandojose.chat;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.controller.repositories.GroupRepository;
import com.fernandojose.chat.controller.repositories.UserRepository;
import com.fernandojose.chat.controller.services.GroupService;
import com.fernandojose.chat.controller.services.UserService;
import com.fernandojose.chat.exceptions.RegisterException;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.CreateExampleDatabase;
import com.fernandojose.chat.utils.HibernateUtil;

import java.util.List;


public class App {
    public static void main(String[] args) throws RegisterException {
        HibernateUtil.getSessionFactory();

        // Si no hay datos cuando se ejecuta, se inicializa una base de datos de prueba.
        /*if(Controller.allUsers().isEmpty()){
            CreateExampleDatabase.initializeDatabase();
        }*/

        /*Controller.newUser("Dani",".Accesoadatos2021", ".Accesoadatos2021");
        User user = UserService.loadUserByName("Dani");
        user.addGroup(GroupService.loadGroupByName("Acceso a datos"));
        UserRepository.newUser(user);
        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();*/
    }
}
