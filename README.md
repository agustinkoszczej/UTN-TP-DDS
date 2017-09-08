# **Diseño de Sistemas UTN FRBA - TP 2017:**
# **Grupo 10**
 
## **¿Dónde invierto?**
El mundo de las inversiones en la bolsa es complejo: contamos con cientos de datos históricos para
miles de empresas, que podemos analizar empleando decenas de metodologías. Y todo esto para
resolver una única y simple pregunta: ¿en qué empresa me conviene invertir mi dinero?
Este trabajo práctico se trata justamente de buscar con ayuda del software una respuesta aproximada a
esta pregunta: diseñaremos, construiremos y probaremos un sistema que nos permita, recopilar,
ordenar y analizar los balances públicos de empresas del mercado de Estados Unidos1.
El sistema lo construiremos iterativa e incrementalmente: partiremos de una simple aplicación de
escritorio, sin persistencia, que nos servirá para entender el dominio y casos de uso, e iremos
agregando complejidad tanto de modelo como arquitectónica. El resultado final será un sistema
distribuido, con interfaz Web y persistencia políglota.
El recorrido que haremos no se corresponderá necesariamente con el de un desarrollo industrial, sino
que estará pensado para acompañar los temas de la cursada. De todas formas, será ilustrativo de
muchas decisiones de diseño realistas.
Vale aclarar que el entregable final, dadas varias simplificaciones que se harán con fines didácticos, NO
servirá como un analizador de inversiones fiable, pero sí se asemeja a uno real y sus ideas podrán ser
utilizadas para que el estudiante curioso explore y, con tiempo y dedicación suficiente, lo convierta en un
software útil. Dicho en otras palabras: no hagan a su abuelo comprar acciones en función de los
resultados de nuestro sistema.
A lo largo del proceso, pondremos en práctica los siguientes conocimientos:
* Cualidades de Diseño / Atributos de Calidad
* Arquitectura Modelo-Vista-Controlador (MVC)
* Interfaces Web y de escritorio
* Adaptación de interfaces entrantes y salientes
* Estrategias de persistencia relaciones y no relacionales
* Mapeo objetos-relacional
* Diseño avanzados con objetos
* Patrones de diseño orientados a objetos: Adapter, Builder, Observer, Command, State, Strategy,
Template Method
* Testing y mocking
* Componentes arquitecturales: bases de datos, balanceadores de carga, cachés, servidores de
aplicaciones, procesos calendarizados, colas de mensajes

## **Integrantes:**

| Legajo | Apellido | Nombre | Curso | Github |
| -------- | -------- | -------- | -------- | -------- |
| 155.878-0 | Centeno | Nicolás | K3003 | [centenonicolas](https://github.com/centenonicolas) |
| 150.163-0 | Latasa | Julián | K3003 | [julianlatasa](https://github.com/julianlatasa) |
| 156.102-9 | Koszczej | Agustín | K3003 | [agustinkoszczej](https://github.com/agustinkoszczej) |
| 156.385-3 | Polonuer | Lucas | K3003 | [lucaspolonuer](https://github.com/lucaspolonuer) |
| ~~?????????~~ | ~~Valentin~~ | ~~Víctor~~ | ~~K3003~~ | [~~victorvalentin~~](https://github.com/victorvalentin) |

### **Comandos Útiles**

`mvn clean install eclipse:clean eclipse:eclipse` 

## **Github:**
* https://github.com/dds-utn/2017-jm-group-10
