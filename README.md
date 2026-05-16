# Task API 

REST API para gestión de tareas construida con Spring Boot, PostgreSQL y autenticación JWT.

## Tecnologías

- Java 21
- Spring Boot 3.2
- Spring Security + JWT
- PostgreSQL
- Spring Data JPA / Hibernate
- Swagger / OpenAPI 3
- Maven

## Requisitos previos

- Java 21+
- Maven 3.6+
- PostgreSQL corriendo en localhost:5432

## Configuración

### 1. Clonar el repositorio

```bash
git clone https://github.com/alzatesua/task-api.git
cd task-api
```

### 2. Crear la base de datos

```bash
sudo -u postgres psql -c "CREATE DATABASE tasks_db;"
```

### 3. Configurar credenciales

Edita `src/main/resources/application.properties`:

```properties
spring.datasource.username=postgres
spring.datasource.password=tu_password
```

### 4. Ejecutar

```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

## Documentación

Swagger UI disponible en: http://localhost:8080/swagger-ui.html
Collection POSTMAN: TaskJavaSprintBoot.postman_collection.json



## 💖 Support

If you enjoy this project, consider supporting my work:

[![GitHub Sponsors](https://img.shields.io/badge/GitHub_Sponsors-💖_Sponsor-ff69b4)](https://github.com/sponsors/alzatesua)
