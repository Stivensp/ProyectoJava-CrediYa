package com.finalproyectjava.util.view;

import java.time.LocalDate;

import com.finalproyectjava.model.Pago;
import com.finalproyectjava.service.PagoService;
import com.finalproyectjava.service.PrestamoService;


public class PagoView extends MenuBaseView{
    private final PagoService pagoS;
    //private final PrestamoService ps;
    //Constructor vacio
    public PagoView(PagoService pagosS, PrestamoService ps){
        this.pagoS = pagosS;
        //this.ps =  ps;
    }
    //Base del menu
    @Override
    public void play(){
        int opcion = 0;

        do{
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
             4. Guardar pago en BD (MySQL)
             5. Listar pagos desde BD
             0. Volver
             Seleccione una opcion:   
            """
            );

            if (!consola.hasNextInt()){
                System.out.println("Debe ingresar un numero");
                continue;
            }

            opcion = consola.nextInt();

            if(opcion < 0 || opcion > 5 ){
                System.out.println("Opción fuera de rango");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarPagoView();
                case 2 -> listaPagosView();
                case 3 -> pagosPorPrestamoView();
                case 4 -> limpiarConsola();
                case 5 -> limpiarConsola();
                case 0 -> limpiarConsola();
            
                default -> {
                }
            }
        } while(opcion != 0);
    }    
    //Registrar pago
    public void registrarPagoView(){
        System.out.println("Dame el id del prestamo");
        int prestamoId = consola.nextInt();

        System.out.println("Fecha inicio (YYYY-MM-DD):");
        String fechaTexto = consola.next();
        LocalDate fechaPago = LocalDate.parse(fechaTexto);

        System.out.println("De cuanto es el pago?");
        Double monto = consola.nextDouble();

        System.out.println("Pago registrado correctamente");
        pagoS.registrarPago(prestamoId, fechaPago, monto);
    }
    //Lista de todos los pagos
    public void listaPagosView(){
        if(!pagoS.listaPagos().isEmpty()){
            for(Pago p : pagoS.listaPagos()){
                System.out.println(p);
            }
        }
    }
    //Pagos por prestamo
    public void pagosPorPrestamoView(){
        System.out.println("Dame el id del prestamo");
        int prestamoId = consola.nextInt();

        if(pagoS.pagoPorPrestamo(prestamoId).isEmpty()){
            System.out.println("No existen pagos relacionados con este pago");
        }else{
            for(Pago p : pagoS.pagoPorPrestamo(prestamoId)){
                System.out.println(p);
            }
        }
    }

}
