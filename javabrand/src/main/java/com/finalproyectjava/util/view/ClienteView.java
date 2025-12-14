package com.finalproyectjava.util.view;

public class ClienteView extends MenuBaseView {
    //Constructor Vacio
    public ClienteView(){
    //Vacio
    }
    //Base del menu
    @Override
    public void play(){
        int opcion = 0;

        do{
            System.out.println("""            
                
                
                                        
            ▄▄▄▄▄▄▄ ▄▄                             
            ███▀▀▀▀▀ ██ ▀▀               ██         
            ███      ██ ██  ▄█▀█▄ ████▄ ▀██▀▀ ▄█▀█▄ 
            ███      ██ ██  ██▄█▀ ██ ██  ██   ██▄█▀ 
            ▀███████ ██ ██▄ ▀█▄▄▄ ██ ██  ██   ▀█▄▄▄ 
                                                    
                                                            
             1. Registrar cliente
             2. Listar clientes
             3. Buscar cliente por ID
             4. Actualizar cliente
             5. Eliminar cliente
             6. Mostrar préstamos del cliente
             7. Guardar cliente en BD (MySQL)
             8. Listar clientes desde BD
             0. Salir
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
                case 6:
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
