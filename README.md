<h1 align="center"> CHALLENGE FORO HUB </h1>

Proyecto Challenge del curso de Java y Spring Boot de Alura/Oracle.
El mismo consiste en una API Rest que simula un foro para resolver dudas relacionadas a ciertos cursos.
El foro tiene sus tópicos, respuestas de los tópicos, cursos y usuarios.

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
