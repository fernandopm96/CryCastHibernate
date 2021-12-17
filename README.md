# CryCast 0.1

## Introducción

El proyecto consiste en una aplicación de chat compuesto por grupos los cuales están integrados por el número de usuarios que se elija. Se sigue el patrón de diseño DAO, realizando capas de abstracción entre la vista, el controlador y el modelo, siendo estos totalmente independientes entre sí.

## A tener en cuenta

IMPORTANTE: Existe ya el siguiente usuario de prueba con grupos y mensajes ya creados, con las siguientes credenciales

> username: Ricardo
>
> contraseña .Accesoadatos2021

## Funcionamiento:

- Cuando se ejecute la aplicación, se insertará información en la base de datos mediante la clase CreateExampleDatabase, para poder disponer de usuarios, grupos y mensajes para visualizar.
- La configuración se realiza a través del fichero 'hibernate.properties', en la carpeta resources.
- La url que viene en este fichero es 'jdbc:postgresql://localhost:5432/postgres', el usuario 'postgres' y la contraseña 'bitnami'.

## Paquetes

- controller: Contiene la clase Controller, además de los paquetes repositories y services.

    - clase Controller: Es el punto de unión entre la vista y la capa de persistencia, representada con los paquetes repositories y services.

    - repositories: Paquete que contiene los repositorios de las entidades. Estos repositorios se encargarán únicamente de realizar las operaciones con la base de datos


- services: Contiene los servicios de las entidades, los cuáles interactúan con la clase Controller y mandan las peticiones a los repositorios, realizando la lógica necesaria.

- model: Se almacena los elementos relacionados con los datos de nuestro modelo de negocio.
    - entities: Contiene las entidades User, Group y Message.

- view: Contiene todas las clases relacionadas con el interfaz gráfico, desarrollado con la librería Swing.

- utils: Paquete que contiene clases HibernateUtil y CreateExampleDatabase.
    - HibernateUtil: a través del patrón singleton, devuelve una instancia de SessionFactory y de Session, comunes a la ejecución del programa.
    - CreateExampleDatabase: Se encarga de rellenar la base de datos de información, a fin de que haya opciones para el usuario cuando se ejecute la aplicación.

- exceptions: Contiene las clases RegisterException y LoginException, ambas con el propósito de interceptar los fallos producidos en la inserción de datos por parte del usuario tanto en el login como en el registro.


