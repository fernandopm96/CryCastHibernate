package com.fernandojose.chat.controller.services;

import com.fernandojose.chat.controller.repositories.UserRepository;
import com.fernandojose.chat.exceptions.LoginException;
import com.fernandojose.chat.exceptions.RegisterException;
import com.fernandojose.chat.model.entities.User;

import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

// Clase encargada de interactuar con el repositorio y el controlador.
public class UserService {

    // Devuelve los nombres de los usuarios que obtiene a través del repositorio, haciendo un mapeo de User a String(nombre).
    public static List<String> getNamesUsers(){
        return UserRepository.getAllUsers().stream().map(u -> u.getName()).collect(Collectors.toList());
    }

    // Devuelve todos los usuarios que recibe del repositorio.
    public static Vector<User> allUsers(){
        return UserRepository.getAllUsers();
    }

    /* Verifica los datos correspondientes al registro, lanzando excepción con mensaje personalizado en caso de que los datos no sean correctos.
    Si los datos son correctos, crea un usuario y llama al repositorio para registrarlo. */
    public static User newUser(String name, String password, String password2) throws RegisterException {

         if(name.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            throw new RegisterException("Debes rellenar todos los campos. ");
         }
         if(UserRepository.nameAlreadyExist(name)){
               throw new RegisterException("Ese nombre ya existe");
         }

        if(!password.matches("^(?=.*[A-Z])(?=.*[\\W])(?=.*[0-9])(?=.*[a-z]).{8,25}$")){
            throw new RegisterException("La contraseña debe tener un tamaño entre 8 y 25 dígitos. \nDebe contener al menos una minúscula, una mayúscula, un número y un símbolo.");
        }
        if(!password.equals(password2)){
            throw new RegisterException("Las contraseñas no coinciden");
        }

        User user = new User(name, password);
        return UserRepository.newUser(user);

    }

    /* Verifica el login comprobando que el nombre corresponda con alguno de la base de datos. Si coincide con alguno, comprueba a través del repositorio que exista
    algún usuario con el nombre y la contraseña introducidos, a fin de distinguir los casos de nombre incorrecto y contraseña incorrecta. Si algún dato no es correcto,
    se manda una excepción con el mensaje de error correspondiente. */
    public static User validateLogin(String name, String password) throws LoginException {

        if(name.isEmpty() || password.isEmpty()){
            throw new LoginException("El nombre de usuario no existe. ");
        }
        if(loadUserByName(name) == null){
            throw new LoginException("El nombre de usuario no existe. ");
        }
        User user = UserRepository.loadUserWithPasswordAndName(name, password);
        if(user == null){
            throw new LoginException("La contraseña no es correcta. ");
        }
        return user;
    }

    // Carga un usuario mediante su nombre a través del repositorio.
    public static User loadUserByName(String name) {
        return UserRepository.loadUserByName(name);
    }
    // Actualiza un usuario a través del repositorio.
    public static void updateUser(User currentUser) {
        UserRepository.updateUser(currentUser);
    }
}
