package com.fernandojose.chat;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.utils.HibernateUtil;


public class App {
    public static void main(String[] args) {

  /*      Controller.showUsers();*/
        Controller.newUser();
        HibernateUtil.closeSessionFactory();
    }
}
