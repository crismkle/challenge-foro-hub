<h1 align="center"> CHALLENGE FORO HUB </h1>

Proyecto Challenge del curso de Java y Spring Boot de Alura/Oracle.
El mismo consiste en una API Rest que simula un foro para resolver dudas relacionadas a ciertos cursos.
El foro tiene sus tópicos, respuestas de los tópicos, cursos y usuarios.

![Badge-Spring](https://github.com/crismkle/challenge-foro-hub/assets/122938039/86e9f180-ddda-4059-8b46-911a742ecfe5)

## :clipboard: Enunciado

Crear un foro llamado ForoHub: en él, vamos a replicar este proceso a nivel de back end y, para eso, desarrollar una API REST usando Spring.

La API se centrará en los tópicos, respuestas, cursos y debe permitir a los usuarios:

- Crear un nuevo tópico / respuesta / usuario;
- Mostrar todos los tópicos / respuestas / usuarios creados;
- Mostrar un tópico específico;
- Actualizar un tópico / respuesta / usuario;
- Eliminar un tópico / respuesta / usuario.

Realizar lo que normalmente conocemos como CRUD (CREATE, READ, UPDATE, DELETE) y usando un framework que facilitará mucho nuestro trabajo.

En resumen, nuestro objetivo con este challenge es implementar una API REST con las siguientes funcionalidades:

- API con rutas implementadas siguiendo las mejores prácticas del modelo REST;
- Validaciones realizadas según las reglas de negocio;
- Implementación de una base de datos relacional para la persistencia de la información;
- Servicio de autenticación/autorización para restringir el acceso a la información.


## :toolbox: Tecnologías utilizadas
- Java JDK v17
- Maven v4
- Spring Boot v3.2.6
- Spring Data JPA
- Spring Security
- Spring Web
- SpringDoc-OpenAPI
- Auth0
- MySQL v8
- Flyway Migration
- Validation
- Lombok


## :hammer:Funcionalidades del proyecto
1. Registro de usuario
2. Login de usuario
3. Listado, actualización y borrado de usuario
4. Creación, listado, listado por id, actualización y borrado de un tópico
5. Creación, listado, actualización y borrado de una respuesta
6. Del 3 al 5 exige el token de seguridad del logueo


## :keyboard: Comentarios del código

### 1. Implementación de buenas prácticas del modelo REST
- Patrón MVC (Model-View-Controller) en donde en el paquete Domain (Model) están todos los datos de Topicos, Respuestas, Cursos y Usuarios, junto con sus servicios en donde se implementan las reglas de negocio.
View que representaría el REST Client (Insomnia) y los controllers con los que se comunica: TopicoController, RespuestaController, UsuarioController y AutenticationController.
- Uso de la clase ResponseEntity de Spring con DTOs para personalizar cada uno de los retornos de las clases controllers.

### 2. Validaciones según reglas de negocio y de integridad
- Una de las reglas de negocio de un foro es la de no permitir crear tópicos duplicados. Para eso se creó el paquete Validaciones, con la interface ValidadorDeTopicos dentro, en la que cada regla de negocio que se agregue implemente su método validar. Estas clases llevan la anotación de @Component, con lo cual en el servicio se puede llamar al método validar de cada una de las reglas de negocio. La entidad Usuario también tiene sus validaciones, por ejemplo que no se permita registrar un usuario con un perfil que no sea la de Estudiante.
```
validadores.forEach(v -> v.validar(datosRegistroTopico));
```
- En cuanto a las validaciones de integridad, se usa la clase ValidacionDeIntegridad que extiende de RuntimeException, y es llamada cuando los datos ingresados no son encontrados.

### 3. Tratamiento de errores
- Para evitar exponer información sensible como el stacktrace de una excepción al generarse un error, por ejemplo, al consultar un tópico inexistente. Para resolver esto se configura la propiedad `server.error.stacktrace` en `applications.properties`.
- En cuanto al tratamiento de los errores en sí, se crea la clase TratadorDeErrores en la que captura las excepciones lanzadas, ya sean tanto de errores 400, 404, errores de validación de integridad y/o de negocio.
```
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratamientoError400(MethodArgumentNotValidException methodArgumentNotValidException){
        var errores = methodArgumentNotValidException.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();

        return ResponseEntity.badRequest().body(errores);
    }
```

![image](https://github.com/crismkle/challenge-foro-hub/assets/122938039/f6238490-f9d2-44fc-8432-772a2692084d)


### 4. Immplementación de base de datos
- Se utiliza una base de datos relacional con MySQL como gestor.

![image](https://github.com/crismkle/challenge-foro-hub/assets/122938039/26d2855b-5213-43c2-a8f0-f88355d57f53)


### 5. Servicio de autenticación/autorización
- El proceso de autenticación de usuario se realiza con los atributos de email y clave. El enfoque usado de Spring Security para la autenticación es el de Stateless.
Para autenticar el usuario se utiliza el controlador `AutenticacionController`, que a su vez utiliza el `AuthenticationManager` de Spring Security pasándole un objeto con los datos de login y clave.
- Para generar y devolver un JSON Web Token (JWT) se integra la librería Auth0 Java JWT, recomendada para generar y verificar JWT en aplicaciones Java. La clase TokenService es la encargada de generar tokens JWT.
- Para el proceso de autorización el token generado en la autenticación debe ser enviado en el header de cada solicitud API Rest, salvo en dos casos: POST de Login y POST de usuario. En estos casos se debe permitir el acceso a todo el público para poder acceder al sistema.


## 📦 Despliegue

Sigue estos pasos para ejecutar el proyecto en tu equipo, recuerda tener instalado el JDK.
1. Clona el repositorio en tu equipo.
2. Importa el proyecto a IntelliJ u otro IDE que soporte Java
3. Crea la base de datos "foro-api" en MySQL
4. Configura las variables de entorno para la BD y JWT en application.properties
5. Ejecuta el proyecto
6. Crea y prueba las requests en algún Rest Client como Insomnia o Postman
7. También puedes probar el proyecto con SpringDoc, usando la herramienta Swagger


## :tv: Ejemplos

Uso de todas las funcionalidades.

### CRUD de tópicos:

https://github.com/crismkle/challenge-foro-hub/assets/122938039/e7b69531-2bc2-44a5-be70-0f816f714bc4

### CRUD de respuestas:

https://github.com/crismkle/challenge-foro-hub/assets/122938039/d394baa0-2cfd-4718-a440-f58db51ff093

### CRUD de usuarios:

https://github.com/crismkle/challenge-foro-hub/assets/122938039/743ad33d-43c4-4919-9295-4c14b240be18


## ✒️ Autor
* **Cristian Muñoz** - [crismkle](https://github.com/crismkle)
