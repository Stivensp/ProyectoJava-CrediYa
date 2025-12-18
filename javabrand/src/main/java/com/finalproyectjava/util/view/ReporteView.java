package com.finalproyectjava.util.view;

import java.util.List;
import java.util.Map;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.service.impl.GeneradorReportesImpl;
import com.finalproyectjava.service.impl.ReporteServiceImpl;

public class ReporteView extends MenuBaseView {

    private final ReporteServiceImpl rs;
    private final GeneradorReportesImpl gr;
    private final PrestamoView pv;

    public ReporteView(ReporteServiceImpl rs, GeneradorReportesImpl gr, PrestamoView pv) {
        this.gr = gr;
        this.rs = rs;
        this.pv = pv;
    }

    @Override
    public void play() {
        int opcion;

        do {
            System.out.println("""
                    
            ▄▄▄▄▄▄▄                                       
            ███▀▀███▄                          ██         
            ███▄▄███▀ ▄█▀█▄ ████▄ ▄███▄ ████▄ ▀██▀▀ ▄█▀█▄ 
            ███▀▀██▄  ██▄█▀ ██ ██ ██ ██ ██ ▀▀  ██   ██▄█▀ 
            ███  ▀███ ▀█▄▄▄ ████▀ ▀███▀ ██     ██   ▀█▄▄▄ 
                            ██                            
                            ▀▀                                                                     
             1. Préstamos activos
             2. Préstamos pagados
             3. Préstamos vencidos
             4. Clientes morosos
             5. Empleados con más préstamos
             6. Total recaudado por pagos
             7. Resumen Estadistico
             8. Lista de clientes activos
             0. Volver
            """);

            opcion = leerEntero("Seleccione una opción:", 0, 8);
            limpiarConsola();

            switch (opcion) {
                case 1 -> mostrarPrestamos(gr.prestamosActivos(), "Préstamos Activos");
                case 2 -> mostrarPrestamos(gr.prestamosPagados(), "Préstamos Pagados");
                case 3 -> mostrarPrestamos(gr.prestamosVencidos(), "Préstamos Vencidos");
                case 4 -> mostrarClientesMorosos(gr.clientesMorosos());
                case 5 -> mostrarEmpleados(rs.empleadosConMasPrestamos());
                case 6 -> System.out.println("Total recaudado: $" + rs.totalRecaudado());
                case 7 -> pv.verEstadoPrestamoView();
                case 8 -> gr.activos();
                case 0 -> System.out.println("Volviendo...");
            }

        } while (opcion != 0);
    }

    // MOSTRAR DATOS 

    private void mostrarPrestamos(List<Prestamo> prestamos, String titulo) {
        System.out.println(titulo);
        if (prestamos.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }
        prestamos.forEach(System.out::println);
    }

    private void mostrarClientesMorosos(List<Cliente> clientes) {
        System.out.println("Clientes Morosos");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes morosos.");
            return;
        }
        clientes.forEach(System.out::println);
    }

    private void mostrarEmpleados(Map<Empleado, Long> empleados) {
        System.out.println("Empleados con más préstamos otorgados");
        if (empleados.isEmpty()) {
            System.out.println("No hay registros.");
            return;
        }

        empleados.entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .forEach(e ->
                        System.out.println(
                                e.getKey() + " -> " + e.getValue() + " préstamos"
                        )
                );
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
}
