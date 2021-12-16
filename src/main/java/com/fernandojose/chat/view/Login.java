package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.model.entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class Login {
    private JPanel jpLogin;
    private JPanel pUsername;
    private JTextField tfUsername;
    private JPasswordField pfPass;
    private JButton loginButton;
    private JButton registerButton;

    public Login() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    User user = Controller.validateLogin(tfUsername.getText(), pfPass.getText());
                    Controller.setCurrentUser(user.getName());
                    JFrame frame = new JFrame("CRYCAST 0.1");
                    frame.setContentPane(new MainView().getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setResizable(false);
                    frame.setVisible(true);
                    SwingUtilities.getWindowAncestor(jpLogin).dispose();
                }catch(Exception ex){JOptionPane.showMessageDialog(jpLogin.getParent(),ex.getMessage(),"login error",JOptionPane.ERROR_MESSAGE);}
            }
        });
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("CRYCAST 0.1");
                frame.setContentPane(new RegisterForm().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(jpLogin).dispose();
            }
        });
    }

    public JPanel getMainPanel(){
        return jpLogin;
    }
}
