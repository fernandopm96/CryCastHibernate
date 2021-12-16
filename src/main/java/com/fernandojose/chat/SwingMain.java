package com.fernandojose.chat;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.utils.CreateExampleDatabase;
import com.fernandojose.chat.utils.HibernateUtil;
import com.fernandojose.chat.view.Login;

import javax.swing.*;

public class SwingMain {
    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();
        if(Controller.allUsers().isEmpty()){
            CreateExampleDatabase.initializeDatabase();
        }

        JFrame frame = new JFrame();
        frame.setContentPane(new Login().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
