package com.finalproyectjava.util.view;

import java.util.List;

import com.finalproyectjava.model.Pago;
import com.finalproyectjava.service.PagoService;
import com.finalproyectjava.service.PrestamoService;

public class PagoView extends MenuBaseView {

    private final PagoService pagoS;

    // Constructor
    public PagoView(PagoService pagoS, PrestamoService ps) {
        this.pagoS = pagoS;
    }

    // Menú principal
    @Override
    public void play() {
        int opcion;
        do {
            System.out.println("""
                    
            ▄▄▄▄▄▄▄                          
            ███▀▀███▄                        
            ███▄▄███▀ ▀▀█▄ ▄████ ▄███▄ ▄█▀▀▀ 
            ███▀▀▀▀  ▄█▀██ ██ ██ ██ ██ ▀███▄ 
            ███      ▀█▄██ ▀████ ▀███▀ ▄▄▄█▀ 
                            ██             
                            ▀▀▀                                                          
             1. Registrar pago
             2. Listar pagos
             3. Ver pagos por préstamo
             0. Volver
            """);

            opcion = leerEntero("Seleccione una opción:", 0, 3);

            switch (opcion) {
                case 1 -> registrarPagoView();
                case 2 -> listarPagosView();
                case 3 -> pagosPorPrestamoView();
                case 0 -> limpiarConsola();
            }

        } while (opcion != 0);
    }

    // Registrar pago
    private void registrarPagoView() {
        int prestamoId = leerEntero("Ingrese ID del préstamo:", 1);
        double monto = leerDouble("Monto del pago:", 0.01);

        pagoS.registrarPago(prestamoId, monto);
        System.out.println("Pago registrado correctamente.");
    }

    // Listar todos los pagos
    private void listarPagosView() {
        List<Pago> pagos = pagoS.listaPagos();

        if (pagos.isEmpty()) {
            System.out.println("No hay pagos registrados.");
            return;
        }

        pagos.forEach(System.out::println);
    }

    // Pagos por préstamo
    private void pagosPorPrestamoView() {
        int prestamoId = leerEntero("Ingrese ID del préstamo:", 1);

        List<Pago> pagos = pagoS.pagoPorPrestamo(prestamoId);

        if (pagos.isEmpty()) {
            System.out.println("No existen pagos para este préstamo.");
            return;
        }

        pagos.forEach(System.out::println);
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
}
