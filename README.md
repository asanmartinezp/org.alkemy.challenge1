# org.alkemy.challenge1
Challange para ingresar a Alkemy

Este repositorio contiene el código correspondiente al pequeño sistema requerido para el ingreso al bootcamp Alkemy. El mismo está escrito en Java y usa el framework Spring Boot.
El requerimiento principal era armar una API REST que permitiera agregar personajes y películas de Disney así como los Géneros asociados a estas. Como era necesario que las rutas siguieran el patron REST se utilizó el proyecto Spring Data REST, el cual expone los diferentes endpoints para la obtención, inserción, actualización y eliminación de las distintas entidades de las clases del modelo siguiendo el patrón HATEOAS.
Los datos generados por el sistema no son persistidos en un archivo en una unidad de almacenamiento sino que utiliza el motor de base de datos relacional en memoria H2 debido principalmente a su flexibilidad.

Además ha sido implementado un sistema de login básico utilizando Spring Security.

Algunos requerimientos no han sido posible de implementar debido a restricciones de tiempo:
 - El envío de correos electrónicos
 - Tests unitarios
 
 Los diferentes endpoints y las operaciones disponibles en ellos están disponibles en el archivo Alkemy.postman_collection.json en la raíz del repositorio el cual puede ser importado en la aplicación Postman.
 
 La aplicación utiliza gradle para la administración de dependencias por lo que puede ser compilada y ejecutada mediante:
 
 - `./gradlew` desde una terminal en Linux 
-  `gradlew.bat` desde la linea de comandos de Windows
