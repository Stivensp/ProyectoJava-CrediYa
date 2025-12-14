package com.finalproyectjava.util.view;

public class ReporteView extends MenuBaseView {   
     //Constructor vacio
    public ReporteView(){
    //Vacio
    }
    //Base del menu
    @Override
    public void play(){    
        int opcion = 0;

        do{
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
            """
            );

            if (!consola.hasNextInt()){
                System.out.println("Debe ingresar un numero");
                continue;
            }

            opcion = consola.nextInt();

            if(opcion < 0 || opcion > 6 ){
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
