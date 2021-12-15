package com.fernandojose.chat.model.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "grupos")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @OneToMany(mappedBy = "group")
    private List<Message> messages = new ArrayList<Message>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "grupos_usuarios",
            joinColumns = @JoinColumn(name = "id_grupo"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<User> users = new ArrayList<User>();

    public Group(){

    }
    public Group(String name) {
        this.name = name;
    }

    public Group(String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }
    public Integer getId() {
        return id;
    }

    // Helpers
    public void addUser(User user){
        users.add(user);
    }
    public void addMessage(Message message){
        messages.add(message);
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }


}
