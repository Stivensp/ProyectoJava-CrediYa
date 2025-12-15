package com.finalproyectjava.util.view;

public class MenuPrincipalView extends MenuBaseView {
    //Atributos
    private final EmpleadoView ev = new EmpleadoView();
    private final ClienteView cv = new ClienteView();
    private final PrestamoView pv = new PrestamoView();
    private final PagoView pagoS = new PagoView();
    //Constructor vacio
    public MenuPrincipalView(){
        //Vacio
    }
    //Base del menu
    @Override
    public void play(){
        int opcion = 0;

        do{
            System.out.println("""            
                
                
                
            ▄▄▄▄▄▄▄                                         ▄▄ 
            ███▀▀███▄       ▀▀              ▀▀              ██ 
            ███▄▄███▀ ████▄ ██  ████▄ ▄████ ██  ████▄  ▀▀█▄ ██ 
            ███▀▀▀▀   ██ ▀▀ ██  ██ ██ ██    ██  ██ ██ ▄█▀██ ██ 
            ███       ██    ██▄ ██ ██ ▀████ ██▄ ████▀ ▀█▄██ ██ 
                                                ██             
                                                ▀▀                        
             1. Gestión de Empleados
             2. Gestión de Clientes
             3. Gestión de Prestamos
             4. Gestión de Pagos
             5. Reportes
             6. Gestión Técnico
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
                case 1 -> {
                    limpiarConsola();
                    ev.play();
                }
                case 2 -> {
                    limpiarConsola();
                    cv.play();
                }
                case 3 -> {
                    limpiarConsola();
                    pv.play();
                }
                case 4 ->{
                    limpiarConsola();
                    pagoS.play();                    
                }
                case 5 -> limpiarConsola();
                case 6 -> limpiarConsola();
                case 0 -> limpiarConsola();
            
                default -> {
                }
            }
        } while(opcion != 0);
    }
}
