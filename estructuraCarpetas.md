crediya/
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/crediya/
    │   │       ├── app/
    │   │       │   └── Main.java
    │   │       │
    │   │       ├── model/
    │   │       │   ├── Empleado.java
    │   │       │   ├── Cliente.java
    │   │       │   ├── Prestamo.java
    │   │       │   └── Pago.java
    │   │       │
    │   │       ├── dao/ 
    │   │       │   ├── interfaces// 
    │   │       │   │   ├── EmpleadoDAO.java
    │   │       │   │   ├── ClienteDAO.java
    │   │       │   │   ├── PrestamoDAO.java
    │   │       │   │   └── PagoDAO.java
    │   │       │   │
    │   │       │   ├── impl/
    │   │       │   │   ├── EmpleadoDAOImpl.java
    │   │       │   │   ├── ClienteDAOImpl.java
    │   │       │   │   ├── PrestamoDAOImpl.java
    │   │       │   │   └── PagoDAOImpl.java
    │   │       │
    │   │       ├── service/
    │   │       │   ├── EmpleadoService.java
    │   │       │   ├── ClienteService.java
    │   │       │   ├── PrestamoService.java
    │   │       │   └── PagoService.java
    │   │       │
    │   │       ├── util/
    │   │       │   ├── ConexionBD.java
    │   │       │   ├── ArchivoUtil.java
    │   │       │   └── MenuUtil.java
    │   │       │
    │   │       └── exceptions/
    │   │           ├── PrestamoNoEncontradoException.java
    │   │           ├── ClienteNoEncontradoException.java
    │   │           └── EmpleadoNoEncontradoException.java
    │   │
    │   └── resources/
    │       ├── data/
    │       │   ├── 
    │       │   ├── 
    │       │   ├── 
    │       │   └── pagos.txt
    │       └── sql/
    │           └── crediya_schema.sql
    │
    └── test/
        └── com/crediya/
