package com.finalproyectjava.util.view;

public class MenuTecnicoView extends MenuBaseView {
    //Constructor vacio    
    public MenuTecnicoView(){
    //Vacio
    }
    //Base del menu
    @Override
    public void play(){    
        int opcion = 0;

        do{
            System.out.println("""            
                
                  
                
            ▄▄▄▄▄▄▄▄▄                                
            ▀▀▀███▀▀▀                ▀▀              
               ███ ▄█▀█▄ ▄████ ████▄ ██  ▄████ ▄███▄ 
               ███ ██▄█▀ ██    ██ ██ ██  ██    ██ ██ 
               ███ ▀█▄▄▄ ▀████ ██ ██ ██▄ ▀████ ▀███▀ 
                                                                                                                        
             1. Ver archivos TXT
             2. Borrar datos TXT
             3. Probar conexión MySQL
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
