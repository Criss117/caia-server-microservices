# C.A.I.A

Este es un proyecto de Spring Boot que utiliza Java 17 y PostgreSQL como base de datos.

## DOCUMENTACION

//[Documentacion](https://drive.google.com/drive/folders/17Z53y-OcQibR3fuAEuZUWhoGvfU-HEwB?usp=sharing)

//[Proyecto caia Video](https://drive.google.com/file/d/13K6uPHYlGWuVvM9Pi5hIQhsPRAKN0o8e/view?usp=sharing)

## Requisitos previos

- **Java 17**: Asegúrate de tener instalada la versión 17 de Java.
- **Docker**: Este proyecto utiliza Docker.
- **Maven**: Este proyecto utiliza maven

### Población de las tablas

Una vez que las tablas sean creadas, se poblaran automaticamente siempre que exista un archivo `import.sql` en la carpeta resources.

### Ejecución del proyecto

Lo primero es ejecutar el script dev.sh que generara los contenedores para la base de datos y el microservicio Eureka-

```bash
./dev.sh
```

Después ejecutar cada microservicio.

```bash
mvn spring-boot:run -f ./caia-users/pom.xml

mvn spring-boot:run -f ./caia-conferences/pom.xml

mvn spring-boot:run -f ./caia-reviewers/pom.xml

mvn spring-boot:run -f ./caia-papers/pom.xml

mvn spring-boot:run -f ./caia-gateway/pom.xml
```

## CAIA – Crisis And Indecision Assembly

Bienvenido a CAIA, el sistema de gestión de conferencias que nadie pidió, pero que todos necesitan (aunque no lo admitan). Aquí podrás organizar, revisar y discutir ensayos en un ambiente de caos cuidadosamente estructurado. Porque ¿quién no disfruta de una buena crisis académica?

Características principales:

- **Gestión de usuarios**: Porque todos necesitan un título, incluso si es irrelevante.
- **Subida de ensayos**: Porque escribir un paper de última hora es una tradición.
- **Asignación de revisores**: O cómo pasarle el trabajo a alguien más.
- **Revisión de ensayos**: Donde el feedback es vago y las expectativas son altas.

Contribuciones son bienvenidas, pero advertidos quedan: aquí nada tiene sentido… y está bien.
