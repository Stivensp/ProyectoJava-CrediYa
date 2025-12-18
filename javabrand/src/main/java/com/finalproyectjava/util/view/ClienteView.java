package com.finalproyectjava.util.view;

import java.util.List;

import com.finalproyectjava.exceptions.ClienteNoEncontradoException;
import com.finalproyectjava.exceptions.OperacionNoPermitidaException;
import com.finalproyectjava.exceptions.ValidacionException;
import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.service.ClienteService;
import com.finalproyectjava.service.PrestamoService;

public class ClienteView extends MenuBaseView {

    private final ClienteService cs;

    public ClienteView(ClienteService cs, PrestamoService ps) {
        this.cs = cs;
    }

    @Override
    public void play() {
        int opcion;
        do {
            System.out.println("""
                    
            ▄▄▄▄▄▄▄ ▄▄                             
            ███▀▀▀▀▀ ██ ▀▀               ██         
            ███      ██ ██  ▄█▀█▄ ████▄ ▀██▀▀ ▄█▀█▄ 
            ███      ██ ██  ██▄█▀ ██ ██  ██   ██▄█▀ 
            ▀███████ ██ ██▄ ▀█▄▄▄ ██ ██  ██   ▀█▄▄▄ 
                                                    
             1. Registrar cliente
             2. Listar clientes
             3. Buscar cliente por ID
             4. Actualizar cliente
             5. Eliminar cliente
             6. Mostrar préstamos del cliente
             0. Volver
            """);

            opcion = leerEntero("Seleccione una opción:", 0, 6);

            switch (opcion) {
                case 1 -> registrarClienteView();
                case 2 -> listarClientesView();
                case 3 -> buscarClienteIdView();
                case 4 -> actualizarClienteView();
                case 5 -> eliminarClienteView();
                case 6 -> prestamosClienteView();
                case 0 -> limpiarConsola();
            }
        } while (opcion != 0);
    }

    private void registrarClienteView() {
        consola.nextLine();
        try {
            String nombre = leerTexto("Nombre:");
            String documento = leerTexto("Documento:");
            String correo = leerCorreo("Correo:");
            String telefono = leerTelefono("Teléfono:");

            cs.registrarCliente(nombre, documento, correo, telefono);
            System.out.println("Cliente registrado correctamente");

        } catch (ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void listarClientesView() {
        List<Cliente> clientes = cs.listaClientes();

        if (clientes.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        clientes.forEach(System.out::println);
    }

    private void buscarClienteIdView() {
        int id = leerEntero("Ingrese ID del cliente:", 1);

        try {
            Cliente c = cs.buscarClienteId(id);
            System.out.println(c);

        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void actualizarClienteView() {
        int id = leerEntero("Ingrese ID del cliente:", 1);
        consola.nextLine();

        try {
            String nombre = leerTexto("Nuevo nombre:");
            String documento = leerTexto("Nuevo documento:");
            String correo = leerCorreo("Nuevo correo:");
            String telefono = leerTelefono("Nuevo teléfono:");

            cs.actualizarCliente(id, nombre, documento, correo, telefono);
            System.out.println("Cliente actualizado correctamente.");

        } catch (ClienteNoEncontradoException | ValidacionException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void eliminarClienteView() {
        int id = leerEntero("Ingrese ID del cliente:", 1);

        try {
            cs.eliminarCliente(id);
            System.out.println("Cliente eliminado.");

        } catch (ClienteNoEncontradoException | OperacionNoPermitidaException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void prestamosClienteView() {
        int id = leerEntero("Ingrese ID del cliente:", 1);

        try {
            List<Prestamo> prestamos = cs.prestamosCliente(id);

            if (prestamos.isEmpty()) {
                System.out.println("El cliente no tiene préstamos.");
                return;
            }

            prestamos.forEach(System.out::println);

        } catch (ClienteNoEncontradoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // VALIDACIONES 

    private int leerEntero(String mensaje, int min) {
        int valor;
        do {
            System.out.print(mensaje + " ");
            while (!consola.hasNextInt()) {
                System.out.println("Debe ingresar un número válido.");
                consola.next();
            }
            valor = consola.nextInt();
        } while (valor < min);
        return valor;
    }

    private int leerEntero(String mensaje, int min, int max) {
        int valor;
        do {
            valor = leerEntero(mensaje, min);
        } while (valor > max);
        return valor;
    }

    private String leerTexto(String mensaje) {
        String texto;
        do {
            System.out.print(mensaje + " ");
            texto = consola.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }

    private String leerCorreo(String mensaje) {
        String correo;
        do {
            correo = leerTexto(mensaje);
            if (!correo.contains("@") || !correo.contains(".")) {
                System.out.println("Correo inválido.");
                correo = "";
            }
        } while (correo.isEmpty());
        return correo;
    }

    private String leerTelefono(String mensaje) {
        String telefono;
        do {
            telefono = leerTexto(mensaje);
            if (!telefono.matches("\\d+")) {
                System.out.println("El teléfono solo debe contener números.");
                telefono = "";
            }
        } while (telefono.isEmpty());
        return telefono;
    }
}
