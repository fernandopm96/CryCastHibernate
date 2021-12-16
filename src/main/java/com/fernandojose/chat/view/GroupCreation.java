package com.fernandojose.chat.view;

import com.fernandojose.chat.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;

public class GroupCreation {
    private JPanel pGroupCreation;
    private JButton bCreateGroup;
    private JList lExistingUsers;
    private JPanel pName;
    private JTextField tfName;
    private JPanel pUsers;
    private JPanel pExistingUsers;
    private JLabel lName;

    public GroupCreation() {

        lExistingUsers.setListData(new Vector(Controller.allNamesUsers()));
        bCreateGroup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!tfName.getText().isEmpty()){
                    try{
                        Controller.newGroup(tfName.getText(),(List<String>) lExistingUsers.getSelectedValuesList());
                        SwingUtilities.getWindowAncestor(pGroupCreation).dispose();
                        JFrame frame = new JFrame("CRYCAST 0.1");
                        frame.setContentPane(new MainView().getMainPanel());
                        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                        frame.pack();
                        frame.setResizable(false);
                        frame.setVisible(true);
                        SwingUtilities.getWindowAncestor(pGroupCreation).dispose();
                    }catch(Exception ex){JOptionPane.showMessageDialog(pGroupCreation.getParent(),ex.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);}
                } else {
                    JOptionPane.showMessageDialog(pGroupCreation.getParent(), "No puedes dejar en blanco el nombre del grupo. ","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public Container getMainPanel() {
        return pGroupCreation;
    }
}
