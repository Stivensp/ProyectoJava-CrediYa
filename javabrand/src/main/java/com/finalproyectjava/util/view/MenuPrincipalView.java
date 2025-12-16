package com.finalproyectjava.util.view;

import com.finalproyectjava.dao.impl.ClienteDAOImpl;
import com.finalproyectjava.dao.impl.EmpleadoDAOImpl;
import com.finalproyectjava.dao.impl.PagoDAOImpl;
import com.finalproyectjava.dao.impl.PagoDualDAOImpl;
import com.finalproyectjava.dao.impl.PagoTxtDAOImpl;
import com.finalproyectjava.dao.impl.PrestamoDAOImpl;
import com.finalproyectjava.dao.interfaces.ClienteDAO;
import com.finalproyectjava.dao.interfaces.EmpleadoDAO;
import com.finalproyectjava.dao.interfaces.PagoDAO;
import com.finalproyectjava.dao.interfaces.PrestamoDAO;
import com.finalproyectjava.service.ClienteService;
import com.finalproyectjava.service.EmpleadoService;
import com.finalproyectjava.service.PagoService;
import com.finalproyectjava.service.PrestamoService;
import com.finalproyectjava.service.ReporteService;

public class MenuPrincipalView extends MenuBaseView {
    //Dao
    ClienteDAO clienteDAO = new ClienteDAOImpl();
    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
    PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
    
    PagoDAO pagoDAO = new PagoDualDAOImpl(
            new PagoDAOImpl(),
            new PagoTxtDAOImpl()
    );
    
    //Atributos Service
    private final EmpleadoService empleadoService;
    private final ClienteService clienteService;
    private final PrestamoService prestamoService;
    private final PagoService pagoService;
    private final ReporteService reporteService;
    
    //Atributos View
    private final ReporteView rv;
    private final EmpleadoView ev;
    private final ClienteView cv;
    private final PrestamoView pv;
    private final PagoView pagoS;

    //Contructor
    public MenuPrincipalView() {
        //Service
        prestamoService = new PrestamoService(prestamoDAO);
        clienteService = new ClienteService(clienteDAO,prestamoService);
        empleadoService = new EmpleadoService(empleadoDAO);
        pagoService = new PagoService(pagoDAO);
        reporteService = new ReporteService(prestamoService, pagoService, clienteService, empleadoService);
        
        //View
        rv = new ReporteView(reporteService);
        ev = new EmpleadoView(empleadoService);
        cv = new ClienteView(clienteService, prestamoService);
        pv = new PrestamoView(prestamoService, clienteService, empleadoService);
        pagoS = new PagoView(pagoService, prestamoService);
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
                case 5 ->{
                    limpiarConsola();                    
                    rv.play();
                }
                case 6 -> limpiarConsola();
                case 0 -> limpiarConsola();
            
                default -> {
                }
            }
        } while(opcion != 0);
    }
}
