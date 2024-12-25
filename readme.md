# Proyecto Java con Maven

## Descripción

Este proyecto es una aplicación Java que utiliza jSP, JPA Y Maven como como herramienta de gestión de dependencias y construcción. Esta aplicación se dedica a gestionar turnos, permitiendo la creación, visualización y filtrado de turnos y ciudadanos..

## Requisitos

- Java 17 o superior
- Maven 3.6.0 o superior
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

Para ejecutar la aplicación, seleccione el proyecto clonado:
```sh
mvn exec:java -Dexec.mainClass="com.tu.paquete.MainClass"
