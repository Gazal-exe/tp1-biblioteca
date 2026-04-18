# TP1 - Sistema de Biblioteca UNLaR

Aplicación de consola desarrollada en Java para gestionar una biblioteca universitaria. El sistema permite administrar estudiantes, libros y préstamos, aplicando validaciones de disponibilidad, control de límite de préstamos y cálculo de multas por demora en la devolución.

## Objetivo del proyecto

El programa modela una biblioteca académica con enfoque en Programación Orientada a Objetos. La lógica principal se concentra en una clase de servicio que administra:

- el catálogo de libros
- los estudiantes registrados
- los préstamos activos
- las devoluciones con cálculo de multa

## Funcionalidades implementadas

Actualmente el sistema permite:

1. Registrar estudiantes nuevos.
2. Agregar libros al catálogo.
3. Registrar préstamos de libros.
4. Registrar devoluciones.
5. Calcular multas por retraso.
6. Listar los préstamos de un estudiante.
7. Listar el catálogo completo.
8. Listar todos los estudiantes registrados.
9. Listar todos los préstamos activos.
10. Buscar libros por título o por una parte del título.

## Reglas de negocio del sistema

El código implementa las siguientes reglas:

- Un libro solo puede prestarse si existe en el catálogo y está disponible.
- Un estudiante debe estar registrado para poder solicitar un préstamo.
- Cada estudiante puede tener como máximo 3 préstamos activos.
- Al registrar un préstamo, el libro pasa a estado no disponible.
- Al registrar una devolución, el libro vuelve a estar disponible.
- La multa se calcula en función de la cantidad de días transcurridos entre la fecha de préstamo y la fecha de devolución.
- El cálculo de la multa está implementado de forma recursiva.
- La multa se limita a un máximo de 30 días de recargo.

## Cálculo de multa

La multa se calcula con esta lógica:

- se toma un valor base del libro de `$1000`
- por cada día de retraso se cobra el `1%` de ese valor
- el monto total surge de acumular ese porcentaje por cada día transcurrido

Ejemplos:

- `1 día` de retraso: `$10`
- `15 días` de retraso: `$150`
- `30 días` o más: `$300` máximo

## Datos precargados al iniciar

La clase principal carga datos de prueba para facilitar el uso del sistema desde el primer arranque.

### Estudiantes iniciales

- Legajo `111` - Yesica Esmeralda
- Legajo `222` - Walter Arias Molino
- Legajo `333` - Carlos Esteban

### Libros iniciales

- ISBN `1001` - `Calculo: Una variable`
- ISBN `1002` - `Calculo de varias variables`
- ISBN `1003` - `Fisica Universitaria`
- ISBN `1004` - `Fisica 1 y 2`
- ISBN `1005` - `Física para la Ciencia y la Tecnología`

### Préstamos iniciales

Al iniciar el programa también se registran préstamos de prueba:

- libro `1001` al estudiante `111`
- libro `1002` al estudiante `222`

Además, se ejecuta un caso de prueba automático para demostrar el cálculo de multa con una devolución simulada de `15 días`.

## Menú principal

La interfaz funciona por consola y ofrece las siguientes opciones:

- `1` Registrar estudiante
- `2` Agregar libro al catálogo
- `3` Registrar préstamo
- `4` Registrar devolución y mostrar multa
- `5` Listar préstamos de un estudiante
- `6` Listar catálogo completo
- `7` Listar estudiantes registrados
- `8` Ver todos los préstamos activos
- `9` Buscar libros por título
- `0` Salir

## Estructura del proyecto

El proyecto está organizado en paquetes con responsabilidades separadas:

- `unlar.edu.ar.model`
  Contiene las clases del dominio:
  `Libro`, `Estudiante` y `Prestamo`.

- `unlar.edu.ar.service`
  Contiene `BibliotecaService`, que centraliza toda la lógica del sistema.

- `unlar.edu.ar.ui`
  Contiene `MenuUI`, responsable de la interacción con el usuario por consola.

- `unlar.edu.ar.exception`
  Contiene las excepciones personalizadas para manejar errores de negocio.

- `unlar.edu.ar`
  Contiene `Main`, punto de entrada de la aplicación.

## Clases principales

### `Libro`

Representa un libro del catálogo con los siguientes datos:

- ISBN
- título
- autor
- año de publicación
- disponibilidad

### `Estudiante`

Representa un alumno registrado en el sistema con:

- legajo
- nombre
- carrera
- email

### `Prestamo`

Relaciona un libro con un estudiante e incluye:

- fecha de préstamo
- fecha de devolución

La clase redefine `equals` y `hashCode`, lo que permite almacenar préstamos activos en un `HashSet`.

### `BibliotecaService`

Es la clase central del sistema. Administra:

- `ArrayList<Libro>` para el catálogo
- `HashMap<String, Estudiante>` para estudiantes registrados
- `HashSet<Prestamo>` para préstamos activos

También implementa la búsqueda, registro de operaciones y validaciones de negocio.

## Excepciones personalizadas

El sistema define excepciones específicas para representar errores frecuentes:

- `LibroNoDisponibleException`
- `EstudianteNoEncontradoException`
- `LimitePrestamosExcedidoException`

Esto permite distinguir claramente los errores de negocio dentro del flujo del programa.

## Tecnologías utilizadas

- Java 17
- Maven
- Colecciones de Java (`ArrayList`, `HashMap`, `HashSet`)
- API `java.time` para manejo de fechas
- Streams para búsquedas y filtros

## Cómo ejecutar el proyecto

### Compilar

```bash
mvn compile
```

### Ejecutar

```bash
mvn exec:java -Dexec.mainClass="unlar.edu.ar.Main"
```

Si no se cuenta con el plugin `exec-maven-plugin`, también puede ejecutarse desde el IDE corriendo la clase `Main`.

## Observaciones sobre la implementación actual

- La interfaz es completamente por consola.
- Los datos se almacenan en memoria durante la ejecución.
- No existe persistencia en base de datos ni archivos.
- El sistema está preparado para demostración, práctica académica y validación de conceptos de POO.

## Estado del trabajo

El código fuente implementa las funcionalidades principales del sistema de biblioteca solicitadas para el trabajo práctico, incluyendo el manejo de préstamos, devoluciones, validaciones y multa por retraso.
