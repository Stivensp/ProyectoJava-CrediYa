package com.finalproyectjava.util.view;

import com.finalproyectjava.model.Cliente;
import com.finalproyectjava.model.Prestamo;
import com.finalproyectjava.service.ClienteService;
import com.finalproyectjava.service.PrestamoService;

public class ClienteView extends MenuBaseView {

    private final ClienteService cs;
   // private final PrestamoService ps;

    //Constructor 
    public ClienteView(ClienteService cs, PrestamoService ps){
        this.cs = cs;
    // this.ps = ps;
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
                System.out.println("Opcin fuera de rango");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarClienteView();
                case 2 -> listaClientesView();
                case 3 -> buscaIdClienteView();
                case 4 -> actualizarClienteView();
                case 5 -> eliminarClienteIdView();
                case 6 -> prestamosClienteView();
                case 0 -> limpiarConsola();
            
                default -> {
                }
            }
        } while(opcion != 0);
    }    
    //Metodos de registro
    public void registrarClienteView(){
        System.out.println("Nombre ");
        String nombre = consola.next();

        System.out.println("Documento ");
        String documento = consola.next();

        System.out.println("Correo ");
        String correo = consola.next();

        System.out.println("Telefono" );
        String telefono = consola.next();
        cs.registrarCliente(nombre, documento, correo, telefono);        
    }
    //Metodo de lista de todos los clientes
    public void listaClientesView(){
        for(Cliente c : cs.listaClientes()){
            System.out.println(c);
        }
    }
    //Metodo buscar por id
    public void buscaIdClienteView(){
        System.out.println("Buscador de ID ");
        int findId = consola.nextInt();
        
        Cliente c = cs.buscarClienteId(findId);

        if(c != null){
            System.out.println(c);
            System.out.println("Cliente encontrado");
        }else {
            System.out.println("Cliente no encontrado");
        }
    }
    //Metodo actualizar por id
    public void actualizarClienteView(){
        System.out.println("Buscador de ID ");
        int findId = consola.nextInt();

        System.out.println("Nombre ");
        String nombre = consola.next();

        System.out.println("Documento ");
        String documento = consola.next();

        System.out.println("Correo ");
        String correo = consola.next();

        System.out.println("Telefono" );
        String telefono = consola.next();
        cs.actualizarCliente(findId, nombre, documento, correo, telefono);        
    }
    //Metodo eliminar por id
    public void eliminarClienteIdView(){
        System.out.println("Buscador de ID ");
        int findId = consola.nextInt();
        
        boolean bol = cs.eliminarCliente(findId);
        if(bol == true){
            System.out.println("Cliente eliminado");
        } else {
            System.out.println("Cliente no encontrado");
        }
    }
    // Mostrar préstamos del cliente
    public void prestamosClienteView(){
        System.out.println("Buscador de ID");
        int clienteId = consola.nextInt();

        if(!cs.prestamosCliente(clienteId).isEmpty()){
            for(Prestamo p : cs.prestamosCliente(clienteId)){
                System.out.println(p);
                System.out.println("Prestamos encontrados");
            }
        }else{
            System.out.println("Clientes no encontrados");
        }

    }

}
