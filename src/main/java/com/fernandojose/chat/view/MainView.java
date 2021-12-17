package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;
import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.Message;
import com.fernandojose.chat.model.entities.User;
import com.fernandojose.chat.utils.WindowUtils;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import static com.fernandojose.chat.utils.WindowUtils.centerWindow;

/* Ventana principal de la aplicación. Dispone de dos paneles, uno que contiene los nombres de los grupos a los
que el usuario tiene acceso y otro panel que muestra los mensajes que hay en los grupos que el usuario seleccione.
Además, dispone de un botón para crear un nuevo grupo, que abrirá una nueva ventana(GroupCreation) además de
disponer de un campo de texto y un botón que realizarán la función de enviar mensajes al grupo que haya seleccionado.*/
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

    /* Llamada al controlador para asignar el usuario que ha iniciado sesión como 'currentUser' para mostrar los
    datos de este usuario en pantalla. Posteriormente, se asignan los eventos a los botones de Crear grupo y enviar
    mensaje, así como a la lista que contiene los grupos del usuario actual. */
    public MainView() {
        tfInput.setEnabled(false);
        Controller.updateUser();
        this.user = Controller.getCurrentUser();
        updateGroups();

        //Cada vez que se selecciona un grupo, cambiamos el contenido de los mensajes vistos
        lChats.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                tfInput.setEnabled(true);
                Group group = (Group) lChats.getSelectedValue();
                Controller.setCurrentGroup(group.getName());
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
                WindowUtils.setIcon(frame);
                frame.setContentPane(new GroupCreation().getMainPanel());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.pack();
                centerWindow(frame);
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
        Vector<Message> messages = Controller.messagesGroupUser(((Group)(lChats.getSelectedValue())).getId());
        lMensajes.setListData(messages);
    }
}
