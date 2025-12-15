package com.finalproyectjava.util.view;

import java.time.LocalDate;

import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.model.Prestamo.EstadoPrestamo;
import com.finalproyectjava.service.ClienteService;
import com.finalproyectjava.service.EmpleadoService;
import com.finalproyectjava.service.PrestamoService;

public class PrestamoView extends MenuBaseView{
    //Constructor vacio
    private final PrestamoService ps;
    //private final ClienteService cs;
    //private final EmpleadoService es;

    public PrestamoView(PrestamoService ps, ClienteService cs, EmpleadoService es){
        this.ps =ps;
    //    this.cs =cs;
    //    this.es =es;

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
                case 5 -> recalcularCuotasView();
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

        System.out.println("Interés en porcentaje ej: 20");
        double interes = consola.nextDouble();

        System.out.println("Cantidad de cuotas:");
        int cuotas = consola.nextInt();

        System.out.println("Fecha inicio (YYYY-MM-DD):");
        String fechaTexto = consola.next();
        LocalDate fechaInicio = LocalDate.parse(fechaTexto);

        ps.agregarPrestamo(
            clienteId,
            empleadoId,
            monto,
            interes,
            cuotas,
            fechaInicio
        );

        System.out.println("Préstamo registrado correctamente");
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
    public void recalcularCuotasView() {

        System.out.print("ID del préstamo: ");
        int id = consola.nextInt();

        Prestamo prestamo = ps.buscarPrestamoId(id);

        if (prestamo == null) {
            System.out.println("Prestamo no encontrado");
            return;
        }

        ps.recalcularCuotas(prestamo);

        System.out.println("Cuota recalculada correctamente");
        System.out.println("Nueva cuota: " + prestamo.getValorCuota());
    }
}
