# Evaluación Java Nisum

[![Build Status](https://travis-ci.org/joemccann/dillinger.svg?branch=master)](https://travis-ci.org/joemccann/dillinger)

## Requested Requirements.

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de error.
Todos los mensajes deben seguir el formato:
{"mensaje": "mensaje de error"}

## Registro

- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más
un listado de objetos "teléfono", respetando el siguiente formato:

```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

- Responder el código de status HTTP adecuado
- En caso de éxito, retorne el usuario y los siguientes campos:
- id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más
deseable un UUID)
- created: fecha de creación del usuario
- modified: fecha de la última actualización de usuario
- last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha
de creación)
- token: token de acceso de la API (puede ser UUID o JWT)
- isactive: Indica si el usuario sigue habilitado dentro del sistema.
- Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya
registrado".
- El correo debe seguir una expresión regular para validar que formato sea el correcto.
(aaaaaaa@dominio.cl)
- La clave debe seguir una expresión regular para validar que formato sea el correcto. (Una
Mayuscula, letras minúsculas, y dos números)
- Se debe hacer traza de logs dentro del aplicativo.
- El token deberá ser persistido junto con el usuario


## Requisitos

- Banco de datos en memoria.
- Gradle como herramienta de construcción.
- Pruebas unitarias (Deseable: Spock Framework).
- Persistencia con Hibernate.
- Framework Spring Boot.
- Java 8+
- Entrega en un repositorio público (github o bitbucket) con el código fuente y script de
  creación de BD.
- Readme explicando cómo probarlo.
- Diagrama de la solución.

## Requisitos opcionales
- JWT como token
- Pruebas unitarias
- Swagger

## Especificaciones:
El proyecto consiste en una API REST que consume tres endpoints los cuales tienen funcionalidades de acceso, creación y token. 
## Alcance:
* El API REST contará con tres servicios web.
* Un servicio web será "/create/", que se encargará de crear un usuario.
  En caso de crear un usuario, debería devolver un HTTP 200-OK.
* Otro servicio web será "/access/" que se encargará de acceder al usuario por medio de las credentials.
* Y el último servicio será el que accede a la información del usuario por medio del token y la contraseña.
* Se creará test unitarios y de integración.

## Tecnologías usadas:
* El proyecto está desarrollado en 11
* Se utiliza Maven para la gestión de dependencias, build y packaging.
* Para el Backend se utiliza Spring Boot versión 2.7.0.
* Se utiliza la base de datos H2DB.
* Para los tests se uso JUnit y Mockito.
* OpenApi y SwaggerUI

## Acceso a la API REST productivo:

Para más facilidad de uso se optó por utilizar [Swagger UI](https://swagger.io/)

Swagger permite acceder de manera más fácil a los endpoints: <br />

![SwaggerImage](/images/swagger_api.png)

Para crear un usuario se debe enviar el siguiente json al endpoint /create/: <br />

```
{
    "name": "Juan Rodriguez",
    "email": "juan@rodriguez.org",
    "password": "hunter2",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
![SwaggerImage](/images/create_endpoint.png)

Cuando el json es recibido y se persiste en la db la respuesta es un 200 OK: <br />

```
{
    "id": "a3c1f5c4-309c-4f6f-9a29-e33d5589192a",
    "created": "2022-10-27T11:11:44.3719797",
    "modified": "2022-10-27T11:11:44.3719797",
    "last_login": "2022-10-27T11:11:44.3719797",
    "token": "$2a$10$77SEpv0VMcjVpWsrW5KktuWw4HovqP4rCsW2GqryzUzMF0eXJtte2",
    "active": true
}
```
![SwaggerImage](/images/create_endpoint.png)

Si un json es enviado con el mismo email, el servicio retornara un error 400: <br />

```
{
  "message": "already existing email"
}
```
![SwaggerImage](/images/error_400_create_endpoint.png)

## Funcionamiento:
Los demás endpoints funcionan a la perfección. El [Swagger UI](https://swagger.io/) guiará al usuario del servicio para su funcionamiento.
