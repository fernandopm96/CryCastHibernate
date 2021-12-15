package com.fernandojose.chat.view;

import javax.naming.ldap.Control;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GroupCreation {
    private JPanel pGroupCreation;
    private JButton bCreateGroup;
    private JList lExistingUsers;
    private JList lAddedusers;
    private JPanel pName;
    private JTextField tfName;
    private JPanel pUsers;
    private JPanel pExistingUsers;
    private JLabel lName;

    public GroupCreation(MainView mainView) {
        //lAddedusers.setListData(new Vector(Controller.allUsers()));
        bCreateGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    //Controller.newGroup(tfName,lAddedusers.getSelectedValuesList());
                    mainView.updateGroups();
                }catch(Exception ex){JOptionPane.showMessageDialog(pGroupCreation.getParent(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
            }
        });
    }

    public Container getMainPanel() {
        return pGroupCreation;
    }
}
