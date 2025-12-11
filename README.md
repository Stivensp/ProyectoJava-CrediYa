# ProyectoJava-CrediYa

## Descripción

CrediYa es un sistema de consola desarrollado en Java para la gestión integral de préstamos personales en una empresa financiera.  
El sistema permite administrar empleados, clientes, préstamos y pagos, facilitando el control de la cartera de créditos mediante funcionalidades robustas y una interfaz de línea de comandos sencilla.

El proyecto aplica principios sólidos de programación orientada a objetos (POO), utiliza persistencia dual con archivos de texto y base de datos MySQL (a través de JDBC), y cuenta con módulos independientes para garantizar una arquitectura modular y escalable.

---

## Funcionalidades principales

- Registro y consulta de empleados (con roles y salarios).
- Gestión de clientes y sus datos de contacto.
- Creación y seguimiento de préstamos, cálculo automático de cuotas e intereses.
- Registro de pagos y actualización automática de saldos pendientes.
- Generación de reportes personalizados utilizando Streams y expresiones Lambda para análisis de cartera.
- Persistencia de datos mediante archivos locales y base de datos MySQL.
- Manejo de excepciones y validaciones para garantizar integridad y confiabilidad.

---

## Tecnologías y conceptos aplicados

- Lenguaje Java (JDK 8+)
- Programación Orientada a Objetos (POO): herencia, polimorfismo, encapsulamiento.
- JDBC para conexión y manejo de base de datos MySQL.
- Manejo de colecciones, Streams y expresiones Lambda para filtrado y procesamiento de datos.
- Diseño modular basado en patrones DAO y capas de servicio.
- Manejo de archivos para persistencia local.
- Buenas prácticas de diseño (principios SOLID).
- Manejo de excepciones personalizadas.

---

## Estructura del proyecto

- `model/`: Clases que representan las entidades principales (Empleado, Cliente, Préstamo, Pago).
- `dao/`: Interfaces y clases para acceso a datos (archivo y base de datos).
- `service/`: Lógica de negocio y validaciones.
- `app/`: Punto de entrada y menú de interacción.
- `util/`: Utilidades varias (conexión, manejo de archivos, menú).
- `exceptions/`: Clases de excepciones personalizadas.
- `resources/data/`: Archivos para persistencia local.
- `resources/sql/`: Scripts para creación y gestión de la base de datos MySQL.

---

## Instalación y uso

1. Clonar el repositorio:  
   ```bash
   git clone https://github.com/tu_usuario/ProyectoJava-CrediYa.git
