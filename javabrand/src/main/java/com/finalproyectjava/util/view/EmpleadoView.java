package com.finalproyectjava.util.view;

public class EmpleadoView extends MenuBaseView {
    //Constructor vacio
    public EmpleadoView(){
    //Vacio
    }
    //Base del menu
    @Override
    public void play(){
        int opcion = 0;

        do{
            System.out.println("""            
                
                
                                                     
            ▄▄▄▄▄▄▄                ▄▄                ▄▄             
            ███▀▀▀▀▀                ██                ██             
            ███▄▄    ███▄███▄ ████▄ ██ ▄█▀█▄  ▀▀█▄ ▄████ ▄███▄ ▄█▀▀▀ 
            ███      ██ ██ ██ ██ ██ ██ ██▄█▀ ▄█▀██ ██ ██ ██ ██ ▀███▄ 
            ▀███████ ██ ██ ██ ████▀ ██ ▀█▄▄▄ ▀█▄██ ▀████ ▀███▀ ▄▄▄█▀ 
                            ██                                     
                            ▀▀                                               
             0. Registrar empleado
             0. Listar empleados
             0. Buscar empleado por ID
             0. Actualizar empleado
             0. Eliminar empleado
             0. Registrar empleado en BD (MySQL)
             0. Listar empleados desde BD
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
