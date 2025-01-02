# Documentacion de Proyecto GYM V2



El proyecto consiste en el desarrollo de una API con tecnología Java utilizando el framework de Spring con Springboot e implementando arquitectura de Microservicios con SpringCloud. 
Este proyecto tiene como objetivo crear rutas e implementar acciones que permitan gestionar distintas operaciones y consultas a bases de datos.
## Requisitos:



- Java Development Kit (JDK): Versión 11 o superior.
- Apache Maven: Versión 3.6 o superior.
- MySQL: Versión 8 o superior.
## Tecnologias utilizadas



- **Frameworks y librerías:**
   - Spring Boot: Framework para crear aplicaciones web y microservicios de manera rápida y sencilla.
   - Spring Cloud Netflix Eureka: Librería para implementar un servidor de registro y descubrimiento de servicios.
   - Spring Cloud Config: Librería para gestionar configuraciones de manera centralizada.
   - Spring Cloud Gateway: Librería para crear una puerta de entrada para los microservicios.
   - Spring Cloud OpenFeign: Librería para facilitar la comunicación entre microservicios mediante llamadas a servicios web.
   - Swagger: Herramienta para documentar y probar APIs de manera interactiva.
  
- **Validación y seguridad:**
   - Spring Validator: Librería para validar datos de entrada en las APIs.
  
- **Productividad y código:**
   - Lombok: Librería para reducir la cantidad de código boilerplate en las clases Java.

- **Persistencia de datos:**
   - JPA (Java Persistence API): Especificación para acceder y manipular datos en bases de datos relacionales.
   - MySQL: Sistema de gestión de bases de datos relacionales.

## Características



A continuación, se detallan las principales características de este proyecto:

1. **API**: La aplicación desarrollada se encarga de consumir la API obteniendo información relevante sobre distintos
   clientes y actividades. Se crea una API que ofrece diversas funcionalidades incluyendo:
    - Búsqueda de clientes y actividades por id.
    - Obtención del listado de clientes, actividades y facturas.
    - Acceso a los datos de un cliente y actividades por id.
    - Listado completo de facturas realizadas.
    - Creacion de actividades nuevas.
    - Guardado de clientes en base de datos.
    - Eliminar y modificar clientes, actividades y facturas por id.
    - Actualizar y modificar clientes y actividades.

2. **Arquitectura de Microservicios**: La API cuenta consta de 5 microservicios tales como:
    - Microservice - Activity: Gestiona las actividades, lo que incluye crear, leer, actualizar y eliminar (CRUD)
      registros de actividades.
    - Microservice - Client: Se encarga de la gestión de clientes, incluyendo la creación, lectura, actualización y
      eliminación de registros de clientes.
    - Microservice - Invoice: Gestiona las facturas, permitiendo la creación, lectura y eliminación de registros de
      facturas.
    - Microservice - Eureka: Actúa como servidor de registro para los demás microservicios, permitiendo el registro y
      descubrimiento de servicios.
    - Microservice - Gateway: : Funciona como puerta de entrada para los microservicios, permitiendo el acceso a los
      diferentes microservicios a través de un único punto de entrada y puerto (8080).
    - Microservice - Config: Proporciona configuraciones para los demás microservicios, permitiendo la gestión
      centralizada de configuraciones.

3. **Manejo de excepciones:**
    - El proyecto incluye un sistema de manejo de excepciones que garantiza una respuesta adecuada a diferentes tipos de errores.

4. **Almacenamiento en Base de Datos**: Se implementa un esquema de base de datos MySQL para almacenar la
   información necesaria.

## Configuración



Antes de ejecutar la aplicación, es necesario configurar las siguientes propiedades en los
archivos `msvc-invoice.yml`, `msvc-client.yml` y ,`msvc-activity.yml` dentro del microservicio de configuraciones en la
carpeta resources:

Se debe de configurar las propiedades correspondientes para MySQL:

