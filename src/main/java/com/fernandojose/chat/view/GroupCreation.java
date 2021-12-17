package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.exceptions.GroupCreationException;
import com.fernandojose.chat.utils.WindowUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

import static com.fernandojose.chat.utils.WindowUtils.centerWindow;

/* Ventana de creación de grupos. */
public class GroupCreation {
    private JPanel pGroupCreation;
    private JButton bCreateGroup;
    private JList lExistingUsers;
    private JPanel pName;
    private JTextField tfName;
    private JPanel pUsers;
    private JPanel pExistingUsers;
    private JLabel lName;
    private JButton cancelarButton;

    /* Se llama al controlador, el cual devuelve el nombre de todos los usuarios, para añadirlos al objeto JList.
    Posteriormente, se asignan los eventos de los botones 'Crear grupo' y 'Cancelar'. */
    public GroupCreation() {

        lExistingUsers.setListData(new Vector(Controller.allNamesUsers()));
        bCreateGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(pGroupCreation.getParent(), "No puedes dejar en blanco el nombre del grupo. ","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(lExistingUsers.isSelectionEmpty()) {
                    JOptionPane.showMessageDialog(pGroupCreation.getParent(), "Debes de introducir al menos un usuario. ","Error",JOptionPane.ERROR_MESSAGE);
                } else {
                    try{
                        Controller.newGroup(tfName.getText(),(List<String>) lExistingUsers.getSelectedValuesList());
                        SwingUtilities.getWindowAncestor(pGroupCreation).dispose();
                        JFrame frame = new JFrame("CRYCAST 0.1");
                        WindowUtils.setIcon(frame);
                        frame.setContentPane(new MainView().getMainPanel());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        centerWindow(frame);
                        frame.setResizable(false);
                        frame.setVisible(true);
                        SwingUtilities.getWindowAncestor(pGroupCreation).dispose();
                    }catch(GroupCreationException ex){JOptionPane.showMessageDialog(pGroupCreation.getParent(), ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
                }

            }
        });
        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("CRYCAST 0.1");
                WindowUtils.setIcon(frame);
                frame.setContentPane(new MainView().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                centerWindow(frame);
                frame.setResizable(false);
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(pGroupCreation).dispose();
            }
        });
    }

    public Container getMainPanel() {
        return pGroupCreation;
    }
}
