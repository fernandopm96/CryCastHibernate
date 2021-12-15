package com.fernandojose.chat.model.entities;

import com.fernandojose.chat.model.entities.Group;
import com.fernandojose.chat.model.entities.Message;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column
    private String name;

    // TODO AÑADIR EMBEDDED Y PASSWORD

    @OneToMany(mappedBy = "user")
    private List<Message> messages = new ArrayList<Message>();

    @ManyToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Group> groups = new ArrayList<Group>();

    public User(){

    }

    public User(String name) {
        this.name = name;
    }

    public User(String name, List<Message> messages, List<Group> groups) {
        this.name = name;
        this.messages = messages;
        this.groups = groups;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Integer getId() {
        return id;
    }

    // Helpers
    public void addMessage(Message message){
        messages.add(message);
    }

    public void addGroup(Group group){
        groups.add(group);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
