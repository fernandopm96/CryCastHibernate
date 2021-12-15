package com.fernandojose.chat;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.utils.CreateExampleDatabase;
import com.fernandojose.chat.utils.HibernateUtil;


public class App {
    public static void main(String[] args) {

        HibernateUtil.getSessionFactory();
        // Si no hay datos cuando se ejecuta, se inicializa una base de datos de prueba.
        if(Controller.allUsers().isEmpty()){
            CreateExampleDatabase.initializeDatabase();
        }

        HibernateUtil.closeSession();
        HibernateUtil.closeSessionFactory();
    }
}
