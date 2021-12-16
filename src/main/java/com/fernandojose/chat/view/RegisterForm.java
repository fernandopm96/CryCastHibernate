package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.model.entities.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JPanel pRegisterForm;
    private JPanel pForm;
    private JPanel pPass1;
    private JPasswordField pfPass1;
    private JPanel pUsername;
    private JPasswordField pfpass2;
    private JPanel pPass2;
    private JButton registrarButton;
    private JTextField tfName;
    private JButton cancelarButton;

    public RegisterForm() {
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setContentPane(new Login().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(pRegisterForm).dispose();
            }
        });
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    User user = Controller.newUser(tfName.getText(),pfPass1.getText(),pfpass2.getText());
                    Controller.setCurrentUser(user.getName());
                    JFrame frame = new JFrame("CRYCAST 0.1");
                    frame.setContentPane(new MainView().getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    frame.setResizable(false);
                    frame.setVisible(true);
                    SwingUtilities.getWindowAncestor(getMainPanel()).dispose();
                }catch(Exception ex){JOptionPane.showMessageDialog(getMainPanel().getParent(),ex.getMessage(),"login error",JOptionPane.ERROR_MESSAGE);}
            }
        });
    }

    public JPanel getMainPanel(){
        return pRegisterForm;
    }
}
