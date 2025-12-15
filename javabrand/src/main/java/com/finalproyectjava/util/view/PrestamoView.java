package com.finalproyectjava.util.view;

import java.time.LocalDate;

import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;
import com.finalproyectjava.service.PrestamoService;

public class PrestamoView extends MenuBaseView{
    //Constructor vacio
    private final PrestamoService ps;
    public PrestamoView(){
        this.ps = new PrestamoService();
    }
    //Base del menu
    @Override
    public void play(){
        int opcion = 0;

        do{
            System.out.println("""            
                
                
                                                                                                                
            ▄▄▄▄▄▄▄                                                     
            ███▀▀███▄                    ██                             
            ███▄▄███▀ ████▄ ▄█▀█▄ ▄█▀▀▀ ▀██▀▀ ▀▀█▄ ███▄███▄ ▄███▄ ▄█▀▀▀ 
            ███▀▀▀▀   ██ ▀▀ ██▄█▀ ▀███▄  ██  ▄█▀██ ██ ██ ██ ██ ██ ▀███▄ 
            ███       ██    ▀█▄▄▄ ▄▄▄█▀  ██  ▀█▄██ ██ ██ ██ ▀███▀ ▄▄▄█▀ 
                                                                        
                                                                            
            1. Crear préstamo
            2. Listar préstamos
            3. Buscar préstamo por ID
            4. Cambiar estado (Pendiente / Pagado)
            5. Recalcular cuotas
            6. Guardar préstamo en BD (MySQL)
            7. Listar préstamos desde BD
            0. Volver
             Seleccione una opcion:   
            """
            );

            if (!consola.hasNextInt()){
                System.out.println("Debe ingresar un numero");
                continue;
            }

            opcion = consola.nextInt();

            if(opcion < 0 || opcion > 7 ){
                System.out.println("Opción fuera de rango");
                continue;
            }

            switch (opcion) {
                case 1 -> agregarPrestamoView();
                case 2 -> listaPrestamosView();
                case 3 -> buscarPrestamoIdView();
                case 4 -> cambiarEstadoPrestamoView();
                case 5 -> limpiarConsola();
                case 0 -> limpiarConsola();
            
                default -> {
                }
            }
        } while(opcion != 0);
    }
    //Agregar pago
    public void agregarPrestamoView() {

        System.out.println("ID Cliente:");
        int clienteId = consola.nextInt();

        System.out.println("ID Empleado:");
        int empleadoId = consola.nextInt();

        System.out.println("Monto:");
        double monto = consola.nextDouble();

        System.out.println("Interés (ej: 0.2):");
        double interes = consola.nextDouble();

        System.out.println("Cantidad de cuotas:");
        int cuotas = consola.nextInt();

        System.out.println("Fecha inicio (YYYY-MM-DD):");
        String fechaTexto = consola.next();
        LocalDate fechaInicio = LocalDate.parse(fechaTexto);

        System.out.println("Estado:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. PAGADO");
        int opcionEstado = consola.nextInt();

        EstadoPrestamo estado =
                (opcionEstado == 1) ? EstadoPrestamo.PENDIENTE : EstadoPrestamo.PAGADO;

             ps.agregarPrestamo(
                clienteId,
                empleadoId,
                monto,
                interes,
                cuotas,
                fechaInicio,
                estado
        );

        System.out.println("Prestamo registrado correctamenten ");
    }
    //Lista de todos los prestamos
    public void listaPrestamosView(){
        if(!ps.listaPrestamo().isEmpty()){
            for(Prestamo p : ps.listaPrestamo()){
                System.out.println(p);
                System.out.println("Cliente encontrado ");
            }
        }else{
                System.out.println("Lista de prestamos vacia ");
            }
    }
    //Buscar prestamo por id
    public void buscarPrestamoIdView(){
        System.out.println("ID buscado");
        int id = consola.nextInt();

        Prestamo p = ps.buscarPrestamoId(id);
        if(p != null){
            System.out.println(p);
        }else{
            System.out.println("No se encontro el prestamo");
        }

    }
    //Cambiar estado del prestamo por id
    public void cambiarEstadoPrestamoView(){
        System.out.println("ID buscado");
        int id = consola.nextInt();

        System.out.println("Estado:");
        System.out.println("1. PENDIENTE");
        System.out.println("2. PAGADO");
        int opcionEstado = consola.nextInt();  
        
        EstadoPrestamo estadPrestamo = (opcionEstado == 1) ? EstadoPrestamo.PENDIENTE : EstadoPrestamo.PAGADO;
    
        ps.cambiarEstadoPrestamo(id, estadPrestamo);
    }
    //Recalcular cuotas
    public void recalcularCuotas(){
        System.out.println("No esta este metodo aun");
    }
}
