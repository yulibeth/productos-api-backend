Aplicación Backend CRUD
Esta es la aplicación backend para un sistema de gestión de productos que permite a los usuarios agregar, editar, ver y eliminar productos. El backend está construido con Spring Boot y MySQL.

Tecnologías Utilizadas
Spring Boot: Framework para desarrollar aplicaciones Java rápidamente.
MySQL: Base de datos para almacenar la información de los productos.
JPA (Hibernate): ORM para interactuar con MySQL.
Instrucciones de Configuración
1. Clonar el repositorio

git clone <repository_url>
cd <directorio_del_proyecto>

2. Instalar dependencias
Si es necesario, asegúrate de tener instalado Maven o Gradle para manejar las dependencias del proyecto. Si estás usando Maven, puedes usar:
mvn install
3. Configuración de la Base de Datos
La configuración de la base de datos se realiza en el archivo src/main/resources/application.properties. Abre ese archivo y agrega las siguientes configuraciones de la base de datos:

Asegúrate de reemplazar tu_contraseña con la contraseña correcta de tu servidor MySQL y modificar los datos requeridos para la conexion a tu BD local segun corresponda.

4. Crear la Base de Datos
Si la base de datos crud_app no existe aún, ejecuta el siguiente script SQL en tu servidor MySQL para crearla:


CREATE DATABASE crud_app;
USE crud_app;

CREATE TABLE producto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10,2) NOT NULL,
    cantidad_stock INT NOT NULL
);


5. Ejecutar la Aplicación
Una vez configurada la base de datos, puedes ejecutar la aplicación utilizando el siguiente comando:

mvn spring-boot:run

La aplicación estará disponible en http://localhost:8080.

Endpoints
1. GET /api/productos
Obtener todos los productos.

2. POST /api/productos
Crear un nuevo producto.

Cuerpo de la solicitud:


{
  "nombre": "Nombre del Producto",
  "descripcion": "Descripción del producto",
  "precio": 19.99,
  "cantidad_stock": 100
}
3. PUT /api/productos/{id}
Actualizar un producto existente por ID.

Cuerpo de la solicitud:


{
  "nombre": "Nombre actualizado del producto",
  "descripcion": "Descripción actualizada",
  "precio": 29.99,
  "cantidad_stock": 150
}
4. DELETE /api/productos/{id}
Eliminar un producto por ID.
