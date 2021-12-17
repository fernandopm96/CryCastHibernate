package com.fernandojose.chat.exceptions;
/* Se lanzará cuando el usuario no rellene correctamente los campos del registro.
- Algún campo sin rellenar
- Nombre de usuario ya existente
- Contraseña no válida
- Dos contraseñas que no coincidan(se pedirá que introduzca dos veces la contraseña en el registro):
 */
public class RegisterException extends Exception {

    public RegisterException(String message){
        super(message);
    }
}
