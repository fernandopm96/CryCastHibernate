package com.fernandojose.chat.exceptions;

/* Excepción que se lanzará cuando surga algún problema en la creación de grupos. */
public class GroupCreationException extends Exception {

    public GroupCreationException(String message){
        super(message);
    }
}
