# BROTHERHOOD OF MUTANTS
### Contexto
![brotherhood](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(14).png)

Magneto quiere reclutar la mayor cantidad de mutantes para poder luchar contra los X-Men.
Te ha contratado a ti para que desarrolles un proyecto que detecte si un humano es mutante basándose en su secuencia de ADN.
Para eso te ha pedido crear un programa con un método o función con la siguiente firma (En alguno de los siguiente lenguajes: 
Java / Golang / C-C++ / Javascript (node) / Python / Ruby):

En donde recibirás como parámetro un array de Strings que representan cada fila de una tabla de (NxN) con la secuencia del ADN.
Las letras de los Strings solo pueden ser: (A,T,C,G), las cuales representa cada base nitrogenada del ADN.

![matrix](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image.png)

Sabrás si un humano es mutante, si encuentras ​más de una secuencia de cuatro letras
iguales​, de forma oblicua, horizontal o vertical.

#### Ejemplo (Caso mutante):
String[] dna = {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}

En este caso el llamado a la función isMutant(dna) devuelve “true”.

#### Desafios:
##### Nivel 1:
Programa (en cualquier lenguaje de programación) que cumpla con el método pedido por Magneto.
##### Nivel 2:
Crear una API REST, hostear esa API en un cloud computing libre (Google App Engine, Amazon AWS, etc), crear el servicio “/mutant/” en donde se pueda detectar si un humano es mutante enviando la secuencia de ADN mediante un HTTP POST con un Json el cual tenga el siguiente formato:

POST → /mutant/
{ “dna”:["ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"] }

En caso de verificar un mutante, debería devolver un HTTP 200-OK, en caso contrario un 403-Forbidden

##### Nivel 3:
Anexar una base de datos, la cual guarde los ADN’s verificados con la API.
Solo 1 registro por ADN.
Exponer un servicio extra “/stats” que devuelva un Json con las estadísticas de las verificaciones de ADN: {“count_mutant_dna”:40, “count_human_dna”:100: “ratio”:0.4}
Tener en cuenta que la API puede recibir fluctuaciones agresivas de tráfico (Entre 100 y 1 millón de peticiones por segundo).

Test-Automáticos, Code coverage > 80%.

### Instrucciones del Manejo del API
El servicio se encuentra desplegado en un ambiente de heroku e implementa un swagger para su comodidad a la hora del uso

![swagger](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(4).png)

Cuenta con las dos funcionalidades necesarias:

1. Deteccion de Mutantes: 

* Path: /v1.0/brotherhood-recruiter/mutant

* Request Mutant:

![mutant_req](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(5).png)

* Response OK:

![mutant_rep](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(6).png)

* Request Human:

![human_req](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(10).png)

* Response Forbidden:

![human_rep](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(9).png)

* Request Bad:

![bad_req](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(7).png)

* Response Bad Request:

![bad_rep](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(8).png)

2. Retorno de las Estadisticas del reclutamiento:

* Path: /v1.0/brotherhood-recruiter/mutant/stats

* Response:

![bad_rep](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(15).png)


#### Funcionalidades Adicionales

Como parte de las funcionalidades anteriormente mencionadas, se dan algunas ventajas a mencionar:

* El ADN que ingrese en la peticion de deteccion puede venir con caracteres en Mayuscula, minuscula o 
con una combinacion de los dos

* Se implemento Liquibase como mecanismo de rastreo, administracion y aplicativo de cambios en el esquema
de nuestra base de datos.

* Se agrega una tabla de auditoria que permitira conocer o tener rastros de modificaciones, agregaciones o actualizaciones
a los registros de los participantes del reclutamiento.


#### Manejo Local
###### Base de Datos con Docker
Para un despliegue local y para las pruebas de integracion se utiliza una base de datos dockerizada la cual cuenta
con las siguientes credenciales:

* url: jdbc:postgresql://localhost:64321/mutants_xmen
* username: xmen
* password: mutants1234@xmen

para su uso es necesario realizar el siguiente procedimiento:
1. Desde la terminal nos dirigimos al root del proyecto, una vez ahi nos dirigimos a la
carpeta docker con el siguiente comando:

![docket](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(11).png)

2. Una vez dentro de la carpeta docker, ejecutamos los siguientes comandos:

![docket](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(12).png)

3. Si desea correr en local la aplicacion:

![estructura](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(3).png)

* En el src/main/resources/application.yaml documente las credenciales del ambiente de heroku de la siguiente manera

![application.yaml](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(1).png)

* En el src/main/resources/schema.sql documente de la siguiente manera
![schema.sql](https://github.com/Martin9958/mutants-xmen/blob/master/imagenes/image%20(2).png)

#### Cobertura de Pruebas: 95%

##### URL del API
https://brotherhood-of-mutants.herokuapp.com/swagger-ui.html#/

### PreRequisitos

* Java 11
* Maven 3.8.0
* Heroku 7.29
* Spring Boot 2.4.3

### Construido con
* Maven: Dependecy Management

### Realizado por
[Andres Martin Cantor](https://github.com/Martin9958)


