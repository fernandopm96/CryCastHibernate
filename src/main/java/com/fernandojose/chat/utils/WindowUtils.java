package com.fernandojose.chat.utils;

import javax.swing.*;
import java.awt.*;

public class WindowUtils {
    private static final String icon = "C:\\Users\\Fernando\\IdeaProjects\\CryCastHibernate\\src\\main\\resources\\logo.png";
    public static void centerWindow(Frame frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }

    public static void setIcon(Frame frame){
        frame.setIconImage(new ImageIcon(icon).getImage());
    }

}
