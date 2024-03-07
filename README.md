# Prueba BCNC para Inditex
## Contenido
1. [Información general](#información-general)
2. [Tecnologías](#tecnologías)
3. [Arquitectura](#arquitectura)
4. [Instalación y ejecución](#instalación-y-ejecución)


<a name="info-general"></a>
### Información general
***
<p>La aplicación bcnc-inditex es un microservicio desarrollado bajo la tecnología de SpringBoot implementando tres endpoint con API Rest.</p> 

<p>Partimos de la premisa que tanto los album como las fotografías las obtendremos de dos servicios externos a los que accederemos a través de un endpoint que nos es facilitado:</p>

> **Albums:** https://jsonplaceholder.typicode.com/albums
>
> **Photos:** https://jsonplaceholder.typicode.com/photos

<p>La funcionalidad de este micorservicio es la visualización de los albumes y las fotografías asociadas a cada album de tres formas distintas que pasamos a detallar a continuación:</p>

> **a) Recuperar albumes/fotos y guardandolos en BBDD:** Este endpoint recuperará de los servicios externos los album y fotos, asociará cada foto a su album y lo guardará en base de datos. Como resultado de la consulta mostrará los albumes y sus fotos asociadas.
>
> **b) Recuperar albumes/fotos y mostrar resultado:** A diferencia del primer endpoint, aquí recuperará de los servicios externos los album y fotos, asociará cada foto a su album y sin guardar en base de datos, sacará como resultado los albumes con sus fotos asociadas.
>
> **c) Recuperar de BBDD albumes/fotos:** En este caso, no se accederá a recuperar los albumes/fotos de los servicios externos, y los recuperará directamente de la base de datos, de tal forma que si previamente no se ha llamado al primer endpoint y no hay datos en BBDD mostrará un mensaje informando de que no existen albumes. Si por el contrario hay datos en base de datos se mostrará los albumes y sus fotos asociadas.

<a name="tecnologias"></a>
### Tecnologías
***
<p>La tecnología utilizada para este proyecto son las que a continuación se detallan, teniendo en cuenta que las versiones sobre las que se ha trabajado son las propuestas en el inicializador de spring 'https://start.spring.io/' en el momento del desarrollo de esta aplicación:</p>

- Java 17
- Spring Boot versión 3.2.3
- Maven
- Base de datos H2
- Junit5

<a name="arquitectura"></a>
### Arquitectura
***
<p>El diseño de la arquitectura de este microservicio está basado en 4 capas principales:</p>

> **Controlador:** Esta capa es la responsable de manejar las solicitudes HTTP, llamar a los servicios que manejan el negocio y enviar respuestas. En nuestro aplicativo solo hay un controlador con 3 enpoints
>
> **Servicios:** Es la capa que contiene lógica de negocio y la responsable de manipular los datos recuperados de la capa de repositorio.
>
>**Repositorios:** Es la responsable de acceder a la base de datos y realizar operaciones de buscar, insertar, borrar o actualizar.
>
>**Entidades:** Son los objetos DAO de persistencia en la base de datos

<a name="instalación"></a>
### Instalación y Ejecución
***
<p>Será necesario tener instalado java 17 y maven.</p>

<p>Los pasos que se detallan a continuación serán para realizarlos a través de comandos de la consola.</p>


+ Para construir el proyecto habrá que ir al directorio del proyecto y ejecutar:

  ```
  mvn package
  ```

  Esta instrucción generará un fichero 'inditex-1.0.jar' en el directorio 'bcnc-inditex/target' después de compilar la aplicación y correr los test.

  Se podrá acceder al informe de cobertura que se ha generado con la herramienta 'jacoco' en 'bcnc-inditex/target/site/jacoco/index.html'.


+ Para hacer correr la aplicación ejecutar:

  ```
  java -jar target/inditex-1.0.jar
  
  ```
  
  A partir de este momento la aplicación ya está arrancada en el puerto 8081.


+ Las url de cada endpoint del microservicio son:

    - Recuperar albumes/fotos y guardandolos en BBDD: http://localhost:8081/album/getAPIExternalAndSaveBBDD
  
    - Recuperar albumes/fotos y mostrar resultado: http://localhost:8081/album/getAPIExternal
  
    - Recuperar de BBDD albumes/fotos: http://localhost:8081/album/getAll
      