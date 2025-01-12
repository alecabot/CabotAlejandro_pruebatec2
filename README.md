# Gestión de Turnos

Esta aplicación se dedica a gestionar turnos, permitiendo la creación, visualización y filtrado de turnos y ciudadanos.

## Requisitos

- Java 17 o superior
- Maven
- Netbean
- Mysql

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/alecabot/CabotAlejandro_pruebatec2.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd CabotAlejandro_pruebatec2
    ```
3. Crea una base de datos en MySQL:
    ```sql
    CREATE DATABASE turnos;
    ```
4. Actualiza el archivo `persistence.xml` con los detalles de tu base de datos:
    ```xml
    <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/turnos?serverTimezone=UTC"/>
    <property name="javax.persistence.jdbc.user" value="<NUEVO_USUARIO>"/>
    <property name="javax.persistence.jdbc.password" value="<NUEVA_CONTRASEÑA>"/>
    ```

5. Haz clic derecho en el proyecto en el panel de proyectos y selecciona `Clean and Build`.

6. Haz clic derecho en el proyecto en el panel de proyectos y selecciona `Run`.

7. Abre tu navegador y ve a `http://localhost:8080/`.



## Arquitectura del Proyecto
   - **JSP**: Las páginas JSP (`index.jsp`) se utilizan para generar contenido HTML dinámico.
   - **Servlets**: Los servlets (`SvCiudadanos`, `SvTurnos`) manejan las solicitudes HTTP y coordinan la lógica de negocio.
   - **ControladoraPersistencia**: Clase que maneja la persistencia de datos, probablemente interactuando con una base de datos.

## Flujo de la Aplicación
   - **Inicio**: La página principal (`index.jsp`) se carga y muestra formularios para ingresar datos de ciudadanos y turnos.
   - **Formulario de Ciudadanos**:
     - El usuario ingresa el nombre de un ciudadano y envía el formulario.
     - El formulario envía una solicitud POST al servlet `SvCiudadanos`.
     - El servlet procesa la solicitud, guarda el ciudadano en la base de datos y redirige de nuevo a `index.jsp`.
     - Si hay errores, se muestran mensajes de error en la página.
   - **Formulario de Turnos**:
     - El usuario ingresa los detalles de un turno y selecciona un ciudadano de la lista desplegable.
     - El formulario envía una solicitud POST al servlet `SvTurnos`.
     - El servlet procesa la solicitud, guarda el turno en la base de datos y redirige de nuevo a `index.jsp`.
     - Si hay errores, se muestran mensajes de error en la página.
   - **Visualización de Datos**:
     - La página `index.jsp` muestra tablas con los ciudadanos y turnos registrados.
     - Los datos se obtienen de la base de datos a través de la `ControladoraPersistencia` y se almacenan en la sesión o en el request.

## Componentes Clave
   - **ControladoraPersistencia**: Clase que maneja la lógica de acceso a datos.
   - **Ciudadano**: Clase que representa a un ciudadano.
   - **Turno**: Clase que representa un turno.
   - **Servlets**: Clases que manejan las solicitudes HTTP (`SvCiudadanos`, `SvTurnos`).

Este es un resumen general del funcionamiento del proyecto. La interacción principal se da entre las páginas JSP, los servlets y la capa de persistencia para manejar los datos de ciudadanos y turnos.

## Estructura del Proyecto

La estructura del proyecto es la siguiente:

```
cabotalejandro_pruebatech2/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mycompany/
│   │   │           └── cabotalejandro_pruebatech2/
│   │   │               ├── logica/
│   │   │               │   ├── Ciudadano.java
│   │   │               │   ├── EstadoTurno.java
│   │   │               │   └── Turno.java
│   │   │               ├── persistencia/
│   │   │               │   ├── CiudadanoJpaController.java
│   │   │               │   ├── TurnoJpaController.java
│   │   │               │   ├── ControladoraPersistencia.java
│   │   │               ├── servlets/
│   │   │               │   ├── SvCiudadanos.java
│   │   │               │   └── SvTurnos.java
│   │   ├── resources/
│   │   │   └── META-INF/
│   │   │       └── persistence.xml
│   │   ├── webapp/
│   │   │   └── META-INF/
│   │   │       └── index.jsp
```


