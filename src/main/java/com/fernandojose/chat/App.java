package com.fernandojose.chat;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.utils.CreateExampleDatabase;
import com.fernandojose.chat.utils.HibernateUtil;
import com.fernandojose.chat.utils.WindowUtils;

public class App {
    public static void main(String[] args) {

        // Instanciamos un SesssionFactory y después 'rellenamos' la base de datos.
        HibernateUtil.getSessionFactory();
        if(Controller.allUsers().isEmpty()){
            CreateExampleDatabase.initializeDatabase();
        }
        // Establecemos el tema visual si está disponible e inicializamos la ventana de login.
        WindowUtils.setTheme();
        WindowUtils.initializeWindow();

    }
}
