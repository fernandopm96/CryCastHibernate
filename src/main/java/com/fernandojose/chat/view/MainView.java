package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.model.entities.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MainView {
    private JPanel pGeneral;
    private JPanel pChats;
    private JPanel pConversacion;
    private JTextField tfInput;
    private JButton bInput;
    private JPanel pInput;
    private JSplitPane jspPanelDividido;
    private JScrollPane jspTextoMensajes;
    private JButton bCrearGrupo;
    private JList lChats;
    private JList lMensajes;
    private User user;
    private Group grupoActual;
    private MainView self;

    public MainView(User user) {
        this.user = user;
        System.out.println(user);
        Controller.setCurrentUser(user.getName());
        updateGroups();
        self = this;

        bInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Controller.sendMessage(tfInput.getText());
            }
        });

        //Cada vez que se selecciona un grupo, cambiamos el contenido de los mensajes vistos
        lChats.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                grupoActual = (Group)(lChats.getSelectedValue());
                Controller.setCurrentGroup(grupoActual.getName());
                lMensajes.setListData(new Vector(grupoActual.getMessages()));
            }
        });

        //Pulsación del botón de enviar
        bInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tfInput.getText().isEmpty()){
                    Controller.sendMessage(tfInput.getText());
                    Controller.setCurrentGroup(grupoActual.getName());
                }
            }
        });
        //Abre el panel de creación de grupo
        bCrearGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pGeneral.getParent().getParent().setVisible(false);
                JFrame frame = new JFrame("Crear Grupo");
                frame.setContentPane(new GroupCreation(self).getMainPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
            }
        });
    }

    public JPanel getMainPanel(){
        return pGeneral;
    }

    public void updateGroups() {
        lChats.setListData(new Vector<Group>(user.getGroups()));
    }
}
