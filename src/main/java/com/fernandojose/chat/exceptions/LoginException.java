package com.fernandojose.chat.exceptions;

/* Excepción relacionada con el inicio de sesión del usuario. Si el nombre de usuario no existe, o la contraseña es
incorrecta, lanzará la excepción con el mensaje correspondiente. */
public class LoginException extends Exception {

    public LoginException(String message){
        super(message);
    }
}
