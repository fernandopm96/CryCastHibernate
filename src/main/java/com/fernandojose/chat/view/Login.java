package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.WindowUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import static com.fernandojose.chat.utils.WindowUtils.centerWindow;

/* Ventana de inicio de sesión. Es la primera ventana que aparece. Pide al usuario el nombre y la contraseña, y
da la opción también de registrar un nuevo usuario, a través del botón 'Registro'. */
public class Login {
    private JPanel jpLogin;
    private JPanel pUsername;
    private JTextField tfUsername;
    private JPasswordField pfPass;
    private JButton loginButton;
    private JButton registerButton;

    // Asignación de eventos para los botones 'Login' Y 'Register'.
    public Login() {

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    User user = Controller.validateLogin(tfUsername.getText(), pfPass.getText());
                    Controller.setCurrentUser(user.getName());
                    JFrame frame = new JFrame("CRYCAST 0.1");
                    WindowUtils.setIcon(frame);
                    frame.setContentPane(new MainView().getMainPanel());
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.pack();
                    centerWindow(frame);
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
                WindowUtils.setIcon(frame);
                frame.setContentPane(new RegisterForm().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                centerWindow(frame);
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
