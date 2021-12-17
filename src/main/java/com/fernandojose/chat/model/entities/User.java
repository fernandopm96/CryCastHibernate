package com.fernandojose.chat.model.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Vector;

/* Entidad que representa los usuarios de la base de datos. Se identifica mediante la id y dispone de dos campos más,
name y password.
La lógica del programa impide que dos usuarios tengan el mismo nombre. */
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "contraseña")
    private String password;

    // Relación uno a muchos con mensajes. Un usuario puede haber mandado/ mandar muchos mensajes, un mensaje lo manda un usuario.
    @OneToMany(mappedBy = "user")
    private List<Message> messages = new Vector<Message>();
    // Relación muchos a muchos con grupos. Un usuario puede estar en varios grupos y un grupo contiene varios usuarios.
    @ManyToMany(mappedBy = "users")
    private List<Group> groups = new Vector<Group>();

    public User(){

    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public User(String name, String password, List<Message> messages, List<Group> groups) {
        this.name = name;
        this.password = password;
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
        return name;
    }
}
