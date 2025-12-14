package com.finalproyectjava.util.view;

import com.finalproyectjava.model.Empleado;
import com.finalproyectjava.service.EmpleadoService;

public class EmpleadoView extends MenuBaseView {
    private final EmpleadoService e;
    //Constructor vacio
    public EmpleadoView(){
        this.e = new EmpleadoService();
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
             1. Registrar empleado
             2. Listar empleados
             3. Buscar empleado por ID
             4. Actualizar empleado
             5. Eliminar empleado
             6. Registrar empleado en BD (MySQL)
             7. Listar empleados desde BD
             0. Volver
             Seleccione una opcion:   
            """
            );

            if (!consola.hasNextInt()){
                System.out.println("Debe ingresar un numero");
                continue;
            }

            opcion = consola.nextInt();

            if(opcion < 0 || opcion > 7 ){
                System.out.println("Opción fuera de rango");
                continue;
            }

            switch (opcion) {
                case 1 -> registrarEmpleadoView();
                case 2 -> {
                    limpiarConsola();
                    listaEmpleadosView();
                }
                case 3 -> buscaIdEmpleadosView();
                case 4 -> actualizarEmpleadoView();
                case 5 -> eliminarEmpleadoIdView();
                case 0 -> limpiarConsola();
            
                default -> {
                }
            }
        } while(opcion != 0);
    }

    //Metodo de registro
    public void registrarEmpleadoView(){
        System.out.println("Nombre ");
        String nombre = consola.next();

        System.out.println("Documento ");
        String documento = consola.next();

        System.out.println("Rol" );
        String rol = consola.next();

        System.out.println("Correo ");
        String correo = consola.next();

        System.out.println("Salario" );
        Double salario = consola.nextDouble();

        e.registrarEmpleado(nombre, documento, rol, correo, salario);
    }

    //Metodo de listar empleados
    public void listaEmpleadosView(){
        for(Empleado em : e.listaEmpleados()){
            System.out.println(em);
        }
    }
    //Metodo buscar por id
    public void buscaIdEmpleadosView(){
        System.out.println("Buscador de ID ");
        int findId = consola.nextInt();

        Empleado em = e.buscarEmpleadoId(findId);

        if(em != null){
            System.out.println(em);
            System.out.println("Empleado encontrado");
        }else{
            System.out.println("ID no encontrado");
        }
    }

    //Metodo actualizar empleado
    public void actualizarEmpleadoView(){
        System.out.println("Buscador de ID ");
        int id = consola.nextInt();

        System.out.println("Nombre ");
        String nombre = consola.next();

        System.out.println("Documento ");
        String documento = consola.next();

        System.out.println("Rol" );
        String rol = consola.next();

        System.out.println("Correo ");
        String correo = consola.next();

        System.out.println("Salario" );
        Double salario = consola.nextDouble();

        e.actualizarEmpleado(id, nombre, documento, rol, correo, salario);
    }

    //Metodo eliminar empleado
    public void eliminarEmpleadoIdView(){
        System.out.println("Buscador de ID ");
        int findId = consola.nextInt();
        
        boolean siOno = e.eliminarEmpleado(findId);
        if(siOno == true){
            System.out.println("Empleado eliminado");
        }else{
            System.out.println("Empleado no encontrado");
        }
    }
}
