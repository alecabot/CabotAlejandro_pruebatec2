# Proyecto Java EE

## Descripción

Este proyecto es una aplicación Java que utiliza jSP, JPA Y Maven como como herramienta de gestión de dependencias y construcción. Esta aplicación se dedica a gestionar turnos, permitiendo la creación, visualización y filtrado de turnos y ciudadanos..

## Requisitos

- Java 17 o superior
- netbeans 19

## Instalación

1. Clona el repositorio:
    ```sh
    git clone https://github.com/tu-usuario/tu-repositorio.git
    ```
2. Navega al directorio del proyecto:
    ```sh
    cd tu-repositorio
    ```
3. Compila el proyecto y descarga las dependencias:
    ```sh
    mvn clean install
    ```

## Uso

Para ejecutar la aplicación, sigue estos pasos:

1. Selecciona el proyecto clonado en tu netbeans.
2. Configura un servidor Tomcat en tu IDE (si no lo tienes):
   En NetBeans, puedes configurar un servidor Tomcat siguiendo estos pasos:
    - Ve a `Tools > Servers`.
    - Haz clic en `Add Server`.
    - Selecciona `Apache Tomcat` y haz clic en `Next`.
    - Proporciona la ruta de instalación de Tomcat y haz clic en `Finish`.
4. Ejecuta la aplicación:
    - Haz clic en el botón de ejecutar (ícono de play) en tu IDE.
    - Selecciona el servidor Tomcat configurado cuando se te solicite.
Esto iniciará el servidor Tomcat y desplegará tu aplicación.

## Funcionamiento

1. **Arquitectura del Proyecto**:
   - **JSP**: Las páginas JSP (`index.jsp`) se utilizan para generar contenido HTML dinámico.
   - **Servlets**: Los servlets (`SvCiudadanos`, `SvTurnos`) manejan las solicitudes HTTP y coordinan la lógica de negocio.
   - **ControladoraPersistencia**: Clase que maneja la persistencia de datos, probablemente interactuando con una base de datos.

2. **Flujo de la Aplicación**:
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

3. **Componentes Clave**:
   - **ControladoraPersistencia**: Clase que maneja la lógica de acceso a datos.
   - **Ciudadano**: Clase que representa a un ciudadano.
   - **Turno**: Clase que representa un turno.
   - **Servlets**: Clases que manejan las solicitudes HTTP (`SvCiudadanos`, `SvTurnos`).

Este es un resumen general del funcionamiento del proyecto. La interacción principal se da entre las páginas JSP, los servlets y la capa de persistencia para manejar los datos de ciudadanos y turnos.