```properties
spring:
    application:
        name:msvc-<name>
    datasource:
        driver-class-name:com.mysql.cj.jdbc.Driver
        url:jdbc:mysql://localhost:3306/<db_name>
        username:<username>
        password:<password>
    jpa:
        hibernate:
            ddl-auto:update
        database:mysql
        database-platform:org.hibernate.dialect.MySQL8Dialect
```

## Ejecucion de proyecto



### Pasos para ejecutar el proyecto

    1. Clonar el repositorio del proyecto desde GitHub.
    2. Ejecutar el comando mvn clean install en el directorio raíz del proyecto.
    3. Iniciar el microservicio de configuración (msvc-config).
    4. Iniciar el microservicio de registro (msvc-eureka).
    5. Iniciar el microservicio de puerta de enlace (msvc-gateway).
    6. Iniciar los microservicios de clientes (msvc-client), actividades (msvc-activity) e invoices (msvc-invoice).
    7. Acceder a la documentación de la API en http://localhost:8080/swagger-ui.html.

## Endpoints

| Endpoint                                                                   | Metodo | Parametros / Body                                                  | Descripcion                                       |
|----------------------------------------------------------------------------|--------|--------------------------------------------------------------------|---------------------------------------------------|
| `/api/v2/clients`                                                          | GET    | N/A                                                                | Obtener todos los clientes registrados.           |
| `/api/v2/clients/{id}`                                                     | GET    | `{ id }` (Path Parameter)                                          | Obtener cliente por id.                           |
| `/api/v2/clients/save`                                                     | POST   | `{ name, surname, age, email, phone }`                             | Guardar cliente.                                  |
| `/api/v2/clients/update/{id}`                                              | PUT    | `{ id }` (Path Parameter) , `{ name, surname, age, email, phone }` | Actualizar cliente por id.                        |
| `/api/v2/clients/delete/{id}`                                              | DELETE | `{ id }` (Path Parameter)                                          | Eliminar cliente por id.                          |
| `/api/v2/clients/search-by-activity/{id}`                                  | GET    | `{ id }` (Path Parameter)                                          | Obtener clientes registrados en actividad por id. |
| `/api/v2/clients/set-activity/client/{idClient}/activity/{idActivity}`     | POST   | `{ idClient }` (Path Parameter),`{ idActivity }` (Path Parameter)  | Agregar actividad a cliente por id.               |
| `/api/v2/clients/delete-activity/client/{idClient}/activity/{idActivity}"` | GET    | `{ idClient }` (Path Parameter),`{ idActivity }` (Path Parameter)  | Eliminar actividad de cliente por id.             |
| `/api/v2/activities`                                                       | GET    | N/A                                                                | Obtener todas las actividades registradas.        |
| `/api/v2/activities/{id}`                                                  | GET    | `{ id }` (Path Parameter)                                          | Obtener actividad por id.                         |
| `/api/v2/activities/save`                                                  | POST   | `{ name, price }`                                                  | Guardar actividad.                                |
| `/api/v2/activities/update/{id}`                                           | PUT    | `{ id }` (Path Parameter) , `{ name, price }`                      | Actualizar actividad por id.                      |
| `/api/v2/activities/delete/{id}`                                           | DELETE | `{ id }` (Path Parameter)                                          | Eliminar actividad por id.                        |
| `/api/v2/activities/search-client/{id}`                                    | GET    | `{ id }` (Path Parameter)                                          | Obtener clientes registrados en actividad por id. |
| `/api/v2/invoices`                                                         | GET    | N/A                                                                | Obtener todas las facturas registradas.           |
| `/api/v2/invoices/{id}`                                                    | GET    | `{ id }` (Path Parameter)                                          | Obtener factura por id.                           |
| `/api/v2/invoices/save/client/{idClient}`                                  | POST   | `{ idClient }` (Path Parameter)                                    | Guardar factura de cliente por id.                |
| `/api/v2/invoices/delete/{id}`                                             | DELETE | `{ id }` (Path Parameter)                                          | Eliminar factura por id.                          |          |
