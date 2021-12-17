package com.fernandojose.chat.model.entities;

import javax.persistence.*;

/* Entidad que representa a la tabla mensajes. Se identifica con un id, y contiene un atributo texto.
Además, contiene claves ajenas de usuario y grupo. Un mensaje lo manda un único usuario en un único grupo. */
@Entity
@Table(name = "mensajes")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Integer id;

    @Column(name = "texto")
    private String text;

    // Relación muchos a uno con usuario. Un mensaje lo manda un usuario, que a su vez manda varios mensajes.
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User user;
    // Relación muchos a uno con grupo. Un mensaje se ubica en un grupo, que a su vez contiene varios mensajes.
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_grupo", referencedColumnName = "id")
    private Group group;

    public Message(){

    }
    public Message(String text) {
        this.text = text;
    }

    public Message(String text, User user, Group group) {
        this.text = text;
        this.user = user;
        this.group = group;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "\n " +this.user.getName() + ": " + this.text;
    }
}
