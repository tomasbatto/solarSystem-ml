# Respuesta a examen - Tomás Fernandez Battolla
## Consigna
En una galaxia lejana, existen tres civilizaciones. Vulcanos, Ferengis y Betasoides. Cada civilización vive en paz en su respectivo planeta.

Dominan la predicción del clima mediante un complejo sistema informático. A continuación el diagrama del sistema solar.

Premisas:

* El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido horario. Su distancia con respecto al sol es de 500Km.
* El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido horario. Su distancia con respecto al sol es de 2000Km.
* El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido anti­horario, su distancia con respecto al sol es de 1000Km.
* Todas las órbitas son circulares.

Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el sistema solar experimenta un período de sequía.
Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en su máximo.

Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están alineados entre sí pero no están alineados con el sol.

## Preguntas
Realizar un programa informático para poder predecir en los próximos 10 años:
1. ¿Cuántos períodos de sequía habrá? 
2. ¿Cuántos períodos de lluvia habrá y qué día será el pico máximo de lluvia?
3. ¿Cuántos períodos de condiciones óptimas de presión y temperatura habrá?

[Respuesta a las preguntas utiliando la API](http://api.vulcano.ml/resultado)

## Bonus:
Para poder utilizar el sistema como un servicio a las otras civilizaciones, los Vulcanos requieren tener una base de datos con las condiciones meteorológicas de todos los días y brindar una API REST de consulta sobre las condiciones de un día en particular.
1) Generar un modelo de datos con las condiciones de todos los días hasta 10 años en adelante utilizando un job para calcularlas.
2) Generar una API REST la cual devuelve en formato JSON la condición climática del día consultado.
3) Hostear el modelo de datos y la API REST en un cloud computing libre (como APP Engine o Cloudfoudry) y enviar la URL para consulta:
Ej: GET → http://....../clima?dia=566 → Respuesta: {“dia”:566, “clima”:”lluvia”}

### Respuesta
* Se implemento una API REST la cual devuelve lo solicitado: GET -> [api.vulcano.ml/clima?dia=566](http://api.vulcano.ml/clima?dia=566)
* Se genero un modelo de datos en una base de datos noSQL (MongoDB) para persistir los dias con su clima y el resultado de la simulacion.
* Resultados de la Simulación: GET -> [api.vulcano.ml/resultado](http://api.vulcano.ml/resultado)
* Repetir simulacion: GET -> [api.vulacano.ml/simular](http://api.vulcano.ml/simular)
* Se realizo el deploy de la aplicación utilizando AWS, en una instancia EC2 corriendo Ubuntu 16.04.

## Tecnologías utilizadas
* [Scala](https://www.scala-lang.org/download/2.11.8.html)
* [SBT](http://www.scala-sbt.org/)
* [Spray](http://spray.io/) (API REST)
* [MongoDB](https://www.mongodb.com/) 
* [Salat](https://github.com/salat/salat) (conexión con MongoDB)
