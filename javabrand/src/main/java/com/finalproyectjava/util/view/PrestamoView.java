package com.finalproyectjava.util.view;

public class PrestamoView extends MenuBaseView{
    //Constructor vacio
    public PrestamoView(){
    //Vacio
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
