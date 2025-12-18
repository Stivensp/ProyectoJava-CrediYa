package com.finalproyectjava.util.backups.servicebackupConsole;
/* 
public class ClienteService {

    //Atributos
    private final List<Cliente> clientes = new ArrayList<>();
    private int countIds = 1;
    private final PrestamoService ps;

    //Constructor
    public ClienteService(PrestamoService ps){
        this.ps = ps;
    }

    //Metodo Registrar
    public Cliente registrarCliente(String nombre, String documento, String correo, String telefono){
        Cliente cliente = new Cliente(
            countIds++,
            nombre,
            documento,
            correo,
            telefono
        );
        clientes.add(cliente);
        return cliente;
    }    
    //Lista de todos los clientes
    public List<Cliente> listaClientes(){
        return clientes;
    }
    //Buscar cliente por id
    public Cliente buscarClienteId(int id){
        for(Cliente c : clientes){
            if(c.getId() == id){
                return c;
            }
        }
        return null;
    }
    //Actualizar cliente por id
    public Cliente actualizarCliente(int id, String nombre, String documento, String correo, String telefono){
        Cliente c = buscarClienteId(id);
        if(c != null){
            c.setNombre(nombre);
            c.setDocumento(documento);
            c.setCorreo(correo);
            c.setTelefono(telefono);
        }
        return c;
    }
    //Eliminar cliente por id
    public boolean eliminarCliente(int id){
        Cliente c = buscarClienteId(id);
        if(c != null){
            clientes.remove(c);
            return true;
        }
        return false;
    }
    // Mostrar préstamos del cliente
    public List<Prestamo> prestamosCliente(int clienteId){
       List<Prestamo> prestamoCliente = new ArrayList<>();

       for(Prestamo p : ps.listaPrestamo()){
        if(p.getClienteId() == clienteId ){
            prestamoCliente.add(p);
        }
      }
    return prestamoCliente;
    }

public List<Prestamo> filtrarPrestamosVencidos(List<Prestamo> lista)

lista.stream()
     .filter(p -> p.getFechaVencimiento().isBefore(LocalDate.now()))
     .toList();

public List<Prestamo> filtrarPrestamosActivos(List<Prestamo> lista)

public void mostrarResumen(List<Prestamo> lista)
fecha.isBefore(hoy);   // true si es anterior
fecha.isAfter(hoy);    // true si es posterior
fecha.isEqual(hoy);    // true si es igual


LocalDateTime ahora = LocalDateTime.now();
LocalTime hora = LocalTime.now();

Period p = Period.between(fechaInicio, fechaFin);

p.getDays();
p.getMonths();
p.getYears();

Period.between(LocalDate.now(), fechaVencimiento).getDays();

LocalDate fecha = LocalDate.now();

fecha = fecha.plusDays(10);
fecha = fecha.plusMonths(1);
fecha = fecha.minusYears(2);

if (fechaVencimiento.isBefore(LocalDate.now())) {
    System.out.println("Préstamo vencido");
}

List<Prestamo> vencidos = prestamos.stream()
    .filter(p -> p.getFechaVencimiento().isBefore(LocalDate.now()))
    .toList();

.filter(p -> "ACTIVO".equals(p.getEstado()))

DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
String fechaTexto = fecha.format(formato);


try {
    LocalDate fecha = rs.getDate("fecha").toLocalDate();
} catch (NullPointerException e) {
    System.out.println("La fecha está vacía");
}


pagos.stream()
    .filter(p -> p.getFechaPago()
        .isAfter(LocalDate.now().minusMonths(1)))
    .forEach(p -> System.out.println(
        "Fecha: " + p.getFechaPago() +
        " Monto: $" + p.getMonto()
    ));
}


long cantidad = pagos.stream()
    .filter(p -> p.getFechaPago()
        .isAfter(LocalDate.now().minusMonths(1)))
    .count();

*/
