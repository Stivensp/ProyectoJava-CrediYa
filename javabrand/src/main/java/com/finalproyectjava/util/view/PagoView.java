package com.finalproyectjava.util.view;

public class PagoView extends MenuBaseView{
    //Constructor vacio
    public PagoView(){
    //Vacio
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
                case 1:
                    limpiarConsola();
                    break;
                case 2:
                    limpiarConsola();
                    break;
                case 3:
                    limpiarConsola();                    
                    break;
                case 4:
                    limpiarConsola();                    
                    break;
                case 5:
                    limpiarConsola();                    
                    break;
                case 0:
                    limpiarConsola();                    
                    break;
            
                default: 
                    break;
            }
        } while(opcion != 0);
    }    

}
