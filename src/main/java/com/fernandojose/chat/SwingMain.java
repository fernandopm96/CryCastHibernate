package com.fernandojose.chat;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.utils.CreateExampleDatabase;
import com.fernandojose.chat.utils.HibernateUtil;
import com.fernandojose.chat.utils.WindowUtils;
import com.fernandojose.chat.view.Login;

import javax.swing.*;

import static com.fernandojose.chat.utils.WindowUtils.centerWindow;

public class SwingMain {
    public static void main(String[] args) throws UnsupportedLookAndFeelException, ClassNotFoundException, InstantiationException, IllegalAccessException {
        HibernateUtil.getSessionFactory();
        if(Controller.allUsers().isEmpty()){
            CreateExampleDatabase.initializeDatabase();
        }


        for (javax.swing.UIManager.LookAndFeelInfo info :  javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                javax.swing.UIManager.setLookAndFeel(info.getClassName());
                break;
            }
        }

        JFrame frame = new JFrame("CryCast 0.1 - Inicio de sesión");
        WindowUtils.setIcon(frame);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new Login().getMainPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        centerWindow(frame);
        frame.setResizable(false);
        frame.setVisible(true);

    }
}
