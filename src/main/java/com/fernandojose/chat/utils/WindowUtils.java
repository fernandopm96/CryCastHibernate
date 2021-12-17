package com.fernandojose.chat.utils;

import com.fernandojose.chat.view.Login;

import javax.swing.*;
import java.awt.*;

// Clase de utilidad para establecer parámetros comunes a las ventanas
public class WindowUtils {
    // Ruta relativa al logo de la aplicación, el logo de CRYCAST(Nuestra empresa.)
    private static final String icon ="src/main/resources/logo.png";

    // Inicializa la ventana de login.
    public static void initializeWindow(){
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
    // Centra la posición de una ventana.

    public static void centerWindow(Frame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void setTheme(){
        for (javax.swing.UIManager.LookAndFeelInfo info :  javax.swing.UIManager.getInstalledLookAndFeels()) {
            if ("Nimbus".equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (UnsupportedLookAndFeelException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
    // Método que establece el icono a las ventanas.
    public static void setIcon(Frame frame){
        frame.setIconImage(new ImageIcon(icon).getImage());
    }



}
