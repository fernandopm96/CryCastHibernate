package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.controller.repositories.GroupRepository;
import com.fernandojose.chat.controller.services.MessageService;
import com.fernandojose.chat.model.entities.Group;
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
    private MainView self;

    public MainView() {

        Controller.updateUser();
        this.user = Controller.getCurrentUser();
        System.out.println(user);
        updateGroups();

        //Cada vez que se selecciona un grupo, cambiamos el contenido de los mensajes vistos
        lChats.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                updateMessages();
            }
        });

        //Pulsación del botón de enviar
        bInput.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tfInput.getText().isEmpty()){
                    Controller.sendMessage(tfInput.getText());
                    updateMessages();
                    lMensajes.updateUI();
                    tfInput.setText("");
                }
            }

        });
        //Abre el panel de creación de grupo
        bCrearGrupo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Crear Grupo");
                frame.setContentPane(new GroupCreation().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                frame.setResizable(false);
                frame.setVisible(true);
                SwingUtilities.getWindowAncestor(pGeneral).dispose();
            }
        });
    }

    public JPanel getMainPanel(){
        return pGeneral;
    }

    public void updateGroups() {
        lChats.setListData(Controller.groupsByUser(Controller.getCurrentUser()));
    }
    public void updateMessages(){
        lMensajes.setListData(Controller.messagesGroupUser(((Group)(lChats.getSelectedValue())).getId()));
    }
}
