package com.finalproyectjava.util.view;

import java.util.List;
import java.util.Map;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.service.ReporteService;

public class ReporteView extends MenuBaseView {
    private final ReporteService rs;

    public ReporteView(ReporteService rs) {
        this.rs = rs;
    }

    @Override
    public void play() {
        int opcion = -1;

        do {
            System.out.println("""
                
                                                                                        
            ▄▄▄▄▄▄▄                                       
            ███▀▀███▄                          ██         
            ███▄▄███▀ ▄█▀█▄ ████▄ ▄███▄ ████▄ ▀██▀▀ ▄█▀█▄ 
            ███▀▀██▄  ██▄█▀ ██ ██ ██ ██ ██ ▀▀  ██   ██▄█▀ 
            ███  ▀███ ▀█▄▄▄ ████▀ ▀███▀ ██     ██   ▀█▄▄▄ 
                            ██                            
                            ▀▀                                                                     
             1. Préstamos activos (estado = pendiente)
             2. Préstamos pagados
             3. Préstamos vencidos (fecha + cuotas)
             4. Clientes morosos (saldo > 0)
             5. Empleados con más préstamos otorgados
             6. Total recaudado por pagos
             0. Volver
             Seleccione una opcion:   
            """);

            if (!consola.hasNextInt()) {
                System.out.println("Debe ingresar un número.");
                consola.nextLine();
                continue;
            }

            opcion = consola.nextInt();
            consola.nextLine(); 

            limpiarConsola();

            switch (opcion) {
                case 1 -> mostrarPrestamos(rs.prestamosActivos(), "Préstamos Activos");
                case 2 -> mostrarPrestamos(rs.prestamosPagados(), "Préstamos Pagados");
                case 3 -> mostrarPrestamos(rs.prestamosVencidos(), "Préstamos Vencidos");
                case 4 -> mostrarClientesMorosos(rs.clientesMorosos());
                case 5 -> mostrarEmpleados(rs.empleadosConMasPrestamos());
                case 6 -> System.out.println("Total recaudado: $" + rs.totalRecaudado());
                case 0 -> System.out.println("Volviendo");
                default -> System.out.println("Opción fuera de rango.");
            }

        } while (opcion != 0);
    }

    private void mostrarPrestamos(List<Prestamo> prestamos, String titulo) {
        System.out.println( titulo );
        if (prestamos.isEmpty()) {
            System.out.println("No hay registros");
            return;
        }
        for (Prestamo p : prestamos) {
            System.out.println(p);
        }
    }

    private void mostrarClientesMorosos(List<Cliente> clientes) {
        System.out.println("Clientes Morosos");
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes morosos.");
            return;
        }
        for (Cliente c : clientes) {
            System.out.println(c);
        }
    }

    private void mostrarEmpleados(Map<Empleado, Long> empleados) {
        System.out.println("Empleados con más préstamos otorgados");
        if (empleados.isEmpty()) {
            System.out.println("No hay registros ");
            return;
        }
        empleados.entrySet()
                .stream()
                .sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
                .forEach(e -> System.out.println(e.getKey() + " -> " + e.getValue() + " préstamos"));
    }
}
