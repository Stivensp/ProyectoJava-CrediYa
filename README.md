https://app.gitbook.com/invite/loNlWVguyK9yCvHoU6kH/HIiDEonPMtXSKzwJZdb5





[# CrediYa – Sistema de Gestión de Préstamos

**CrediYa** es una aplicación desarrollada en **Java** que permite la gestión de **clientes, empleados, préstamos y pagos**, utilizando una arquitectura en capas basada en **DAO + Service**, con persistencia en **MySQL** y **archivos de texto**.

El proyecto está diseñado con un enfoque en **buenas prácticas**, **separación de responsabilidades** y **facilidad de mantenimiento**.

---

## 🚀 Funcionalidades principales

### 👤 Clientes
- Registrar clientes
- Listar clientes
- Buscar cliente por ID
- Actualizar información del cliente
- Eliminar cliente
- Consultar préstamos asociados a un cliente

### 🧑‍💼 Empleados
- Registrar empleados
- Listar empleados
- Buscar empleado por ID
- Actualizar empleado
- Eliminar empleado

### 💳 Préstamos
- Registrar préstamos
- Asociar préstamos a clientes y empleados
- Control de estado del préstamo:
  - Pendiente
  - Pagado
  - Vencido
- Cálculo de saldo
- Listado de préstamos

### 💰 Pagos
- Registrar pagos a préstamos
- Listar pagos
- Consultar pagos por préstamo
- Persistencia adicional en archivo `.txt`

### 📊 Reportes
- Préstamos activos
- Préstamos pagados
- Préstamos vencidos
- Clientes morosos
- Empleados con más préstamos otorgados
- Total recaudado por pagos

---

## 🏗️ Arquitectura del proyecto

El proyecto sigue una **arquitectura en capas**, separando claramente las responsabilidades:

```
Presentation (View / Menu)
        ↓
Service (Lógica de negocio)
        ↓
DAO (Interfaces)
        ↓
DAO Impl (Persistencia)
        ↓
Database / Archivos
```

---

## 📁 Estructura del proyecto

```
crediya/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/crediya/
    │   │       ├── app/          # Clase Main
    │   │       ├── model/        # Entidades del dominio
    │   │       ├── dao/
    │   │       │   ├── interfaces/
    │   │       │   └── impl/
    │   │       ├── service/      # Lógica de negocio
    │   │       ├── util/         # Utilidades (BD, archivos)
    │   │       └── exceptions/   # Excepciones personalizadas
    │   └── resources/
    │       ├── data/             # Archivos .txt
    │       └── sql/              # Script de base de datos
```

---

## 🧩 Modelo de dominio

- Un **Cliente** solicita uno o varios **Préstamos**
- Un **Empleado** otorga **Préstamos**
- Un **Préstamo** pertenece a un **Cliente** y a un **Empleado**
- Un **Pago** pertenece a un **Préstamo**

### Relaciones clave
- Cliente **1..*** Préstamo
- Empleado **1..*** Préstamo
- Préstamo **1..*** Pago

---

## 🔌 Persistencia

### 🗄️ Base de datos
- **MySQL**
- Conexión centralizada en `ConexionBD`
- Acceso a datos mediante el patrón **DAO**

### 📄 Archivos
- Persistencia de pagos en el archivo `pagos.txt`
- Manejo mediante `ArchivoUtil`

---

## 🛠️ Tecnologías utilizadas
- Java SE
- JDBC
- MySQL
- Arquitectura DAO
- Programación Orientada a Objetos (POO)
- UML (Diagrama de Clases)
- Archivos de texto (`.txt`)

---

## ▶️ Cómo ejecutar el proyecto

1. Crear la base de datos usando el script:

```
src/main/resources/sql/crediya_schema.sql
```

2. Configurar las credenciales de la base de datos en:

```
util/ConexionBD.java
```

3. Ejecutar la clase principal:

```
com.crediya.app.Main
```

---

## 📌 Principios aplicados
- Separación de responsabilidades
- Bajo acoplamiento
- Alta cohesión
- Programación contra interfaces
- Arquitectura mantenible y escalable

](https://app.gitbook.com/invite/loNlWVguyK9yCvHoU6kH/HIiDEonPMtXSKzwJZdb5)
