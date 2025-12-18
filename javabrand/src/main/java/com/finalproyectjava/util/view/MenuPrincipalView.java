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
import com.finalproyectjava.service.impl.ClienteServiceImpl;
import com.finalproyectjava.service.impl.EmpleadoServiceImpl;
import com.finalproyectjava.service.impl.GeneradorReportesImpl;
import com.finalproyectjava.service.impl.PagoServiceImpl;
import com.finalproyectjava.service.impl.PrestamoServiceImpl;
import com.finalproyectjava.service.impl.ReporteServiceImpl;



public class MenuPrincipalView extends MenuBaseView {

    // DAO 
    ClienteDAO clienteDAO = new ClienteDAOImpl();
    EmpleadoDAO empleadoDAO = new EmpleadoDAOImpl();
    PrestamoDAO prestamoDAO = new PrestamoDAOImpl();
    PagoDAO pagoDAO = new PagoDualDAOImpl(
            new PagoDAOImpl(),
            new PagoTxtDAOImpl()
    );

    //  SERVICES 
    private final EmpleadoServiceImpl empleadoService;
    private final ClienteServiceImpl clienteService;
    private final PrestamoServiceImpl prestamoService;
    private final PagoServiceImpl pagoService;
    private final ReporteServiceImpl reporteService;
    private final GeneradorReportesImpl generadorReporte;

    //  VIEWS
    private final ReporteView rv;
    private final EmpleadoView ev;
    private final ClienteView cv;
    private final PrestamoView pv;
    private final PagoView pagoV;

    //  CONSTRUCTOR
    public MenuPrincipalView() {
        //  Pago primero
        pagoService = new PagoServiceImpl(pagoDAO);
        // Prestamo necesita PagoService
        prestamoService = new PrestamoServiceImpl(prestamoDAO, pagoService);
        //  Cliente necesita PrestamoService
        clienteService = new ClienteServiceImpl(clienteDAO, prestamoService);
        // Otros services
        empleadoService = new EmpleadoServiceImpl(empleadoDAO);
        reporteService = new ReporteServiceImpl(prestamoService,pagoService,clienteService,empleadoService);

        generadorReporte= new GeneradorReportesImpl(prestamoService,pagoService,clienteService,empleadoService,pagoDAO);
    //  Views
    ev = new EmpleadoView(empleadoService);
    cv = new ClienteView(clienteService, prestamoService);
    pv = new PrestamoView(prestamoService, clienteService, empleadoService);
    rv = new ReporteView(reporteService, generadorReporte,pv);
    pagoV = new PagoView(pagoService, prestamoService);
    }

    @Override
    public void play() {
        int opcion = 0;

        do {
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
             3. Gestión de Préstamos
             4. Gestión de Pagos
             5. Reportes
             0. Salir
             Seleccione una opción:
            """);

            if (!consola.hasNextInt()) {
                System.out.println("Debe ingresar un número");
                consola.next();
                continue;
            }

            opcion = consola.nextInt();

            switch (opcion) {
                case 1 -> { limpiarConsola(); ev.play(); }
                case 2 -> { limpiarConsola(); cv.play(); }
                case 3 -> { limpiarConsola(); pv.play(); }
                case 4 -> { limpiarConsola(); pagoV.play(); }
                case 5 -> { limpiarConsola(); rv.play(); }
                case 0 -> limpiarConsola();
                default -> System.out.println("Opción fuera de rango");
            }

        } while (opcion != 0);
    }
}
