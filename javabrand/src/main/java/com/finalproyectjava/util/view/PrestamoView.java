package com.finalproyectjava.util.view;

import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;
import com.finalproyectjava.service.impl.ClienteServiceImpl;
import com.finalproyectjava.service.impl.EmpleadoServiceImpl;
import com.finalproyectjava.service.impl.PrestamoServiceImpl;

public class PrestamoView extends MenuBaseView {

    private final PrestamoServiceImpl ps;

    public PrestamoView(PrestamoServiceImpl ps, ClienteServiceImpl cs, EmpleadoServiceImpl es) {
        this.ps = ps;
    }

    @Override
    public void play() {
        int opcion = 0;

        do {
            System.out.println("""
                    
            ▄▄▄▄▄▄▄                                                     
            ███▀▀███▄                    ██                             
            ███▄▄███▀ ████▄ ▄█▀█▄ ▄█▀▀▀ ▀██▀▀ ▀▀█▄ ███▄███▄ ▄███▄ ▄█▀▀▀ 
            ███▀▀▀▀   ██ ▀▀ ██▄█▀ ▀███▄  ██  ▄█▀██ ██ ██ ██ ██ ██ ▀███▄ 
            ███       ██    ▀█▄▄▄ ▄▄▄█▀  ██  ▀█▄██ ██ ██ ██ ▀███▀ ▄▄▄█▀ 
                                                                        
            1. Crear préstamo
            2. Listar préstamos
            3. Buscar préstamo por ID
            4. Cambiar estado
            5. Recalcular cuota
            6. Prestamo Detalles
            0. Volver
            Seleccione una opción:
            """);

            if (!consola.hasNextInt()) {
                System.out.println("Entrada inválida. Debe ingresar un número.");
                consola.next();
                continue;
            }

            opcion = consola.nextInt();

            switch (opcion) {
                case 1 -> agregarPrestamoView();
                case 2 -> listarPrestamosView();
                case 3 -> buscarPrestamoIdView();
                case 4 -> cambiarEstadoPrestamoView();
                case 5 -> recalcularCuotasView();
                case 6 -> verEstadoPrestamoView();
                case 0 -> limpiarConsola();
                default -> System.out.println("Opción fuera de rango.");
            }

        } while (opcion != 0);
    }

    private void agregarPrestamoView() {

        int clienteId = leerEntero("ID Cliente:", 1);
        int empleadoId = leerEntero("ID Empleado:", 1);
        double monto = leerDouble("Monto:", 1);
        double interes = leerDouble("Interés (%):", 0);
        int cuotas = leerEntero("Cantidad de cuotas:", 1);

        ps.agregarPrestamo(clienteId, empleadoId, monto, interes, cuotas);
        System.out.println("Préstamo registrado correctamente.");
    }


    private void listarPrestamosView() {
        var lista = ps.listaPrestamo();

        if (lista.isEmpty()) {
            System.out.println("No hay prestamos registrados");
            return;
        }

        lista.forEach(p -> {
            System.out.println(p);
            System.out.println("--------------------------------");
        });
    }


    private void buscarPrestamoIdView() {
        int id = leerEntero("ID del préstamo:", 1);

        Prestamo p = ps.buscarPrestamoId(id);
        if (p == null) {
            System.out.println("Préstamo no encontrado.");
            return;
        }

        System.out.println(p);
    }

    private void cambiarEstadoPrestamoView() {
        int id = leerEntero("ID del préstamo:", 1);

        Prestamo p = ps.buscarPrestamoId(id);
        if (p == null) {
            System.out.println("Préstamo no encontrado.");
            return;
        }

        System.out.println("""
            Estado actual: %s
            1. PENDIENTE
            2. PAGADO
            """.formatted(p.getEstado()));

        int opcion = leerEntero("Seleccione estado:", 1, 2);
        EstadoPrestamo nuevoEstado =
                (opcion == 1) ? EstadoPrestamo.PENDIENTE : EstadoPrestamo.PAGADO;

        ps.cambiarEstadoPrestamo(id, nuevoEstado);
        System.out.println("Estado actualizado correctamente.");
    }

    private void recalcularCuotasView() {
        int id = leerEntero("ID del préstamo:", 1);

        Prestamo p = ps.buscarPrestamoId(id);
        if (p == null) {
            System.out.println("Préstamo no encontrado.");
            return;
        }

        ps.recalcularCuotas(p);
        System.out.println("Cuota recalculada.");
        System.out.println("Nueva cuota: " + p.getValorCuota());
    }

private void verEstadoPrestamoView() {

    int id = leerEntero("ID del préstamo:", 1);

    Prestamo prestamo = ps.buscarPrestamoId(id);
    if (prestamo == null) {
        System.out.println("Préstamo no encontrado.");
        return;
    }

    var pagos = ps.obtenerPagosPrestamo(id);

    // Actualiza estado si ya se pagó todo
    ps.actualizarEstadoPrestamo(id);

    double totalPrestamo = ps.calcularTotalPrestamo(prestamo);
    double totalPagado = ps.calcularTotalPagado(pagos);
    double deudaRestante = ps.calcularDeudaRestante(prestamo, pagos);

    System.out.println("""
        ===============================
        DETALLE DEL PRÉSTAMO
        ===============================
        ID Préstamo: %d
        Cliente ID: %d
        Empleado ID: %d

        Monto original: %.2f
        Interés: %.2f %%
        Total a pagar: %.2f

        Cuotas: %d
        Valor por cuota: %.2f

        Total pagado: %.2f
        Deuda restante: %.2f

        Estado: %s
        ===============================
        """.formatted(
            prestamo.getId(),
            prestamo.getClienteId(),
            prestamo.getEmpleadoId(),
            prestamo.getMonto(),
            prestamo.getInteres(),
            totalPrestamo,
            prestamo.getCuotas(),
            prestamo.getValorCuota(),
            totalPagado,
            deudaRestante,
            prestamo.getEstado()
        ));

    System.out.println("PAGOS REGISTRADOS:");
    if (pagos.isEmpty()) {
        System.out.println("No hay pagos registrados.");
    } else {
        pagos.forEach(p ->
            System.out.println(
                "Fecha: " + p.getfechaPago() +
                " | Monto: " + p.getMonto()
            )
        );
    }


    System.out.println("\nDETALLE DE CUOTAS:");
    ps.generarDetalleCuotas(prestamo, pagos)
      .forEach(System.out::println);

    System.out.println("===============================");
}


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
}
