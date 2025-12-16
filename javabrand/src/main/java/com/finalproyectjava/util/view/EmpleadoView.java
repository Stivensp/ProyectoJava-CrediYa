package com.finalproyectjava.util.view;

import java.util.List;

import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.service.EmpleadoService;

public class EmpleadoView extends MenuBaseView {

    private final EmpleadoService e;

    // Constructor
    public EmpleadoView(EmpleadoService e) {
        this.e = e;
    }

    // Menú principal
    @Override
    public void play() {
        int opcion;
        do {
            System.out.println("""
                    
            ▄▄▄▄▄▄▄                ▄▄                ▄▄             
            ███▀▀▀▀▀                ██                ██             
            ███▄▄    ███▄███▄ ████▄ ██ ▄█▀█▄  ▀▀█▄ ▄████ ▄███▄ ▄█▀▀▀ 
            ███      ██ ██ ██ ██ ██ ██ ██▄█▀ ▄█▀██ ██ ██ ██ ██ ▀███▄ 
            ▀███████ ██ ██ ██ ████▀ ██ ▀█▄▄▄ ▀█▄██ ▀████ ▀███▀ ▄▄▄█▀ 
                            ██                                     
                            ▀▀                                               
             1. Registrar empleado
             2. Listar empleados
             3. Buscar empleado por ID
             4. Actualizar empleado
             5. Eliminar empleado
             0. Volver
            """);

            opcion = leerEntero("Seleccione una opción:", 0, 5);

            switch (opcion) {
                case 1 -> registrarEmpleadoView();
                case 2 -> listarEmpleadosView();
                case 3 -> buscarEmpleadoIdView();
                case 4 -> actualizarEmpleadoView();
                case 5 -> eliminarEmpleadoView();
                case 0 -> limpiarConsola();
            }

        } while (opcion != 0);
    }

    // Registrar empleado
    private void registrarEmpleadoView() {
        consola.nextLine();

        String nombre = leerTexto("Nombre:");
        String documento = leerTexto("Documento:");
        String rol = leerTexto("Rol:");
        String correo = leerCorreo("Correo:");
        double salario = leerDouble("Salario:", 0);

        e.registrarEmpleado(nombre, documento, rol, correo, salario);
        System.out.println("Empleado registrado correctamente.");
    }

    // Listar empleados
    private void listarEmpleadosView() {
        List<Empleado> empleados = e.listaEmpleados();
        if (empleados.isEmpty()) {
            System.out.println("No hay empleados registrados.");
            return;
        }

        empleados.forEach(System.out::println);
    }

    // Buscar empleado por ID
    private void buscarEmpleadoIdView() {
        int id = leerEntero("Ingrese ID del empleado:", 1);

        Empleado em = e.buscarEmpleadoId(id);
        if (em == null) {
            System.out.println("Empleado no encontrado.");
            return;
        }

        System.out.println(em);
    }

    // Actualizar empleado
    private void actualizarEmpleadoView() {
        int id = leerEntero("Ingrese ID del empleado:", 1);
        consola.nextLine();

        String nombre = leerTexto("Nuevo nombre:");
        String documento = leerTexto("Nuevo documento:");
        String rol = leerTexto("Nuevo rol:");
        String correo = leerCorreo("Nuevo correo:");
        double salario = leerDouble("Nuevo salario:", 0);

        e.actualizarEmpleado(id, nombre, documento, rol, correo, salario);
        System.out.println("Empleado actualizado correctamente.");
    }

    // Eliminar empleado
    private void eliminarEmpleadoView() {
        int id = leerEntero("Ingrese ID del empleado:", 1);

        if (e.eliminarEmpleado(id)) {
            System.out.println("Empleado eliminado.");
        } else {
            System.out.println("Empleado no encontrado.");
        }
    }

    //  VALIDACIONES

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

    private double leerDouble(String mensaje, double min) {
        double valor;
        do {
            System.out.print(mensaje + " ");
            while (!consola.hasNextDouble()) {
                System.out.println("Debe ingresar un número válido.");
                consola.next();
            }
            valor = consola.nextDouble();
        } while (valor < min);
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
}
